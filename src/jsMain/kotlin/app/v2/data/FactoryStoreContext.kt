package app.v2.data

import react.FC
import react.PropsWithChildren
import react.ReducerInstance
import react.createContext
import react.useReducer

val FactoryStoreContext = createContext<ReducerInstance<Map<ULong, Factory>, FactoryStoreAction>>()

sealed interface FactoryStoreAction
data class SetFactories(val factories: List<Factory>) : FactoryStoreAction
data class SetFactory(val factory: Factory) : FactoryStoreAction

val FactoryStoreContextProvider = FC<PropsWithChildren> { props ->
    val store = useReducer<Map<ULong, Factory>, FactoryStoreAction>({ state, action ->
        val next = state.toMutableMap()
        when (action) {
            is SetFactories -> next.putAll(action.factories.associateBy { it.id })
            is SetFactory -> next[action.factory.id] = action.factory
        }
        next.toMap()
    }, initialState = mapOf())

    FactoryStoreContext(store) { +props.children }
}
