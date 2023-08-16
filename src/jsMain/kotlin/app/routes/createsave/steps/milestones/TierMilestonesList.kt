package app.routes.createsave.steps.milestones

import app.game.data.Milestone
import app.game.data.Tier
import app.routes.createsave.NewSaveContext
import app.theme.AppThemeContext
import mui.icons.material.ExpandLess
import mui.icons.material.ExpandMore
import mui.material.Checkbox
import mui.material.Collapse
import mui.material.IconButton
import mui.material.IconButtonEdge
import mui.material.ListItem
import mui.material.ListItemButton
import mui.material.ListItemIcon
import mui.material.ListItemText
import mui.material.SwitchBaseEdge
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.useContext
import react.useState
import web.cssom.px

external interface TierMilestonesListProps : Props {
  var tier: Tier
}

val TierMilestonesList = FC<TierMilestonesListProps>("TierMilestonesList") { props ->
  val appTheme by useContext(AppThemeContext)!!

  var newSave by useContext(NewSaveContext)!!
  val milestones = Milestone.entries.filter { it.tier == props.tier }
  val allSelected = newSave.milestones.containsAll(milestones)
  val anySelected = milestones.any { newSave.milestones.contains(it) }

  var expanded by useState(!allSelected)

  ListItem {
    ListItemButton {
      ListItemIcon {
        sx { minWidth = 24.px }

        Checkbox {
          sx { padding = appTheme.spacing(1) }
          edge = SwitchBaseEdge.start
          tabIndex = -1
          disableRipple = true
          checked = allSelected
          indeterminate = anySelected && !allSelected
        }
      }

      ListItemText { primary = ReactNode(props.tier.displayName) }
      onClick = {
        val next =
          if (allSelected) newSave.milestones - milestones.toSet()
          else newSave.milestones.toSet() + milestones
        newSave = newSave.setMilestones(next.toList())
      }
    }

    secondaryAction = IconButton.create {
      onClick = { expanded = !expanded }
      edge = IconButtonEdge.end
      (ExpandLess.takeIf { expanded } ?: ExpandMore) { }
    }
  }

  Collapse {
    sx { paddingLeft = appTheme.spacing(8) }
    `in` = expanded

    mui.material.List {
      milestones.forEach { milestone ->
        val included = newSave.milestones.contains(milestone)

        ListItemButton {
          ListItemIcon {
            sx { minWidth = 24.px }

            Checkbox {
              sx { padding = appTheme.spacing(1) }
              edge = SwitchBaseEdge.start
              tabIndex = -1
              disableRipple = true
              checked = included
            }
          }

          ListItemText { +milestone.displayName }
          onClick = {
            val next =
              if (included) newSave.milestones - milestone
              else newSave.milestones + milestone
            newSave = newSave.setMilestones(next)
          }
        }
      }
    }
  }
}
