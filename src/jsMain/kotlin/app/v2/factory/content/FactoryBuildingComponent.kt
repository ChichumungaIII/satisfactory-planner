package app.v2.factory.content

import app.util.PropsDelegate
import app.v2.common.input.BUILDINGS
import app.v2.common.input.BuildingAutocomplete
import app.v2.common.input.ClockSpeedInput
import app.v2.common.input.CountInput
import app.v2.common.input.FactoryItemRateInput
import app.v2.common.input.RecipeAutocomplete
import app.v2.common.input.ToggleIconButton
import app.v2.common.layout.ControlBar
import app.v2.data.FactoryLeaf
import app.v2.data.FactoryNode
import app.v2.data.FactoryTree
import csstype.ClassName
import csstype.px
import mui.icons.material.ArrowForward
import mui.icons.material.ExpandLess
import mui.icons.material.ExpandMore
import mui.icons.material.Repeat
import mui.icons.material.RepeatOn
import mui.icons.material.Segment
import mui.material.Box
import mui.material.IconButton
import mui.material.IconButtonColor
import mui.material.Size
import mui.material.Stack
import mui.material.SvgIconSize
import mui.material.Tooltip
import mui.system.responsive
import react.FC
import react.Props
import react.ReactNode
import util.math.q

external interface FactoryBuildingComponentProps : Props {
    var settings: FactoryLeaf
    var setNode: (FactoryNode) -> Unit
}

val FactoryBuildingComponent = FC<FactoryBuildingComponentProps>("FactoryBuildingComponent") { props ->
    var settings by PropsDelegate(props.settings, props.setNode)
    val (count, building, recipe, clock, details) = settings

    Box {
        className = ClassName("factory-building")

        ControlBar {
            ToggleIconButton {
                toggle = count != null
                setToggle = { on ->
                    settings = settings.copy(count = 1.toUInt().takeIf { on })
                }

                titleOn = "Repeat"
                iconOn = RepeatOn

                titleOff = "Repeat"
                iconOff = Repeat
            }

            count?.also {
                CountInput {
                    model = it
                    setModel = { next -> settings = settings.copy(count = next) }
                }
            }

            BuildingAutocomplete {
                model = building
                setModel = { next ->
                    settings = settings.copy(
                        building = next,
                        recipe = next?.let { new -> recipe?.takeIf { new.recipes.contains(it) } },
                        details = details && recipe != null
                    )
                }
            }

            RecipeAutocomplete {
                model = recipe
                setModel = { next ->
                    settings = settings.copy(
                        recipe = next,
                        building = building ?: next?.let {
                            BUILDINGS.associateWith { it.recipes }
                                .filterValues { it.contains(next) }.keys.singleOrNull()
                        },
                        details = details && recipe != null
                    )
                }

                this.building = building
            }

            ClockSpeedInput {
                model = clock
                setModel = { next -> settings = settings.copy(clock = next) }
            }

            if (recipe != null) {
                ToggleIconButton {
                    toggle = details
                    setToggle = { next -> settings = settings.copy(details = next) }

                    titleOn = "Hide Details"
                    iconOn = ExpandLess

                    titleOff = "Show Details"
                    iconOff = ExpandMore
                }
            }

            Tooltip {
                className = ClassName("factory-building__action")
                title = ReactNode("Move Into Group")

                IconButton {
                    color = IconButtonColor.default
                    size = Size.small
                    Segment {
                        fontSize = SvgIconSize.medium
                    }

                    onClick = { props.setNode(FactoryTree(title = "Factory Group", nodes = listOf(settings))) }
                }
            }
        }

        if (recipe != null && details) {
            Box {
                className = ClassName("factory-building__details")

                Stack {
                    spacing = responsive(2.px)

                    recipe.inputs.takeUnless { it.isEmpty() }?.also {
                        it.entries.forEach { (item, rate) ->
                            Box {
                                className = ClassName("factory-building__item-details")

                                FactoryItemRateInput {
                                    this.clock = clock
                                    setClock = { next -> settings = settings.copy(clock = next) }

                                    this.count = count
                                    this.rate = rate * 60.q / recipe.time
                                }

                                +item.displayName
                            }
                        }
                    } ?: run {
                        Box { +"Nothing" }
                    }
                }

                ArrowForward {
                    className = ClassName("factory-building__recipe-divider")
                }

                Stack {
                    spacing = responsive(2.px)

                    recipe.outputs.takeUnless { it.isEmpty() }?.also {
                        it.entries.forEach { (item, rate) ->
                            Box {
                                className = ClassName("factory-building__item-details")

                                FactoryItemRateInput {
                                    this.clock = clock
                                    setClock = { next -> settings = settings.copy(clock = next) }

                                    this.count = count
                                    this.rate = rate * 60.q / recipe.time
                                }

                                +item.displayName
                            }
                        }
                    } ?: run {
                        Box { +"Nothing" }
                    }
                }
            }
        }
    }
}
