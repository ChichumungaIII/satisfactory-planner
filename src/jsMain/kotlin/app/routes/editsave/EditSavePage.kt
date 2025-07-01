package app.routes.editsave

import app.AppRoute
import app.RouteParams
import app.api.save.v1.Save
import app.common.input.groupchecklist.GroupedChecklist
import app.game.data.Milestone
import app.game.data.Phase
import app.game.data.Research
import app.game.data.Tier
import app.redux.state.resource.save.SaveSave
import app.redux.useAppDispatch
import app.routes.createsave.steps.phase.priorPhases
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.Divider
import mui.material.FormControl
import mui.material.InputLabel
import mui.material.MenuItem
import mui.material.Select
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.router.useNavigate
import react.useEffect
import react.useState
import web.cssom.px

external interface EditSavePageProps : Props {
  var save: Save
}

val EditSavePage = FC<EditSavePageProps>("EditSavePage") { props ->
  val navigate = useNavigate()
  val dispatch = useAppDispatch()

  val save = props.save
  var progress by useState(save.progress)
  useEffect(save.progress) { progress = save.progress }

  Typography {
    variant = TypographyVariant.h2
    +"Phase"
  }

  FormControl {
    InputLabel { +"Completed Phase" }
    Select {
      sx { width = 384.px }

      label = ReactNode("Completed Phase")
      autoFocus = true

      value = progress.phase.name
      onChange = { event, _ ->
        val phase = Phase.valueOf(event.target.value.unsafeCast<String>())
        val phases = priorPhases(phase)
        val tiers = Tier.entries.filter { phases.contains(it.phase) }
        val milestones = Milestone.entries.filter { tiers.contains(it.tier) }
        progress = progress.copy(
          phase = phase,
          milestones = save.progress.milestones.filter { milestones.contains(it) },
        )
      }

      Phase.entries.forEach { phase ->
        MenuItem {
          value = phase.name
          +phase.displayName
        }
      }
    }
  }

  Divider {}

  Typography {
    variant = TypographyVariant.h2
    +"Milestones"
  }

  (GroupedChecklist<Milestone>()) {
    elements = Milestone.entries
    getGroup = { it.tier.displayName }
    getDisplayName = { it.displayName }

    selected = progress.milestones.toSet()
    setSelected = { next -> progress = progress.copy(milestones = next.toList()) }
  }

  Divider {}

  Typography {
    variant = TypographyVariant.h2
    +"MAM Research"
  }

  (GroupedChecklist<Research>()) {
    elements = Research.entries.filterNot { it.category == Research.Category.HARD_DRIVE }
    getGroup = { it.category.displayName }
    getDisplayName = { it.displayName }

    selected = progress.research.toSet()
    setSelected = { next ->
      val research = progress.research.filter { it.category == Research.Category.HARD_DRIVE } + next
      progress = progress.copy(research = research)
    }
  }

  Divider {}

  Typography {
    variant = TypographyVariant.h2
    +"Alternate Recipes"
  }

  (GroupedChecklist<Research>()) {
    elements = Research.entries.filter { it.category == Research.Category.HARD_DRIVE }
    getGroup = { it.category.displayName }
    getDisplayName = { it.displayName }

    selected = progress.research.toSet()
    setSelected = { next ->
      val research = progress.research.filterNot { it.category == Research.Category.HARD_DRIVE } + next
      progress = progress.copy(research = research)
    }
  }

  Divider {}

  Button {
    variant = ButtonVariant.contained
    +"Save Changes"
    onClick = {
      dispatch(SaveSave(save = save.copy(progress = progress)))
      navigate(AppRoute.V3_SAVE.url(RouteParams.SAVE_ID to save.name.id))
    }
  }

  Button {
    variant = ButtonVariant.outlined
    +"Cancel"
    onClick = {
      progress = save.progress
      navigate(AppRoute.V3_SAVE.url(RouteParams.SAVE_ID to save.name.id))
    }
  }
}
