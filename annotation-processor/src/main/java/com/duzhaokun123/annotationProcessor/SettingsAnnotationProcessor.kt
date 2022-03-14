package com.duzhaokun123.annotationProcessor

import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement
import javax.tools.Diagnostic

class StringSet

/**
 * @param saveAsString only support int, Long, Float
 */
@Target(AnnotationTarget.FIELD)
annotation class SettingsValue(val saveAsString: Boolean = false)

@Target(AnnotationTarget.FIELD)
annotation class StringSetDefValue(val defValue: String)

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.duzhaokun123.annotationProcessor.SettingsValue")
@SupportedOptions(KAPT_KOTLIN_GENERATED_OPTION_NAME)
class SettingsAnnotationProcessor : AbstractProcessor() {

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment
    ): Boolean {
        val annotatedElements = roundEnv.getElementsAnnotatedWith(SettingsValue::class.java)
        if (annotatedElements.isEmpty()) return false

        val kaptKotlinGeneratedDir =
            processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: run {
                processingEnv.messager.printMessage(
                    Diagnostic.Kind.ERROR, "Can't find target dir for generated kotlin files"
                )
                return false
            }

        val codeSb = StringBuilder()
        codeSb.append(
            """
            |package com.duzhaokun123.generated
            |
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
        """.trimMargin()
        )

        setOf("Int", "Long", "Float").forEach { type ->
            codeSb.append(
                """
                |fun SharedPreferences.getStringAs$type(key: String, defValue: $type) =
                |try {
                | getString(key, "")!!.to$type()
                |} catch (e: NumberFormatException) {
                | defValue
                |}
                |
            """.trimMargin()
            )
            codeSb.append(
                """
                |fun SharedPreferences.Editor.put${type}AsString(key: String, value: $type) =
                | preferences.edit().putString(key, value.toString())
                |
            """.trimMargin()
            )
        }

        for (element in annotatedElements) {
            val variableElement = element.toVariableElementOrNull() ?: continue
            val type = when (variableElement.asType().toString()) {
                "int" -> "Int"
                "long" -> "Long"
                "float" -> "Float"
                "boolean" -> "Boolean"
                "java.lang.String" -> "String"
                "com.duzhaokun123.annotationProcessor.StringSet" -> "StringSet"
                else -> {
                    processingEnv.messager.printMessage(
                        Diagnostic.Kind.ERROR,
                        "Unsupported element type: ${variableElement.asType()}",
                        variableElement
                    )
                    return false
                }
            }
            val defaultValue = variableElement.getDefaultValueString(type) ?: return false
            val name = variableElement.simpleName
            if (variableElement.getAnnotation(SettingsValue::class.java).saveAsString) {
                codeSb.append(
                    """
                    |var $name
                    | get() = preferences.getStringAs$type("$name", $defaultValue)
                    | set(value) {
                    |  preferences.edit().put${type}AsString("$name", value).apply()
                    | }
                    |
                """.trimMargin()
                )
            } else {
                codeSb.append(
                    """
                    |var $name
                    | get() = preferences.get$type("$name", $defaultValue)${"!!".takeIf { type == "StringSet" } ?: ""}
                    | set(value) {
                    |  preferences.edit().put$type("$name", value).apply()
                    | }
                    |
                """.trimMargin()
                )
            }
            codeSb.append(
                """
                |fun remove${name.let { it.replaceRange(0, 1, "${it[0].uppercaseChar()}") }}() {
                | preferences.edit().remove("$name").apply()
                |}
                |
            """.trimMargin()
            )
        }

        codeSb.append("\n}")

        File(kaptKotlinGeneratedDir, "Settings.kt").apply {
            parentFile.mkdirs()
            writeText(codeSb.toString())
        }

        return true
    }

    private fun Element.toVariableElementOrNull(): VariableElement? {
        if (this !is VariableElement) {
            processingEnv.messager.printMessage(
                Diagnostic.Kind.ERROR, "Invalid element type ", this
            )
            return null
        }

        return this
    }

    private fun VariableElement.getDefaultValueString(type: String): String? {
        return when (type) {
            "Int", "Long", "Boolean" -> constantValue?.toString() ?: "null"
            "Float" -> constantValue.toString() + "F"
            "String" -> "\"$constantValue\""
            "StringSet" -> this.getAnnotation(StringSetDefValue::class.java)?.defValue ?: "emptySet()"
            else -> {
                processingEnv.messager.printMessage(
                    Diagnostic.Kind.ERROR, "Unsupported element type: $type", this
                )
                return null
            }
        }
    }
}