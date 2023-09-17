package app.redux.state.optimization

import app.redux.AppAction
import app.redux.state.AppState
import kotlinx.coroutines.Job

data class Optimizations(
  val requests: Map<Long, Job> = mapOf(),
) {
  operator fun get(id: Long) = requests[id]
}

abstract class OptimizationsAction : AppAction() {
  override fun AppState.update() = copy(optimizations = with(optimizations) { update() })
  abstract fun Optimizations.update(): Optimizations
}

class RegisterOptimizationRequest(val id: Long, val request: Job) : OptimizationsAction() {
  override fun Optimizations.update() = copy(requests = requests + (id to request))
}

