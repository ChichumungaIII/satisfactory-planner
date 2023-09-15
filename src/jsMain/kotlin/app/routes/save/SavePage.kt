package app.routes.save

import app.api.save.v1.Save
import mui.icons.material.ExpandMore
import mui.material.Accordion
import mui.material.AccordionDetails
import mui.material.AccordionSummary
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.create
import react.useContext

external interface SavePageProps : Props {
  var save: Save
}

val SavePage = FC<SavePageProps>("SavePage") { props ->
  var state by useContext(SavePageState.Manager.Context)!!.scope(props.save.name)

  Accordion {
    expanded = state.showPlans
    onChange = { _, next -> state = state.copy(showPlans = next) }

    AccordionSummary {
      expandIcon = ExpandMore.create()

      Typography {
        variant = TypographyVariant.h2
        +"Plans"
      }
    }

    AccordionDetails {
      +"Plans"
    }
  }
}
