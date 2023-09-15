package app.redux

import app.redux.state.AppState
import react.FC
import react.PropsWithChildren
import react.redux.Provider
import react.redux.useDispatch
import react.redux.useSelector
import redux.RAction
import redux.WrapperAction
import redux.compose
import redux.createStore
import redux.rEnhancer

abstract class AppAction : RAction {
  abstract fun AppState.update(): AppState
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

fun <R> useAppSelector(selector: (AppState) -> R) = useSelector(selector)
fun <R> useAppSelector(selector: (AppState) -> R, equalityFn: (R, R) -> Boolean) = useSelector(selector, equalityFn)
fun useAppDispatch() = useDispatch<RAction, WrapperAction>()
