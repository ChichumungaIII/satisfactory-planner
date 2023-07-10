package app.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

private val Scope = MainScope()
fun launchMain(block: suspend CoroutineScope.() -> Unit) = Scope.launch(block = block)
fun <T> asyncMain(block: suspend CoroutineScope.() -> T) = Scope.async(block = block)
