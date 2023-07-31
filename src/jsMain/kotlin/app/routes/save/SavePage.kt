package app.routes.save

import app.api.save.v1.Save
import app.common.util.LoadingIndicator
import app.common.util.LoadingIndicatorVariant
import app.data.common.RemoteData
import app.data.plan.PlanCollectionLoader
import mui.icons.material.Add
import mui.icons.material.ExpandMore
import mui.icons.material.Schema
import mui.material.Accordion
import mui.material.AccordionDetails
import mui.material.AccordionSummary
import mui.material.ListItem
import mui.material.ListItemButton
import mui.material.ListItemIcon
import mui.material.ListItemText
import mui.material.Paper
import mui.material.PaperVariant
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.create
import react.useContext
import react.useState

external interface SavePageProps : Props {
  var save: Save
}

val SavePage = FC<SavePageProps>("SavePage") { props ->
  val (planCollection, planCollectionLoader) = useContext(PlanCollectionLoader.Context)!!

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
      Paper.takeIf { showPlans }?.invoke {
        variant = PaperVariant.outlined

        mui.material.List {
          when (planCollection) {
            is RemoteData.Empty -> planCollectionLoader.load(props.save.name)

            is RemoteData.Loading -> {
              ListItem {
                ListItemText {
                  LoadingIndicator { variant = LoadingIndicatorVariant.Small }
                }
              }
            }

            is RemoteData.Loaded -> {
              planCollection.data.forEach { plan ->
                ListItemButton {
                  ListItemIcon { Schema {} }
                  ListItemText { +plan.displayName }
                }
              }

              ListItemButton {
                ListItemIcon { Add {} }
                ListItemText { +"Create Plan" }
              }
            }

            is RemoteData.Error -> TODO()
          }
        }
      }
    }
  }
}
