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
import react.useState

external interface SavePageProps : Props {
  var save: Save
}

val SavePage = FC<SavePageProps>("SavePage") { props ->
  var showPlans by useState(false)

  Accordion {
    expanded = showPlans
    onChange = { _, next -> showPlans = next }

    AccordionSummary {
      expandIcon = ExpandMore.create()

      Typography {
        variant = TypographyVariant.h2
        +"Plans"
      }
    }

    AccordionDetails {
      PlanList.takeIf { showPlans }?.invoke {
        save = props.save
      }
    }
  }
}
