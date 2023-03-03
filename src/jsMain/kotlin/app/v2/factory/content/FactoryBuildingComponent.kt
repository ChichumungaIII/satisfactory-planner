package app.v2.factory.content

import app.common.RationalInput
import app.common.RationalValidation.Companion.fail
import app.common.RationalValidation.Companion.pass
import app.util.PropsDelegate
import app.v2.common.input.*
import app.v2.data.FactoryLeaf
import app.v2.data.FactoryNode
import app.v2.data.FactoryTree
import csstype.ClassName
import csstype.px
import mui.icons.material.*
import mui.material.*
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

        Box {
            className = ClassName("factory-building__settings")

            Tooltip {
                className = ClassName("factory-building__action")
                title = ReactNode("Repeat")

                IconButton {
                    color = IconButtonColor.default
                    size = Size.small
                    (if (count == null) Repeat else RepeatOn) {
                        fontSize = SvgIconSize.small
                    }

                    onClick = {
                        val next = if (count == null) 1.toUInt() else null
                        settings = settings.copy(count = next)
                    }
                }
            }

            count?.also {
                CountInput {
                    model = it
                    setModel = { next -> settings = settings.copy(count = next)}
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
                Tooltip {
                    className = ClassName("factory-building__action")
                    title = if (details) ReactNode("Hide Details") else ReactNode("Show Details")

                    IconButton {
                        color = IconButtonColor.default
                        size = Size.small
                        (if (details) ExpandLess else ExpandMore) {
                            fontSize = SvgIconSize.medium
                        }

                        onClick = { settings = settings.copy(details = !details) }
                    }
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
                    className = ClassName("factory-building__items")

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
                    className = ClassName("factory-building__items")

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
