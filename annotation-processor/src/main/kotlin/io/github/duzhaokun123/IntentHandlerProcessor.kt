package io.github.duzhaokun123

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration

@Target(AnnotationTarget.CLASS)
annotation class IntentFilter

class IntentHandlerProcessor(
    private val codeGenerator: CodeGenerator,
): SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {

        val symbols = resolver
            .getSymbolsWithAnnotation(IntentFilter::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()
            .toList()

        val file = runCatching { codeGenerator.createNewFile(
            dependencies = Dependencies(false, *resolver.getAllFiles().toList().toTypedArray()),
            packageName = "io.github.duzhaokun123.codegen",
            fileName = "IntentHandler"
        ).bufferedWriter() }.getOrNull() ?: return symbols

        file.appendLine("val intentFilters by lazy { listOf(")
        symbols.forEach { symbol ->
            file.appendLine("${symbol.qualifiedName!!.asString()}(),")
        }
        file.appendLine(")}")

        file.close()
        return emptyList()
    }
}

class IntentHandlerProcessorProvider: SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return IntentHandlerProcessor(environment.codeGenerator)
    }
}