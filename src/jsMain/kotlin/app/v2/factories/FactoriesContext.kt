package app.v2.factories

import app.v2.AppScope
import app.v2.api.factory.FactoryServiceContext
import app.v2.data.common.LoadState
import app.v2.data.common.LoadState.Loading
import app.v2.data.factory.Factory
import app.v2.data.factory.FactoryStoreContext
import app.v2.data.factory.SetFactories
import kotlinx.coroutines.launch
import react.FC
import react.PropsWithChildren
import react.ReducerInstance
import react.createContext
import react.useContext
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

  var updateFactories: (FactoriesContextAction) -> Unit = { throw Error("updateFactories callback not bound") }
  val factoriesContext = useReducer<LoadState<Unit, List<Factory>>, FactoriesContextAction>({ state, action ->
    when (action) {
      LoadList -> state.takeIf { it is Loading } ?: LoadState.loading<Unit, List<Factory>>(Unit).also {
        AppScope.launch {
          val next = try {
            val factories = factoryService.listFactories()
            updateStore(SetFactories(factories))
            LoadState.loaded(Unit, factories)
          } catch (e: IllegalArgumentException) {
            LoadState.failure(Unit, e.message ?: "There was an error loading the Factory list.")
          }
          updateFactories(SetState(next))
        }
      }

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

      is SetState -> action.state
    }
  }, initialState = LoadState.empty())
  updateFactories = factoriesContext.component2()

  FactoriesContext(factoriesContext) { +props.children }
}
