package app.common.layout

import app.Header
import app.HeaderSpacer
import app.NavMenu
import react.FC
import react.PropsWithChildren
import react.useState

external interface RootComponentProps : PropsWithChildren {
    var title: String
}

val RootComponent = FC<RootComponentProps>("RootComponent") { props ->
    var headerOpen by useState(false)

    Header {
        title = props.title
        isOpen = headerOpen
        toggle = { headerOpen = !headerOpen }
    }

    NavMenu {
        isOpen = headerOpen
        close = { headerOpen = false }
    }

    HeaderSpacer {
        isOpen = headerOpen
        +props.children
    }
}
