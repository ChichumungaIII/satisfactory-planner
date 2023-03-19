package app.v2.factory

import app.v2.data.Factory
import react.FC
import react.PropsWithChildren
import react.StateInstance
import react.createContext
import react.useState

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

val FactoryHistoryContext = createContext<StateInstance<FactoryHistory>>()

external interface FactoryHistoryContextProviderProps : PropsWithChildren {
    var factory: Factory
}

val FactoryHistoryContextProvider = FC<FactoryHistoryContextProviderProps>("FactoryHistoryContextProvider") { props ->
    val factoryHistoryState = useState(FactoryHistory(listOf(props.factory), 0))

//    var factoryHistory by factoryHistoryState
//    useEffect(props.factory.id.toInt()) {
//        println("RESET: ${props.factory.id}")
//        factoryHistory = FactoryHistory(listOf(props.factory), 0)
//    }

    FactoryHistoryContext(factoryHistoryState) { +props.children }
}
