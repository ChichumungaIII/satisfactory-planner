package app.v2.factory.content

import app.util.PropsDelegate
import app.v2.data.FactoryLeaf
import app.v2.data.FactoryTree
import csstype.ClassName
import mui.icons.material.Add
import mui.icons.material.ArrowDropDown
import mui.icons.material.ArrowDropUp
import mui.material.*
import react.FC
import react.Props

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

                when (node) {
                    is FactoryTree -> RecursiveFactoryContentComponent {
                        this.content = node
                        this.setContent = { next -> content = content.setNode(index, next) }
                    }

                    is FactoryLeaf -> FactoryBuildingComponent {
                        settings = node
                        setSettings = { next -> content = content.setNode(index, next) }
                    }
                }
            }
        }

        Fab {
            className = ClassName("factory-content__add-building")

            color = FabColor.primary
            size = Size.small
            Add { fontSize = SvgIconSize.medium }

            onClick = { content = content.addNode(FactoryLeaf()) }
        }
    }
}
val RecursiveFactoryContentComponent = FactoryContentComponent


