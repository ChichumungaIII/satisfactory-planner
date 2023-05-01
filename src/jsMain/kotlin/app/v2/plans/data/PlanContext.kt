package app.v2.plans.data

import app.v2.AppScope
import app.v2.data.LoadState
import app.v2.plans.data.model.Plan
import app.v2.plans.data.model.PlanServiceContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import react.FC
import react.PropsWithChildren
import react.ReducerInstance
import react.createContext
import react.useContext
import react.useReducer

sealed interface PlanContextAction
data class GetPlan(val id: ULong) : PlanContextAction
data class SavePlan(val plan: Plan) : PlanContextAction
private data class SetPlan(val plan: Plan) : PlanContextAction

val PlanContext = createContext<ReducerInstance<LoadState<ULong, Plan>, PlanContextAction>>()

private var planToSave: Plan? = null

val PlanContextProvider = FC<PropsWithChildren>("PlanContextProvider") { props ->
  val planService = useContext(PlanServiceContext)
  val (store, updateStore) = useContext(PlanStoreContext)
  val (_, updatePlans) = useContext(PlansListContext)

  var updatePlan: (PlanContextAction) -> Unit = { throw Error("updatePlan callback not bound") }
  val planContext = useReducer<LoadState<ULong, Plan>, PlanContextAction>(
    { state, action ->
      when (action) {
        is GetPlan ->
          state.takeIf { it.id == action.id }
            ?: store[action.id]?.let { LoadState.loaded(action.id, it) }
            ?: LoadState.loading<ULong, Plan>(action.id).also {
              AppScope.launch {
                val plan = planService.get(action.id)
                updateStore(StorePlan(plan))
                updatePlan(SetPlan(plan))
              }
            }

        is SavePlan -> {
          planToSave = action.plan.also { plan ->
            AppScope.launch {
              delay(100)
              if (plan == planToSave) {
                planService.update(plan)
                updateStore(StorePlan(plan))
                updatePlans(ReplacePlan(plan))
              }
            }
          }
          LoadState.loaded(action.plan.id, action.plan)
            .takeUnless { it.id != state.id }
            ?: state
        }

        is SetPlan ->
          LoadState.loaded(action.plan.id, action.plan)
            .takeUnless { it.id != state.id }
            ?: state
      }
    },
    initialState = LoadState.empty()
  )
  updatePlan = planContext.component2()

  PlanContext(planContext) { +props.children }
}
