package app.v2.plans

import app.AppRoute
import app.v2.AppScope
import app.v2.common.layout.AppTitleComponent
import app.v2.common.layout.FrameComponent
import app.v2.common.layout.TitleBarComponent
import app.v2.common.layout.ZeroStateComponent
import app.v2.data.LoadState.Loaded
import app.v2.data.LoadState.Loading
import app.v2.plans.data.AppendPlan
import app.v2.plans.data.LoadPlansList
import app.v2.plans.data.PlanStoreContext
import app.v2.plans.data.PlansListContext
import app.v2.plans.data.RemovePlan
import app.v2.plans.data.StorePlan
import app.v2.plans.data.model.PlanServiceContext
import kotlinx.coroutines.launch
import mui.material.Backdrop
import mui.material.CircularProgress
import react.FC
import react.Props
import react.create
import react.router.useNavigate
import react.useContext
import react.useState

external interface PlansRouteComponentProps : Props

val PlansRouteComponent = FC<PlansRouteComponentProps>("PlansRouteComponent") {
    val navigate = useNavigate()
    val planService = useContext(PlanServiceContext)
    val (_, updatePlanStore) = useContext(PlanStoreContext)
    val (plans, updatePlans) = useContext(PlansListContext)

    var creating by useState(false)

    FrameComponent {
        titleBar = TitleBarComponent.create {
            title = AppTitleComponent.create { title = "Plans" }
        }
        when (plans) {
            is Loading -> ZeroStateComponent {
                CircularProgress { size = 80; thickness = 4.8 }
            }

            is Loaded -> PlansListComponent {
                this.plans = plans.data
                onCreate = { plan ->
                    creating = true
                    AppScope.launch {
                        planService.create(plan)
                        updatePlanStore(StorePlan(plan))
                        updatePlans(AppendPlan(plan))
                        navigate(AppRoute.PLAN.url("planId" to "${plan.id}"))
                    }
                }
                onDelete = { id ->
                    updatePlans(RemovePlan(id))
                }
            }

            else -> updatePlans(LoadPlansList)
        }

        Backdrop {
            open = creating
            CircularProgress { size = 80; thickness = 4.8 }
        }
    }
}
