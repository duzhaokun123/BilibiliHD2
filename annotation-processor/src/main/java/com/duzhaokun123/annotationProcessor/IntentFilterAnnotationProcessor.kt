package com.duzhaokun123.annotationProcessor

import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@Target(AnnotationTarget.CLASS)
annotation class IntentFilter

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.duzhaokun123.annotationProcessor.IntentFilter")
@SupportedOptions(KAPT_KOTLIN_GENERATED_OPTION_NAME)
class IntentHandlerAnnotationProcessor : AbstractProcessor() {

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment
    ): Boolean {
        val annotatedElements = roundEnv.getElementsAnnotatedWith(IntentFilter::class.java)
        if (annotatedElements.isEmpty()) return false
        val kaptKotlinGeneratedDir =
            processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: run {
                processingEnv.messager.printMessage(
                    Diagnostic.Kind.ERROR, "Can't find target dir for generated kotlin files"
                )
                return false
            }

        val codeSb = StringBuilder()
        codeSb.appendLine("val intentFilters by lazy { listOf(")
        annotatedElements.forEach {
            codeSb.appendLine("  ${it.asType()}(),")
        }
        codeSb.appendLine(")}")

        with(File(kaptKotlinGeneratedDir, "IntentFilters.kt")) {
            parentFile.mkdirs()
            writeText(codeSb.toString())
        }

        return true
    }
}