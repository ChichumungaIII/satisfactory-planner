package app.v2.factory

import app.v2.AppScope
import app.v2.data.Factory
import app.v2.data.FactoryStoreContext
import app.v2.data.LoadState
import app.v2.data.SetFactory
import app.v2.data.service.FactoryServiceContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import react.*

sealed interface FactoryContextAction
data class SetFactoryId(val id: ULong) : FactoryContextAction
data class SaveFactory(val factory: Factory) : FactoryContextAction
private data class SetState(val state: LoadState<ULong, Factory>) : FactoryContextAction

val FactoryContext = createContext<ReducerInstance<LoadState<ULong, Factory>, FactoryContextAction>>()

private var factoryToSave: Factory? = null

val FactoryContextProvider = FC<PropsWithChildren> { props ->
    val factoryService = useContext(FactoryServiceContext)
    val (store, updateStore) = useContext(FactoryStoreContext)

    fun debouncedSaveFactory(factory: Factory) {
        factoryToSave = factory
        AppScope.launch {
            delay(100)
            if (factory == factoryToSave) {
                updateStore(SetFactory(factory))
                factoryService.updateFactory(factory)
            }
        }
    }

    val factoryContext = useReducer<LoadState<ULong, Factory>, FactoryContextAction>(
        { state, action ->
            when (action) {
                is SetState -> action.state

                is SetFactoryId -> {
                    if (state.id == action.id) state
                    else store[action.id]?.let { LoadState.loaded(action.id, it) } ?: LoadState.request(action.id)
                }

                is SaveFactory -> {
                    val factory = action.factory
                    debouncedSaveFactory(factory)

                    if (state.id == factory.id) LoadState.loaded(factory.id, factory)
                    else state
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
