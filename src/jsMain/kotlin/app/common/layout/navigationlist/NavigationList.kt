package app.common.layout.navigationlist

import app.AppRoute
import app.RouteParams
import app.common.util.LoadingIndicator
import app.common.util.LoadingIndicatorVariant
import app.redux.state.resource.ResourceState.Companion.Empty
import app.redux.state.resource.ResourceState.Companion.Loaded
import app.redux.state.resource.ResourceState.Companion.Loading
import app.redux.state.resource.save.LoadSaves
import app.redux.state.resource.save.useSaves
import app.redux.useAppDispatch
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
  val dispatch = useAppDispatch()

  val saves = useSaves()

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
        when (saves) {
          is Empty -> dispatch(LoadSaves)

          is Loading -> ListItem {
            LoadingIndicator { variant = LoadingIndicatorVariant.Small }
          }

          is Loaded -> {
            if (saves.resource.isEmpty()) {
              ListItem {
                ListItemText {
                  sx { padding = appTheme.spacing(1, 4) }
                  +"No saves found"
                }
              }
            }

            saves.resource.forEach { save ->
              ListItemButton {
                onClick = {
                  navigate(to = AppRoute.V3_SAVE.url(RouteParams.SAVE_ID to save.name.id))
                }

                ListItemText {
                  sx { paddingLeft = appTheme.spacing(4) }
                  +save.displayName
                }
              }
            }
          }
        }
      }
    }
  }

  Divider {}
}
