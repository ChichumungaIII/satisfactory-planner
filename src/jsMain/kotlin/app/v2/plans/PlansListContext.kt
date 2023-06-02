package app.v2.plans

import app.v2.AppScope
import app.v2.api.plan.PlanServiceContext
import app.v2.data.common.LoadState
import app.v2.data.common.LoadState.Loaded
import app.v2.data.common.LoadState.Loading
import app.v2.data.plan.Plan
import app.v2.data.plan.PlanStoreContext
import app.v2.data.plan.StorePlans
import kotlinx.coroutines.launch
import react.FC
import react.PropsWithChildren
import react.ReducerInstance
import react.createContext
import react.useContext
import react.useReducer

sealed interface PlansListContextAction
object LoadPlansList : PlansListContextAction
data class AppendPlan(val plan: Plan) : PlansListContextAction
data class ReplacePlan(val plan: Plan) : PlansListContextAction
data class RemovePlan(val id: ULong) : PlansListContextAction
private data class SetPlansList(val plans: List<Plan>) : PlansListContextAction

val PlansListContext = createContext<ReducerInstance<LoadState<Unit, List<Plan>>, PlansListContextAction>>()

val PlansListContextProvider = FC<PropsWithChildren>("PlansListContextProvider") { props ->
  val planService = useContext(PlanServiceContext)
  val (_, updateStore) = useContext(PlanStoreContext)

  var updatePlans: (PlansListContextAction) -> Unit = { throw Error("Can't get away with it.") }
  val plansListContext = useReducer<LoadState<Unit, List<Plan>>, PlansListContextAction>({ state, action ->
    when (action) {
      LoadPlansList -> state.takeIf { it is Loading } ?: LoadState.loading<Unit, List<Plan>>(Unit).also {
        AppScope.launch {
          planService.list().also { plans ->
            updateStore(StorePlans(plans))
            updatePlans(SetPlansList(plans))
          }
        }
      }

      is AppendPlan -> when (state) {
        is Loaded -> LoadState.loaded(Unit, state.data + action.plan)
        else -> throw IllegalStateException("Cannot append a plan to the list prior to loading.")
      }

      is ReplacePlan -> when (state) {
        is Loaded -> LoadState.loaded(Unit, state.data.map { it.takeUnless { it.id == action.plan.id } ?: action.plan })
        else -> state
      }

      is RemovePlan -> when (state) {
        is Loaded -> {
          AppScope.launch { planService.delete(action.id) }
          LoadState.loaded(Unit, state.data.filterNot { it.id == action.id })
        }

        else -> throw IllegalStateException("Cannot remove a plan from the list prior to loading.")
      }

      is SetPlansList -> LoadState.loaded(Unit, action.plans)
    }
  }, initialState = LoadState.empty())
  updatePlans = plansListContext.component2()


  PlansListContext(plansListContext) { +props.children }
}
