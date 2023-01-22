package app.v2.factories

import app.v2.common.layout.ZeroStateComponent
import csstype.ClassName
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props

external interface FactoriesComponentProps : Props

val FactoriesComponent = FC<FactoriesComponentProps>("FactoriesComponent") { props ->
    ZeroStateComponent {
        Typography {
            variant = TypographyVariant.subtitle1
            +"It doesn't look like you've made any factories yet."
        }
        Button {
            className = ClassName("factories__create-button")

            variant = ButtonVariant.contained
            +"Create a factory"
        }
    }
}
