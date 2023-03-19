package app.v2.factory

import app.v2.data.Factory

data class FactoryHistory(
    val history: List<Factory>,
    val index: Int,
) {
    val factory = history[index]

    fun hasPrevious() = index > 0
    fun getPrevious() = FactoryHistory(history, index - 1)

    fun hasNext() = index + 1 < history.size
    fun getNext() = FactoryHistory(history, index + 1)

    fun append(factory: Factory) = FactoryHistory(history.subList(0, index + 1) + factory, index + 1)
}
