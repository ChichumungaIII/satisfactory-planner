package app.v2.plans

import app.v2.common.layout.AppTitleComponent
import app.v2.common.layout.FrameComponent
import app.v2.common.layout.TitleBarComponent
import app.v2.common.layout.ZeroStateComponent
import app.v2.data.LoadState.Loaded
import app.v2.data.LoadState.Loading
import app.v2.plans.data.LoadPlansList
import app.v2.plans.data.PlansListContext
import mui.material.CircularProgress
import react.FC
import react.Props
import react.create
import react.useContext

external interface PlansRouteComponentProps : Props

val PlansRouteComponent = FC<PlansRouteComponentProps>("PlansRouteComponent") {
    val (plans, updatePlans) = useContext(PlansListContext)

    FrameComponent {
        titleBar = TitleBarComponent.create {
            title = AppTitleComponent.create { title = "Plans" }
        }
        content = when (plans) {
            is Loading -> ZeroStateComponent.create {
                CircularProgress { size = 80; thickness = 4.8 }
            }

            is Loaded -> PlansListComponent.create {
                this.plans = plans.data
            }

            else -> null.also { updatePlans(LoadPlansList) }
        }
    }
}
