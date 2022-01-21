package io.github.duzhaokun123.androidapptemplate.utils

import kotlinx.coroutines.CoroutineScope

data class RunIOCatchingPending<R>(
    val block: suspend CoroutineScope.() -> R,
    var onSuccess: (suspend CoroutineScope.(R) -> Unit)? = null,
    var onFailure: (suspend CoroutineScope.(Throwable) -> Unit)? = null,
    var result: Result<R>? = null
)

fun <R> runIOCatching(block: suspend CoroutineScope.() -> R) = RunIOCatchingPending(block)

fun <R> RunIOCatchingPending<R>.onSuccess(onSuccess: suspend CoroutineScope.(R) -> Unit) =
    apply { this.onSuccess = onSuccess }

fun <R> RunIOCatchingPending<R>.onFailure(onFailure: suspend CoroutineScope.(Throwable) -> Unit) =
    apply { this.onFailure = onFailure }

fun <R> RunIOCatchingPending<R>.launch() {
    runIO {
        result = runCatching { block() }
            .onSuccess { onSuccess?.invoke(this, it) }
            .onFailure { onFailure?.invoke(this, it) }
    }
}