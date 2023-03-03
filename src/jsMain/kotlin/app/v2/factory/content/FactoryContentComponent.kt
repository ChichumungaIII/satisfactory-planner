package app.v2.factory.content

import app.util.PropsDelegate
import app.v2.data.FactoryLeaf
import app.v2.data.FactoryTree
import csstype.ClassName
import csstype.Margin
import csstype.px
import mui.icons.material.Add
import mui.icons.material.ArrowDropDown
import mui.icons.material.ArrowDropUp
import mui.icons.material.Clear
import mui.material.*
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode

external interface FactoryContentComponentProps : Props {
    var content: FactoryTree
    var setContent: (FactoryTree) -> Unit
}

val FactoryContentComponent: FC<FactoryContentComponentProps> = FC("FactoryContentComponent") { props ->
    var content by PropsDelegate(props.content, props.setContent)

    Card {
        className = ClassName("factory-content")

        variant = PaperVariant.outlined
        elevation = 2

        content.nodes.withIndex().forEach { (index, node) ->
            Box {
                className = ClassName("factory-content__item")

                Tooltip {
                    title = ReactNode("Delete")

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
                            content = content.splice(index - 1, 2, content.nodes[index], content.nodes[index - 1])
                        }
                    }

                    IconButton {
                        className = ClassName("factory-content__item__controls__button")

                        size = Size.small
                        ArrowDropDown { fontSize = SvgIconSize.small }

                        disabled = index == content.nodes.size - 1
                        onClick = {
                            content = content.splice(index, 2, content.nodes[index + 1], content.nodes[index])
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
            title = ReactNode("Add Building")

            Fab {
                color = FabColor.primary
                size = Size.small
                Add { fontSize = SvgIconSize.medium }

                onClick = { content = content.addNode(FactoryLeaf()) }
            }
        }
    }
}

val RecursiveFactoryContentComponent = FactoryContentComponent
