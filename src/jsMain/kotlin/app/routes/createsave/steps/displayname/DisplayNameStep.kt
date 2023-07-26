package app.routes.createsave.steps.displayname

import app.routes.createsave.NewSaveContext
import mui.material.TextField
import mui.system.sx
import org.w3c.dom.HTMLInputElement
import react.FC
import react.Props
import react.ReactNode
import react.dom.onChange
import react.useContext
import web.cssom.px

external interface DisplayNameStepProps : Props

val DisplayNameStep = FC<DisplayNameStepProps>("DisplayNameStep") {
  var newSave by useContext(NewSaveContext)!!

  TextField {
    sx { width = 384.px }

    label = ReactNode("Save name")
    autoFocus = true
    required = true

    value = newSave.displayName
    onChange = { event ->
      val displayName = event.target.unsafeCast<HTMLInputElement>().value
      newSave = newSave.copy(displayName = displayName)
    }
  }
}
