package app.v2.factory.content

import app.util.PropsDelegate
import app.v2.common.input.BUILDINGS
import app.v2.common.input.BuildingAutocomplete
import app.v2.common.input.ClockSpeedInput
import app.v2.common.input.RecipeAutocomplete
import app.v2.data.FactoryLeaf
import app.v2.data.FactoryNode
import app.v2.data.FactoryTree
import csstype.ClassName
import mui.icons.material.ArrowCircleRightOutlined
import mui.icons.material.DoubleArrow
import mui.material.*
import react.FC
import react.Props
import react.ReactNode

external interface FactoryBuildingComponentProps : Props {
    var settings: FactoryLeaf
    var setNode: (FactoryNode) -> Unit
}

val FactoryBuildingComponent = FC<FactoryBuildingComponentProps>("FactoryBuildingComponent") { props ->
    var settings by PropsDelegate(props.settings, props.setNode)
    val (building, recipe, clock) = settings

    Box {
        className = ClassName("factory-building")

        BuildingAutocomplete {
            model = building
            setModel = { next ->
                settings = settings.copy(
                    building = next,
                    recipe = next?.let { new -> recipe?.takeIf { new.recipes.contains(it) } })
            }
        }

        RecipeAutocomplete {
            model = recipe
            setModel = { next ->
                settings = settings.copy(
                    recipe = next,
                    building = building ?: next?.let {
                        BUILDINGS.associateWith { it.recipes }.filterValues { it.contains(next) }.keys.singleOrNull()
                    })
            }

            this.building = building
        }

        ClockSpeedInput {
            model = clock
            setModel = { next -> settings = settings.copy(clock = next) }
        }

        Tooltip {
            className = ClassName("factory-building__action")
            title = ReactNode("Move Into Group")

            IconButton {
                color = IconButtonColor.default
                size = Size.small
                DoubleArrow {}

                onClick = { props.setNode(FactoryTree(listOf(settings))) }
            }
        }
    }
}
