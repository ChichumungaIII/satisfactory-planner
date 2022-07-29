package app

import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props

external interface ProductionPlansProps : Props

val ProductionPlans = FC<ProductionPlansProps> { _ ->
    Typography {
        variant = TypographyVariant.h2

        +"Production Plans"
    }
}
