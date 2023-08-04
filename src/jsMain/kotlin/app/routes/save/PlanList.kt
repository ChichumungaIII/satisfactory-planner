package app.routes.save

import app.api.plan.v1.CreatePlanRequest
import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.api.plan.v1.PlanServiceJs
import app.api.save.v1.Save
import app.common.input.NamedResourceDialog
import app.common.util.LoadingIndicator
import app.common.util.LoadingIndicatorVariant
import app.data.common.RemoteData
import app.data.plan.PlanCollectionLoader
import app.util.launchMain
import kotlinx.coroutines.delay
import mui.icons.material.Add
import mui.icons.material.Schema
import mui.material.ListItem
import mui.material.ListItemButton
import mui.material.ListItemIcon
import mui.material.ListItemText
import mui.material.Paper
import mui.material.PaperVariant
import react.FC
import react.Props
import react.useContext
import react.useState
import kotlin.time.Duration.Companion.milliseconds

external interface PlanListProps : Props {
  var save: Save
}

val PlanList = FC<PlanListProps>("PlanList") { props ->
  val planService = useContext(PlanServiceJs.Context)!!
  val (planCollection, planCollectionLoader) = useContext(PlanCollectionLoader.Context)!!

  var createPlanDisplayName by useState<String?>(null)
  val createPlanDialogState = useState(false)
  var createPlanDialog by createPlanDialogState

  Paper {
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

          createPlanDisplayName?.also { displayName ->
            ListItem {
              ListItemIcon { LoadingIndicator { variant = LoadingIndicatorVariant.Small } }
              ListItemText { +displayName }
            }
          }

          ListItemButton {
            disabled = createPlanDisplayName != null
            onClick = { createPlanDialog = true }

            ListItemIcon { Add {} }
            ListItemText { +"Create Plan" }
          }
        }

        is RemoteData.Error -> TODO()
      }
    }
  }

  NamedResourceDialog.takeIf { createPlanDialog }?.invoke {
    openState = createPlanDialogState
    title = "Create New Plan"
    onCreate = { name ->
      launchMain {
        delay(2000.milliseconds)

        val request = CreatePlanRequest(
          parent = props.save.name,
          plan = Plan(
            name = PlanName.createRandom(props.save.name),
            displayName = name,
            partition = Plan.Partition.empty(),
          )
        )
        val plan = planService.createPlan(request)
        planCollectionLoader.add(plan)
        createPlanDisplayName = null
      }
      createPlanDisplayName = name
    }
  }
}
