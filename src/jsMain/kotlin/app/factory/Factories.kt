package app.factory

import app.Header
import app.NavMenu
import react.FC
import react.Props
import react.useState

external interface FactoriesProps : Props

val Factories = FC<FactoriesProps>("Factories") { _ ->
    var isOpen by useState(false)

    Header {
        this.title = "Factories"
        this.isOpen = isOpen
        this.toggle = { isOpen = !isOpen }
    }

    NavMenu {
        this.isOpen = isOpen
        this.close = { isOpen = false }
    }
}
