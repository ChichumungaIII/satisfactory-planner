package app.common.layout

import app.data.common.RemoteData
import app.data.save.SavesListService
import mui.icons.material.ExpandLess
import mui.icons.material.ExpandMore
import mui.material.Collapse
import mui.material.IconButton
import mui.material.IconButtonEdge
import mui.material.ListItem
import mui.material.ListItemButton
import mui.material.ListItemText
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.useContext
import react.useState

external interface NavigationListProps : Props

val NavigationList = FC<NavigationListProps>("NavigationList") { props ->
  val (savesData, savesListService) = useContext(SavesListService.Context)!!

  var allSaves by useState(false)

  mui.material.List {
    ListItem {
      disablePadding = true

      ListItemButton {
        dense = true

        ListItemText { primary = ReactNode("All Saves") }
      }

      secondaryAction = IconButton.create {
        onClick = { allSaves = !allSaves }
        edge = IconButtonEdge.end
        (ExpandLess.takeIf { allSaves } ?: ExpandMore) {}
      }
    }

    Collapse {
      `in` = allSaves
      this.asDynamic().unmountOnExit = true

      mui.material.List {
        when (savesData) {
          is RemoteData.Empty -> savesListService.load()
          is RemoteData.Loading -> ListItem { LoadingIndicator {} }

          is RemoteData.Loaded -> savesData.data.forEach { save -> }

          is RemoteData.Error -> TODO()
        }
      }
    }
  }
}
