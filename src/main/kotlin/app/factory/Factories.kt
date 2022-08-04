package app.factory

import app.Header
import react.FC
import react.Props

external interface FactoriesProps : Props

val Factories = FC<FactoriesProps>("Factories") { _ ->
    Header {
        title = "Factories"
    }
}
