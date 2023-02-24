package app.v2.factory.content

import app.util.PropsDelegate
import app.v2.data.FactoryLeaf
import app.v2.data.FactoryTree
import react.FC
import react.Props

external interface FactoryContentComponentProps : Props {
    var content: FactoryTree
    var setContent: (FactoryTree) -> Unit
}

val FactoryContentComponent = FC<FactoryContentComponentProps>("FactoryContentComponent") { props ->
    var content by PropsDelegate(props.content, props.setContent)

    content.nodes.singleOrNull()?.let { node ->
        when (node) {
            is FactoryLeaf -> FactoryBuildingComponent {
                settings = node

                setBuilding = { next -> content = content.setNode(0, node.copy(building = next)) }
            }


            is FactoryTree -> {
                TODO("Handle nontrivial factory trees.")
            }
        }
    } ?: run {
        TODO("Handle multiple content nodes.")
    }
}
