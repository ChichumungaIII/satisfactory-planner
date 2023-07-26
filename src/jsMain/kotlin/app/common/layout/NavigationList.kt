package app.common.layout

import app.AppRoute
import app.common.util.LoadingIndicator
import app.common.util.LoadingIndicatorVariant
import app.data.common.RemoteData
import app.data.save.SavesListService
import app.theme.AppThemeContext
import mui.icons.material.ExpandLess
import mui.icons.material.ExpandMore
import mui.material.Collapse
import mui.material.Divider
import mui.material.IconButton
import mui.material.IconButtonEdge
import mui.material.ListItem
import mui.material.ListItemButton
import mui.material.ListItemText
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.router.useNavigate
import react.useContext
import react.useState
import web.cssom.px

external interface NavigationListProps : Props

val NavigationList = FC<NavigationListProps>("NavigationList") {
  val appTheme by useContext(AppThemeContext)!!
  val navigate = useNavigate()
  val (savesData, savesListService) = useContext(SavesListService.Context)!!

  var allSaves by useState(false)

  mui.material.List {
    ListItem {
      ListItemButton {
        ListItemText { primary = ReactNode("All Saves") }
        onClick = { navigate(to = AppRoute.V3.url) }
      }

      secondaryAction = IconButton.create {
        sx { padding = 6.px }
        onClick = { allSaves = !allSaves }
        edge = IconButtonEdge.end
        (ExpandLess.takeIf { allSaves } ?: ExpandMore) { }
      }
    }

    Collapse {
      `in` = allSaves
      this.asDynamic().unmountOnExit = true

      mui.material.List.takeIf { allSaves }?.invoke {
        when (savesData) {
          is RemoteData.Empty -> savesListService.load()

          is RemoteData.Loading -> ListItem {
            LoadingIndicator { variant = LoadingIndicatorVariant.Small }
          }

          is RemoteData.Loaded -> {
            if (savesData.data.isEmpty()) {
              ListItem {
                ListItemText {
                  sx { padding = appTheme.spacing(1, 4) }
                  +"No saves found"
                }
              }
            }

            savesData.data.forEach { save ->
              ListItemButton {
                onClick = {
                  navigate(to = AppRoute.SAVE.url("saveId" to save.name.id.toString()))
                }

                ListItemText {
                  sx { paddingLeft = appTheme.spacing(4) }
                  +save.displayName
                }
              }
            }
          }

          is RemoteData.Error -> TODO()
        }
      }
    }
  }

  Divider {}
}
