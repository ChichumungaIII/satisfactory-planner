package app.redux

import react.FC
import react.PropsWithChildren
import react.redux.Provider
import react.redux.useDispatch
import redux.RAction
import redux.WrapperAction
import redux.compose
import redux.createStore
import redux.rEnhancer

private val AppStore = createStore(
  reducer = { state, action ->
    when (action) {
      is Increment -> state.copy(counter = state.counter + 1)
      is Decrement -> state.copy(counter = state.counter - 1)
      else -> state
    }
  },
  preloadedState = AppState(),
  enhancer = compose(rThunk(), rEnhancer()),
)

val AppStoreProvider = FC<PropsWithChildren>("SatisfactoryStore") {
  Provider {
    store = AppStore
    +it.children
  }
}

fun useAppDispatch() = useDispatch<RAction, WrapperAction>()
