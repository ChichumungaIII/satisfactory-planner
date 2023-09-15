package app.redux

import app.redux.state.AppState
import react.FC
import react.PropsWithChildren
import react.redux.Provider
import react.redux.useDispatch
import redux.RAction
import redux.WrapperAction
import redux.compose
import redux.createStore
import redux.rEnhancer

interface AppAction : RAction {
  fun AppState.update(): AppState
}

private val AppStore = createStore(
  reducer = { state, action ->
    when (action) {
      is AppAction -> with(action) { state.update() }
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
