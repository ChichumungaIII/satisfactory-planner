package app.v2.plan

import app.v2.common.layout.AppTitleComponent
import app.v2.common.layout.FrameComponent
import app.v2.common.layout.TitleBarComponent
import js.core.get
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.create
import react.router.useParams

external interface PlanRouteComponentProps : Props

val PlanRouteComponent = FC<PlanRouteComponentProps>("PlanRouteComponent") { props ->
    val planId = useParams()["planId"]!!.toULong()

    FrameComponent {
        titleBar = TitleBarComponent.create {
            title = AppTitleComponent.create { title = "Plans" }
        }
        content = Typography.create {
            variant = TypographyVariant.h1
            +"$planId"
        }
    }
}
