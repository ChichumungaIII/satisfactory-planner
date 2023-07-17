package app.routes.createsave

import app.game.data.Phase
import mui.material.FormControl
import mui.material.InputLabel
import mui.material.MenuItem
import mui.material.Select
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.useContext
import web.cssom.px

external interface PhaseStepProps : Props

val PhaseStep = FC<PhaseStepProps>("PhaseStep") { props ->
  var newSave by useContext(NewSaveContext)!!

  FormControl {
    InputLabel { +"Starting Phase" }
    Select {
      sx { width = 384.px }

      label = ReactNode("Starting Phase")
      autoFocus = true

      value = newSave.phases.size
      onChange = { event, _ ->
        val phases = Phase.values().take(event.target.value.unsafeCast<Int>())
        newSave = newSave.copy(
          phases = phases,
        )
      }

      listOf(0, 1, 2, 3, 4).forEach { n ->
        MenuItem {
          value = n
          +"Phase $n"
        }
      }
    }
  }
}
