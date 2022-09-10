package app.util

import kotlin.reflect.KProperty

class PropsDelegate<T>(
    private val value: T,
    private val set: (T) -> Unit,
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = value

    operator fun setValue(thisRef: Any?, property: KProperty<*>, next: T) {
        set(next)
    }
}
