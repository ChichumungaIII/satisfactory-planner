package app.plan

import app.Header
import app.HeaderSpacer
import app.NavMenu
import react.FC
import react.Props
import react.useState

external interface PlansRouteProps : Props

val PlansRoute = FC<PlansRouteProps>("PlansRoute") { _ ->
    var isOpen by useState(false)

    Header {
        this.title = "Production Plans"
        this.isOpen = isOpen
        this.toggle = { isOpen = !isOpen }
    }

    NavMenu {
        this.isOpen = isOpen
        this.close = { isOpen = false }
    }

    HeaderSpacer {
        this.isOpen = isOpen
        Plans {}
    }
}
