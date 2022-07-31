package app.factory

import app.Header
import react.FC
import react.Props

external interface FactoriesProps : Props

val Factories = FC<FactoriesProps> { _ ->
    Header {
        title = "Factories"
    }
}
