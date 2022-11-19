package app.factory.unit

import app.data.u6.U6Building
import app.factory.model.ProductionBuilding
import app.util.PropsDelegate
import csstype.ClassName
import mui.material.Box
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props

external interface ProductionBuildingComponentProps : Props {
    var production: ProductionBuilding
    var setProduction: (ProductionBuilding) -> Unit
}

val ProductionBuildingComponent = FC<ProductionBuildingComponentProps>("ProductionBuildingComponent") { props ->
    var production by PropsDelegate(props.production) { props.setProduction(it) }

    Box {
        className = ClassName("production-building production-building--${production.building.className}")

        Typography {
            className = ClassName("production-building__title")
            variant = TypographyVariant.body1

            +production.building.displayName
        }
    }
}

private inline val U6Building.className: String get() = name.lowercase()
