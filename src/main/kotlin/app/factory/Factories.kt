package app

import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props

external interface FactoriesProps : Props

val Factories = FC<FactoriesProps> { _ ->
    Typography {
        variant = TypographyVariant.h2

        +"Factories"
    }
}
