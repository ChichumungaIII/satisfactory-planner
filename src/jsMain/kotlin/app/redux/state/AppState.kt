package app.redux.state

import app.redux.state.optimization.Optimizations
import app.redux.state.resource.ResourceCache
import app.redux.state.resource.plan.PlanCache
import app.redux.state.resource.save.SaveCache

data class AppState(
  val saveCache: SaveCache = ResourceCache(),
  val planCache: PlanCache = ResourceCache(),
  val optimizations: Optimizations = Optimizations()
)
