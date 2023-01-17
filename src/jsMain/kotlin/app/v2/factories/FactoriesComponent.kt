package app.v2.factories

import csstype.ClassName
import mui.material.Box
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props

external interface FactoriesComponentProps : Props

val FactoriesComponent = FC<FactoriesComponentProps>("FactoriesComponent") { props ->
    Box {
        className = ClassName("factories__empty")

        Typography {
            variant = TypographyVariant.subtitle1
            +"It doesn't look like you've made any factories yet."
        }
        Button {
            className = ClassName("factories__empty__create-button")

            variant = ButtonVariant.contained
            +"Create a factory"
        }
    }
}
