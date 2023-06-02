package app.v2.factory

import app.v2.AppScope
import app.v2.api.factory.FactoryServiceContext
import app.v2.data.Factory
import app.v2.data.FactoryStoreContext
import app.v2.data.LoadState
import app.v2.data.SetFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import react.FC
import react.PropsWithChildren
import react.ReducerInstance
import react.createContext
import react.useContext
import react.useReducer

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

  var updateFactory: (FactoryContextAction) -> Unit = { throw Error("updateFactory callback not bound") }
  val factoryContext = useReducer<LoadState<ULong, Factory>, FactoryContextAction>(
    { state, action ->
      when (action) {
        is SetFactoryId -> {
          if (state.id == action.id) state
          else store[action.id]?.let { LoadState.loaded(action.id, it) }
            ?: LoadState.loading<ULong, Factory>(action.id).also {
              AppScope.launch {
                val next =
                  try {
                    val factory = factoryService.getFactory(action.id)
                    updateStore(SetFactory(factory))
                    LoadState.loaded(action.id, factory)
                  } catch (e: IllegalArgumentException) {
                    LoadState.failure(
                      action.id,
                      e.message ?: "There was an error loading Factory #${action.id}"
                    )
                  }
                updateFactory(SetState(next))
              }
            }
        }

        is SaveFactory -> {
          val factory = action.factory
          debouncedSaveFactory(factory)

          if (state.id == factory.id) LoadState.loaded(factory.id, factory)
          else state
        }

        is SetState -> action.state
      }
    }, initialState = LoadState.empty()
  )
  updateFactory = factoryContext.component2()

  FactoryContext(factoryContext) { +props.children }
}
