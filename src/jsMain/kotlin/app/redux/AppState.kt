package app.redux

import redux.RAction

data class AppState(
  val counter: Int = 0,
)

data object Increment : RAction
data object Decrement : RAction
