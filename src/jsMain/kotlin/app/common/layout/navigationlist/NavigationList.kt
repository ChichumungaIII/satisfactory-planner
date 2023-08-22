package app.common.layout.navigationlist

import app.AppRoute
import app.RouteParams
import app.common.util.LoadingIndicator
import app.common.util.LoadingIndicatorVariant
import app.data.common.RemoteData
import app.data.save.SaveCollectionLoader
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
import web.cssom.px

external interface NavigationListProps : Props

val NavigationList = FC<NavigationListProps>("NavigationList") {
  val navigate = useNavigate()
  val appTheme by useContext(AppThemeContext)!!

  val (saveCollection, saveCollectionLoader) = useContext(SaveCollectionLoader.Context)!!

  var state by useContext(NavigationListState.Context)!!

  mui.material.List {
    ListItem {
      ListItemButton {
        ListItemText { primary = ReactNode("All Saves") }
        onClick = { navigate(to = AppRoute.V3.url) }
      }

      secondaryAction = IconButton.create {
        sx { padding = 6.px }
        onClick = { state = state.copy(showAllSaves = !state.showAllSaves) }
        edge = IconButtonEdge.end
        (ExpandLess.takeIf { state.showAllSaves } ?: ExpandMore) { }
      }
    }

    Collapse {
      `in` = state.showAllSaves
      this.asDynamic().unmountOnExit = true

      mui.material.List.takeIf { state.showAllSaves }?.invoke {
        when (saveCollection) {
          is RemoteData.Empty -> saveCollectionLoader.load()

          is RemoteData.Loading -> ListItem {
            LoadingIndicator { variant = LoadingIndicatorVariant.Small }
          }

          is RemoteData.Loaded -> {
            if (saveCollection.value.isEmpty()) {
              ListItem {
                ListItemText {
                  sx { padding = appTheme.spacing(1, 4) }
                  +"No saves found"
                }
              }
            }

            saveCollection.value.forEach { save ->
              ListItemButton {
                onClick = {
                  navigate(to = AppRoute.SAVE.url(RouteParams.SAVE_ID to save.name.id))
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
