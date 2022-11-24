package app.factory.unit

import app.common.RationalInput
import app.common.RationalValidation.Companion.fail
import app.common.RationalValidation.Companion.pass
import app.common.input.RecipeAutocomplete
import app.factory.model.FactoryUnit
import app.factory.model.ProductionBuilding
import csstype.ClassName
import react.FC
import react.Props
import react.ReactNode
import util.math.q

private val CLOCK_INPUT_SCALE = 100.q
private val MAX_CLOCK_VALUE = 250.q

external interface ProductionBuildingComponentProps : Props {
    var production: ProductionBuilding
    var setUnit: (FactoryUnit) -> Unit
}

val ProductionBuildingComponent = FC<ProductionBuildingComponentProps>("ProductionBuildingComponent") { props ->
    FactoryUnitComponent {
        unit = props.production
        setUnit = { props.setUnit(it) }

        RecipeAutocomplete {
            className = ClassName("production-building__recipe-input")

            recipe = props.production.recipe
            setRecipe = { props.setUnit(props.production.copy(recipe = it)) }

            building = props.production.building
        }

        RationalInput {
            className = ClassName("production-building__clock-input")
            label = ReactNode("Clock speed")

            value = props.production.clock * CLOCK_INPUT_SCALE
            setValue = { next -> next?.let { props.setUnit(props.production.copy(clock = it / CLOCK_INPUT_SCALE)) } }

            validators = listOf(
                { value ->
                    if (value >= 0.q) pass()
                    else fail("Clock speed must be positive.")
                },
                { value ->
                    if (value <= MAX_CLOCK_VALUE) pass()
                    else fail("Clock speed cannot exceed 250%.")
                })
        }
    }
}
