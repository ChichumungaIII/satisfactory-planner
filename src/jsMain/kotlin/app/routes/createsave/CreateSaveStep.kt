package app.routes.createsave

import app.theme.AppThemeContext
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import react.FC
import react.PropsWithChildren
import react.useContext

external interface CreateSaveStepProps : PropsWithChildren {
  var initial: Boolean?
  var final: Boolean?
}

val CreateSaveStep = FC<CreateSaveStepProps>("CreateSaveStep") { props ->
  val appTheme by useContext(AppThemeContext)!!

  Stack {
    direction = responsive(StackDirection.column)
    spacing = responsive(appTheme.spacing(3))

    +props.children
    CreateSaveStepActions {
      initial = props.initial ?: false
      final = props.final ?: false
    }
  }
}
