package app.v2.factories

import app.v2.AppScope
import app.v2.data.Factory
import app.v2.data.FactoryServiceContext
import app.v2.data.FactoryStoreContext
import app.v2.data.LoadState
import app.v2.data.SetFactories
import kotlinx.coroutines.launch
import react.FC
import react.PropsWithChildren
import react.ReducerInstance
import react.createContext
import react.useContext
import react.useEffect
import react.useReducer

sealed interface FactoriesContextAction
object LoadList : FactoriesContextAction
data class AddFactory(val factory: Factory) : FactoriesContextAction
data class DeleteFactory(val factoryId: ULong) : FactoriesContextAction
private data class SetState(val state: LoadState<Unit, List<Factory>>) : FactoriesContextAction

val FactoriesContext = createContext<ReducerInstance<LoadState<Unit, List<Factory>>, FactoriesContextAction>>()

val FactoriesContextProvider = FC<PropsWithChildren> { props ->
    val factoryService = useContext(FactoryServiceContext)
    val (_, updateStore) = useContext(FactoryStoreContext)

    val factoriesContext = useReducer<LoadState<Unit, List<Factory>>, FactoriesContextAction>(
        { state, action ->
            when (action) {
                is SetState -> action.state
                LoadList -> state.takeIf { it is LoadState.Requested } ?: LoadState.request(Unit)

                is AddFactory -> when (state) {
                    is LoadState.Loaded -> LoadState.loaded(Unit, state.data + action.factory)
                    else -> state
                }

                is DeleteFactory -> when (state) {
                    is LoadState.Loaded -> {
                        AppScope.launch { factoryService.deleteFactory(action.factoryId) }
                        LoadState.loaded(Unit, state.data.filterNot { it.id == action.factoryId })
                    }

                    else -> state
                }

            }
        }, initialState = LoadState.empty()
    )
    val (state, updateState) = factoriesContext
    useEffect(state) {
        if (state is LoadState.Requested) {
            updateState(SetState(LoadState.loading(state.id)))
            AppScope.launch {
                val next = try {
                    val factories = factoryService.listFactories()
                    updateStore(SetFactories(factories))
                    LoadState.loaded(Unit, factories)
                } catch (e: IllegalArgumentException) {
                    LoadState.failure(Unit, e.message ?: "There was an error loading the Factory list.")
                }
                updateState(SetState(next))
            }
        }
    }

    FactoriesContext(factoriesContext) { +props.children }
}
