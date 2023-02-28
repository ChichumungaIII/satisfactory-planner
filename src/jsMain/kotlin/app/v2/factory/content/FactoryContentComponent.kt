package app.v2.factory.content

import app.util.PropsDelegate
import app.v2.data.FactoryLeaf
import app.v2.data.FactoryTree
import mui.icons.material.Add
import mui.material.*
import react.FC
import react.Props

external interface FactoryContentComponentProps : Props {
    var content: FactoryTree
    var setContent: (FactoryTree) -> Unit
}

val FactoryContentComponent: FC<FactoryContentComponentProps> = FC("FactoryContentComponent") { props ->
    var content by PropsDelegate(props.content, props.setContent)

    Box {
        content.nodes.withIndex().forEach { (index, node) ->
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

            Divider {}
        }

        Fab {
            color = FabColor.primary
            size = Size.small
            Add { fontSize = SvgIconSize.medium }

            onClick = { content = content.addNode(FactoryLeaf()) }
        }
    }
}
val RecursiveFactoryContentComponent = FactoryContentComponent


