package app.v2.factory.content

import app.util.PropsDelegate
import app.v2.common.layout.ControlBar
import app.v2.common.layout.ControlBarItem
import app.v2.data.FactoryLeaf
import app.v2.data.FactoryTree
import csstype.ClassName
import csstype.Margin
import csstype.px
import mui.icons.material.Add
import mui.icons.material.ArrowDropDown
import mui.icons.material.ArrowDropUp
import mui.icons.material.Clear
import mui.icons.material.KeyboardDoubleArrowDown
import mui.icons.material.KeyboardDoubleArrowUp
import mui.material.Box
import mui.material.Card
import mui.material.Divider
import mui.material.Fab
import mui.material.FabColor
import mui.material.FormControlVariant
import mui.material.IconButton
import mui.material.IconButtonColor
import mui.material.Orientation
import mui.material.PaperVariant
import mui.material.Size
import mui.material.SvgIconSize
import mui.material.TextField
import mui.material.Tooltip
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.dom.onChange

external interface FactoryContentComponentProps : Props {
    var content: FactoryTree
    var setContent: (FactoryTree) -> Unit
}

val FactoryContentComponent: FC<FactoryContentComponentProps> = FC("FactoryContentComponent") { props ->
    var content by PropsDelegate(props.content, props.setContent)
    val (count, title, nodes, details, expanded) = content

    Box {
        className = ClassName("factory-content")

        ControlBar {
            title?.also {
                ControlBarItem {
                    TextField {
                        variant = FormControlVariant.outlined
                        size = Size.small

                        value = it
                        onChange = { event ->
                            content = content.copy(title = event.target.asDynamic().value as String)
                        }
                    }
                }
            }

            ControlBarItem {
                Tooltip {
                    this.title = if (expanded) ReactNode("Collapse") else ReactNode("Expand")

                    IconButton {
                        color = IconButtonColor.default
                        size = Size.small
                        (if (expanded) KeyboardDoubleArrowUp else KeyboardDoubleArrowDown) {
                            fontSize = SvgIconSize.medium
                        }

                        onClick = { content = content.copy(expanded = !expanded) }
                    }
                }
            }
        }

        Card.takeIf { expanded }?.invoke {
            className = ClassName("factory-content__card")
            variant = PaperVariant.outlined

            nodes.withIndex().forEach { (index, node) ->
                Box {
                    className = ClassName("factory-content__item")

                    Tooltip {
                        this.title = ReactNode("Delete")

                        IconButton {
                            color = IconButtonColor.default
                            size = Size.small
                            Clear { fontSize = SvgIconSize.small }

                            onClick = {
                                content = content.removeNode(index)
                            }
                        }
                    }

                    Box {
                        className = ClassName("factory-content__item__controls")

                        IconButton {
                            className = ClassName("factory-content__item__controls__button")

                            size = Size.small
                            ArrowDropUp { fontSize = SvgIconSize.small }

                            disabled = index == 0
                            onClick = {
                                content = content.splice(index - 1, 2, nodes[index], nodes[index - 1])
                            }
                        }

                        IconButton {
                            className = ClassName("factory-content__item__controls__button")

                            size = Size.small
                            ArrowDropDown { fontSize = SvgIconSize.small }

                            disabled = index == nodes.size - 1
                            onClick = {
                                content = content.splice(index, 2, nodes[index + 1], nodes[index])
                            }
                        }
                    }

                    Divider {
                        sx { margin = Margin(4.px, 0.px, 4.px, 2.px) }

                        orientation = Orientation.vertical
                        flexItem = true
                    }

                    when (node) {
                        is FactoryTree -> RecursiveFactoryContentComponent {
                            this.content = node
                            this.setContent = { next -> content = content.setNode(index, next) }
                        }

                        is FactoryLeaf -> FactoryBuildingComponent {
                            settings = node
                            setNode = { next -> content = content.setNode(index, next) }
                        }
                    }
                }
            }

            Tooltip {
                className = ClassName("factory-content__add-building")
                this.title = ReactNode("Add Building")

                Fab {
                    color = FabColor.primary
                    size = Size.small
                    Add { fontSize = SvgIconSize.medium }

                    onClick = { content = content.addNode(FactoryLeaf()) }
                }
            }
        }
    }
}

val RecursiveFactoryContentComponent = FactoryContentComponent
