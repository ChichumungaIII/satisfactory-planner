package app.v2.plans.plan

import app.api.OptimizeRequest
import app.api.client.optimize
import app.data.recipe.ProductionRecipe
import app.v2.AppScope
import app.v2.plans.data.PlanContext
import app.v2.plans.data.SavePlan
import app.v2.plans.data.model.Plan
import app.v2.plans.data.model.PlanInput
import app.v2.plans.data.model.PlanProduct
import app.v2.plans.data.model.PlanResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import react.FC
import react.PropsWithChildren
import react.ReducerInstance
import react.createContext
import react.useContext
import react.useReducer
import kotlin.time.Duration.Companion.milliseconds

sealed interface ComputeOutcomeContextAction
data class ForPlan(val plan: Plan) : ComputeOutcomeContextAction

val ComputeOutcomeContext = createContext<ReducerInstance<Unit, ComputeOutcomeContextAction>>()

private val DEBOUNCE = 500.milliseconds

private var current: Plan? = null
private var latest: OptimizeRequest? = null

val ComputeOutcomeContextComponent = FC<PropsWithChildren>("CreateOutcomeContextComponent") {
  val (_, updatePlan) = useContext(PlanContext)

  val outcome = useReducer<Unit, ComputeOutcomeContextAction>({ _, action ->
    when (action) {
      is ForPlan -> {
        val plan = action.plan
        current = plan

        AppScope.launch {
          delay(DEBOUNCE)
          if (plan != current) return@launch

          val request = OptimizeRequest(
            recipes = ProductionRecipe.values().toSet(),
            inputs = plan.inputs.mapNotNull(PlanInput::toInput),
            outcomes = plan.products.mapNotNull(PlanProduct::toOutcome),
          )
          if (request == latest) return@launch
          latest = request

          val response = optimize(request)
          if (plan != current) return@launch

          val results = ProductionRecipe.values()
            .associateWith { response.outcome[it] }
            .mapNotNull { (recipe, rate) -> rate?.let { recipe to it } }
            .map { (recipe, rate) ->
              PlanResult(recipe, rate, details = action.plan.getResult(recipe)?.details ?: false)
            }
          val computedPlan = action.plan.copy(
            results = results,
            minimums = response.minimums,
            maximums = response.maximums,
          )
          updatePlan(SavePlan(computedPlan))
        }
      }
    }
  }, initialState = Unit)

  ComputeOutcomeContext(outcome) {
    +it.children
  }
}
