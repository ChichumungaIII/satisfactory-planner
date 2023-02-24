package app.v2.factory

import app.v2.AppScope
import app.v2.data.Factory
import app.v2.data.service.FactoryServiceContext
import app.v2.data.FactoryStoreContext
import app.v2.data.LoadState
import app.v2.data.SetFactory
import kotlinx.coroutines.launch
import react.FC
import react.PropsWithChildren
import react.ReducerInstance
import react.createContext
import react.useContext
import react.useEffect
import react.useReducer

sealed interface FactoryContextAction
data class SetFactoryId(val id: ULong) : FactoryContextAction
private data class SetState(val state: LoadState<ULong, Factory>) : FactoryContextAction

val FactoryContext = createContext<ReducerInstance<LoadState<ULong, Factory>, FactoryContextAction>>()

val FactoryContextProvider = FC<PropsWithChildren> { props ->
    val factoryService = useContext(FactoryServiceContext)
    val (store, updateStore) = useContext(FactoryStoreContext)

    val factoryContext = useReducer<LoadState<ULong, Factory>, FactoryContextAction>(
        { state, action ->
            when (action) {
                is SetState -> action.state
                is SetFactoryId -> {
                    if (state.id == action.id) state
                    else store[action.id]?.let { LoadState.loaded(action.id, it) } ?: LoadState.request(action.id)
                }
            }
        }, initialState = LoadState.empty()
    )
    val (state, updateState) = factoryContext
    useEffect(state) {
        if (state is LoadState.Requested) {
            updateState(SetState(LoadState.loading(state.id)))
            AppScope.launch {
                val next =
                    try {
                        val factory = factoryService.getFactory(state.id)
                        updateStore(SetFactory(factory))
                        LoadState.loaded(state.id, factory)
                    } catch (e: IllegalArgumentException) {
                        LoadState.failure(state.id, e.message ?: "There was an error loading Factory #${state.id}")
                    }
                updateState(SetState(next))
            }
        }
    }

    FactoryContext(factoryContext) { +props.children }
}
