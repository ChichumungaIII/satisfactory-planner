package app.redux

import app.redux.state.AppState
import js.core.jso
import redux.RAction
import redux.WrapperAction
import redux.applyMiddleware

interface RThunk : RAction {
  operator fun invoke(
    dispatch: (RAction) -> WrapperAction,
    getState: () -> AppState,
  ): Unit
}

fun rThunk() = applyMiddleware<AppState, RAction, WrapperAction, RAction, WrapperAction>(
  { store ->
    { next ->
      { action ->
        if (action is RThunk) jso<WrapperAction>().also { action(store::dispatch, store::getState) }
        else next(action)
      }
    }
  })
