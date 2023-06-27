package app.sample

import app.themes.ThemeContext
import app.themes.getAppBarHeight
import mui.icons.material.AccountTree
import mui.icons.material.Add
import mui.icons.material.ExpandMore
import mui.icons.material.Factory
import mui.material.Box
import mui.material.Collapse
import mui.material.Drawer
import mui.material.DrawerVariant
import mui.material.IconButton
import mui.material.ListItem
import mui.material.ListItemButton
import mui.material.ListItemIcon
import mui.material.ListItemText
import mui.material.ListSubheader
import mui.system.sx
import react.FC
import react.Props
import react.create
import react.useContext
import web.cssom.pct

external interface SampleDrawerProps : Props

val SampleDrawer = FC<SampleDrawerProps>("SampleDrawer") { props ->
  val theme by useContext(ThemeContext)!!

  Drawer {
    variant = DrawerVariant.persistent
    open = true

    Box { sx { paddingTop = theme.getAppBarHeight() } }

    mui.material.List {
      sx { width = 100.pct }

      subheader = ListSubheader.create { +"Factories" }

      ListItem {
        disablePadding = true

        ListItemButton {
          ListItemIcon { Factory {} }
          ListItemText { +"All Factories" }
        }
        secondaryAction = IconButton.create {
          ExpandMore {}
        }
      }
      Collapse {
        `in` = true

        mui.material.List {
          sx {
            width = 100.pct
            paddingLeft = theme.spacing(6)
          }

          ListItemButton {
            ListItemIcon { Factory {} }
            ListItemText { +"Reinforced Iron Plates" }
          }
          ListItemButton {
            ListItemIcon { Factory {} }
            ListItemText { +"Steel Beams" }
          }
          ListItemButton {
            ListItemIcon { Factory {} }
            ListItemText { +"Oil Refinery" }
          }
          ListItemButton {
            ListItemIcon { Add {} }
            ListItemText { +"New Factory" }
          }
        }
      }
    }

    mui.material.List {
      sx { width = 100.pct }

      subheader = ListSubheader.create { +"Production Plans" }

      ListItem {
        disablePadding = true

        ListItemButton {
          ListItemIcon { AccountTree {} }
          ListItemText { +"All Plans" }
        }
        secondaryAction = IconButton.create {
          ExpandMore {}
        }
      }
      Collapse {
        `in` = true

        mui.material.List {
          sx {
            width = 100.pct
            paddingLeft = theme.spacing(6)
          }

          ListItemButton {
            ListItemIcon { AccountTree {} }
            ListItemText { +"Armory" }
          }
          ListItemButton {
            ListItemIcon { AccountTree {} }
            ListItemText { +"Springboard" }
          }
          ListItemButton {
            ListItemIcon { AccountTree {} }
            ListItemText { +"Oil Fields" }
          }
          ListItemButton {
            ListItemIcon { Add {} }
            ListItemText { +"New Plan" }
          }
        }
      }
    }
  }
}
