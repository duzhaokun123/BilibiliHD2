package io.github.duzhaokun123

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSPropertyDeclaration

@Target(AnnotationTarget.FIELD)
annotation class SettingsValue(val value: String, val saveAsString: Boolean = false)

class StringSet

class SettingsProcessor(
    private val codeGenerator: CodeGenerator
): SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {

        val symbols = resolver
            .getSymbolsWithAnnotation(SettingsValue::class.qualifiedName!!)
            .filterIsInstance<KSPropertyDeclaration>()
            .toList()

        val file = runCatching { codeGenerator.createNewFile(
            dependencies = Dependencies(false, *resolver.getAllFiles().toList().toTypedArray()),
            packageName = "io.github.duzhaokun123.codegen",
            fileName = "Settings"
        ).bufferedWriter() }.getOrNull() ?: return symbols

        file.append("package io.github.duzhaokun123.codegen\n\n")
        file.append("""
            |import android.content.Context
            |import android.content.SharedPreferences
            |import androidx.preference.PreferenceManager
            |
            |object Settings {
            | lateinit var preferences: SharedPreferences
            |  private set
            |
            | fun init(context: Context) {
            |  preferences = PreferenceManager.getDefaultSharedPreferences(context)
            | }
            | 
            """.trimMargin())

        setOf("Int", "Long", "Float").forEach { type ->
            file.append("""
                |fun SharedPreferences.getStringAs$type(key: String, defValue: $type) =
                |try {
                | getString(key, "")!!.to$type()
                |} catch (e: NumberFormatException) {
                | defValue
                |}
                |
            """.trimMargin())
            file.append("""
                |fun SharedPreferences.Editor.put${type}AsString(key: String, value: $type) =
                | preferences.edit().putString(key, value.toString())
                |
            """.trimMargin())
        }

        symbols.forEach { symbol ->
            val name = symbol.simpleName.asString()
            val type = symbol.type.toString()
            val arguments = symbol.annotations.find { it.annotationType.toString() == SettingsValue::class.simpleName }!!.arguments
            val value = arguments.find { it.name!!.asString() == "value" }!!.value.let { if (type == "String") "\"$it\"" else it }
            val saveAsString = arguments.find { it.name!!.asString() == "saveAsString" }!!.value as Boolean
            file.appendLine("//$name: $type = $value")

            if (saveAsString) {
                file.append("""
                    |var $name
                    | get() = preferences.getStringAs$type("$name", $value)
                    | set(value) {
                    |  preferences.edit().put${type}AsString("$name", value).apply()
                    | }
                    |
                """.trimMargin())
            } else {
                file.append("""
                    |var $name
                    | get() = preferences.get$type("$name", $value)${"!!".takeIf { type == "StringSet" } ?: ""}
                    | set(value) {
                    |  preferences.edit().put$type("$name", value).apply()
                    | }
                    |
                """.trimMargin())
            }
            file.append("""
                |fun remove${name.let { it.replaceRange(0, 1, "${it[0].uppercaseChar()}") }}() {
                | preferences.edit().remove("$name").apply()
                |}
                |
            """.trimMargin())
        }

        file.append("\n}")

        file.close()
        return emptyList()
    }
}

class SettingsProcessorProvider: SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return SettingsProcessor(environment.codeGenerator)
    }
}