package app.factory.unit

import app.common.RationalInput
import app.common.RationalValidation.Companion.fail
import app.common.RationalValidation.Companion.pass
import app.common.input.RecipeAutocomplete
import app.data.u6.U6Building
import app.factory.model.ProductionBuilding
import app.util.PropsDelegate
import csstype.ClassName
import mui.icons.material.ExpandMore
import mui.material.Accordion
import mui.material.AccordionDetails
import mui.material.AccordionSummary
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.useState
import util.math.q

private val CLOCK_INPUT_SCALE = 100.q
private val MAX_CLOCK_VALUE = 250.q

external interface ProductionBuildingComponentProps : Props {
    var production: ProductionBuilding
    var setProduction: (ProductionBuilding) -> Unit
}

val ProductionBuildingComponent = FC<ProductionBuildingComponentProps>("ProductionBuildingComponent") { props ->
    var production by PropsDelegate(props.production) { props.setProduction(it) }
    var open by useState(true)

    Accordion {
        className = ClassName(
            """factory-unit
                | production-building
                | production-building--${production.building.className}""".trimMargin()
        )
        disableGutters = true

        expanded = open
        onChange = { _, next -> open = next }

        AccordionSummary {
            expandIcon = ExpandMore.create {}

            Typography {
                className = ClassName("factory-unit__title")
                variant = TypographyVariant.body1

                +production.building.displayName
            }
        }

        AccordionDetails {
            RecipeAutocomplete {
                className = ClassName("production-building__recipe-input")

                recipe = production.recipe
                setRecipe = { production = production.copy(recipe = it) }

                building = production.building
            }

            RationalInput {
                className = ClassName("production-building__clock-input")
                label = ReactNode("Clock speed")

                value = production.clock * CLOCK_INPUT_SCALE
                setValue = { next -> next?.let { production = production.copy(clock = it / CLOCK_INPUT_SCALE) } }

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
}

private inline val U6Building.className: String get() = name.lowercase()
