package app.v2.factory

import app.AppRoute
import app.util.PropsDelegate
import app.v2.common.input.EditDisplayNameDialog
import app.v2.common.layout.AppTitleComponent
import app.v2.common.layout.FrameComponent
import app.v2.common.layout.TitleBarComponent
import app.v2.data.Factory
import app.v2.factories.DeleteFactory
import app.v2.factories.DeleteFactoryDialog
import app.v2.factories.FactoriesContext
import app.v2.factory.content.FactoryContentComponent
import js.core.jso
import mui.icons.material.Delete
import mui.icons.material.Edit
import mui.icons.material.MoreVert
import mui.material.IconButton
import mui.material.ListItemIcon
import mui.material.ListItemText
import mui.material.Menu
import mui.material.MenuItem
import react.FC
import react.Props
import react.create
import react.router.useNavigate
import react.useContext
import react.useState
import web.cssom.ClassName
import web.dom.Element

external interface FactoryComponentProps : Props {
  var factory: Factory
  var setFactory: (Factory) -> Unit
}

val FactoryComponent = FC<FactoryComponentProps>("FactoryComponent") { props ->
  val navigate = useNavigate()
  val (_, updateFactories) = useContext(FactoriesContext)!!

  var factory by PropsDelegate(props.factory, props.setFactory)

  var menuElement by useState<Element?>(null)
  var displayName by useState<String?>(null)
  var factoryToDelete by useState<Factory?>(null)

  FrameComponent {
    titleBar = TitleBarComponent.create {
      title = AppTitleComponent.create { title = factory.displayName }
      controls = IconButton.create {
        className = ClassName("title-bar__icon")
        MoreVert {}
        onClick = { event -> menuElement = event.currentTarget }
      }
    }

    FactoryContentComponent {
      content = factory.tree
      setContent = { next -> factory = factory.copy(tree = next) }
    }
  }

  Menu {
    open = menuElement != null
    menuElement?.also { anchorEl = { it } }
    anchorOrigin = jso { vertical = "top"; horizontal = "right" }
    transformOrigin = jso { vertical = "top"; horizontal = "right" }

    onClose = { menuElement = null }

    MenuItem {
      ListItemIcon { Edit {} }
      ListItemText { +"Rename Factory" }
      onClick = {
        menuElement = null
        displayName = factory.displayName
      }
    }
    MenuItem {
      ListItemIcon { Delete {} }
      ListItemText { +"Delete Factory" }
      onClick = {
        menuElement = null
        factoryToDelete = factory
      }
    }
  }

  displayName?.also {
    EditDisplayNameDialog {
      title = "Rename Factory"
      this.displayName = it
      onCancel = { displayName = null }
      onAccept = {
        displayName = null
        factory = factory.copy(displayName = it)
      }
    }
  }

  factoryToDelete?.also {
    DeleteFactoryDialog {
      this.factory = it
      onCancel = { factoryToDelete = null }
      onDelete = {
        factoryToDelete = null
        updateFactories(DeleteFactory(it.id))
        navigate(AppRoute.V2_FACTORIES.url)
      }
    }
  }
}
