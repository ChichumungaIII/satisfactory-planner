package app.common.util

import app.data.common.SaveState
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props

external interface SaveIndicatorProps : Props {
  var state: SaveState
}

val SaveIndicator = FC<SaveIndicatorProps>("SaveIndicator") { props ->
  Typography {
    variant = TypographyVariant.caption

    if (props.state.saving) {
      +"Saving..."
    } else props.state.lastSaved?.also {
      val localDateTime = it.toLocalDateTime(TimeZone.currentSystemDefault())
      +"Last saved $localDateTime"
    }
  }
}
