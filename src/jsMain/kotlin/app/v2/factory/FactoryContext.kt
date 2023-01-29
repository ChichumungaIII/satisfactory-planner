package app.v2.factory

import app.v2.data.Factory
import app.v2.data.FactoryServiceContext
import app.v2.data.FactoryStoreContext
import app.v2.data.Failure
import app.v2.data.Loadable
import app.v2.data.Loaded
import app.v2.data.Loading
import app.v2.data.SetFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.FC
import react.PropsWithChildren
import react.ReducerInstance
import react.createContext
import react.useContext
import react.useEffect
import react.useReducer

private val Scope = MainScope()

val FactoryContext = createContext<ReducerInstance<Loadable<Factory>, FactoryContextAction>>()

private sealed interface InternalFactoryContextAction
private data class SetState(val state: FactoryContextState) : InternalFactoryContextAction

sealed interface FactoryContextAction
data class SetFactoryId(val id: ULong) : FactoryContextAction, InternalFactoryContextAction
private object InternalSync : FactoryContextAction

private interface FactoryContextState {
    val id: ULong?
    val loadable: Loadable<Factory>
}

private object Uninitialized : FactoryContextState {
    override val id = null
    override val loadable = Loading<Factory>(false)
}

private data class FactorySpecified(override val id: ULong) : FactoryContextState {
    override val loadable = Loading<Factory>(false)
}

private data class FactoryLoading(override val id: ULong) : FactoryContextState {
    override val loadable = Loading<Factory>(true)
}

private data class FactoryLoaded(val factory: Factory) : FactoryContextState {
    override val id = factory.id
    override val loadable = Loaded(factory)
}

private data class FactoryError(override val id: ULong, val error: String) : FactoryContextState {
    override val loadable = Failure<Factory>(error)
}

val FactoryContextProvider = FC<PropsWithChildren> { props ->
    val factoryService = useContext(FactoryServiceContext)
    val (store, updateStore) = useContext(FactoryStoreContext)

    val (internal, updateInternal) = useReducer<FactoryContextState, InternalFactoryContextAction>(
        { internal, action ->
            when (action) {
                is SetState -> action.state
                is SetFactoryId -> {
                    if (internal.id == action.id) internal
                    else store[action.id]?.let { FactoryLoaded(it) } ?: FactorySpecified(action.id)
                }
            }
        }, initialState = Uninitialized
    )
    useEffect(internal) {
        if (internal is FactorySpecified) {
            updateInternal(SetState(FactoryLoading(internal.id)))
            Scope.launch {
                val next =
                    try {
                        val factory = factoryService.getFactory(internal.id)
                        updateStore(SetFactory(factory))
                        FactoryLoaded(factory)
                    } catch (e: IllegalArgumentException) {
                        FactoryError(internal.id, e.message ?: "There was an error loading Factory #${internal.id}")
                    }
                updateInternal(SetState(next))
            }
        }
    }

    val factoryContext = useReducer<Loadable<Factory>, FactoryContextAction>({ factory, action ->
        when (action) {
            is InternalFactoryContextAction -> internal.loadable.also { updateInternal(action) }
            is InternalSync -> internal.loadable
        }
    }, initialState = internal.loadable)
    val (_, updateFactory) = factoryContext
    useEffect(internal) { updateFactory(InternalSync) }

    FactoryContext(factoryContext) { +props.children }
}
