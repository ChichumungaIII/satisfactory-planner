package app.routes.save

import app.AppRoute
import app.api.plan.v1.CreatePlanRequest
import app.api.plan.v1.DeletePlanRequest
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
import mui.icons.material.Delete
import mui.icons.material.Schema
import mui.material.CircularProgress
import mui.material.Icon
import mui.material.IconButton
import mui.material.IconButtonEdge
import mui.material.ListItem
import mui.material.ListItemButton
import mui.material.ListItemIcon
import mui.material.ListItemText
import mui.material.Paper
import mui.material.PaperVariant
import mui.material.Size
import mui.material.SvgIconSize
import mui.system.sx
import react.FC
import react.Props
import react.create
import react.router.useNavigate
import react.useContext
import react.useState
import web.cssom.Position
import web.cssom.px
import kotlin.time.Duration.Companion.milliseconds

external interface PlanListProps : Props {
  var save: Save
}

val PlanList = FC<PlanListProps>("PlanList") { props ->
  val navigate = useNavigate()

  val planService = useContext(PlanServiceJs.Context)!!
  val (planCollection, planCollectionLoader) = useContext(PlanCollectionLoader.Context)!!

  var createPlanDisplayName by useState<String?>(null)
  val createPlanDialogState = useState(false)
  var createPlanDialog by createPlanDialogState

  Paper {
    variant = PaperVariant.outlined

    val collection = planCollection.takeIf { it.name == props.save.name } ?: RemoteData.empty()
    mui.material.List {
      when (collection) {
        is RemoteData.Empty -> planCollectionLoader.load(props.save.name)

        is RemoteData.Loading -> {
          ListItem {
            ListItemText {
              LoadingIndicator { variant = LoadingIndicatorVariant.Small }
            }
          }
        }

        is RemoteData.Loaded -> {
          collection.data.forEach { plan ->
            ListItem {
              ListItemButton {
                onClick = {
                  val url = AppRoute.SAVE_PLAN.url(
                    "saveId" to plan.parent.id.toUInt().toString(),
                    "planId" to plan.name.id.toUInt().toString(),
                  )
                  navigate(to = url)
                }
                ListItemIcon { Schema {} }
                ListItemText { +plan.displayName }
              }

              secondaryAction = IconButton.create {
                size = Size.small
                edge = IconButtonEdge.end
                onClick = {
                  launchMain {
                    planService.deletePlan(DeletePlanRequest(name = plan.name))
                  }
                  planCollectionLoader.remove(plan)
                }

                Delete { fontSize = SvgIconSize.small }
              }
            }
          }

          if (createPlanDisplayName == null) {
            ListItemButton {
              disabled = createPlanDisplayName != null
              onClick = { createPlanDialog = true }

              ListItemIcon { Add {} }
              ListItemText { +"Create Plan" }
            }
          } else {
            ListItem {
              disablePadding = false
              ListItemIcon {
                Icon {
                  sx { position = Position.relative }
                  CircularProgress {
                    sx {
                      position = Position.absolute
                      left = 0.px
                    }
                    size = 24.px
                    thickness = 3.0
                  }
                }
              }
              ListItemText { +"Creating $createPlanDisplayName..." }

            }
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

        val url = AppRoute.SAVE_PLAN.url(
          "saveId" to plan.parent.id.toUInt().toString(),
          "planId" to plan.name.id.toUInt().toString(),
        )
        navigate(to = url)
      }
      createPlanDisplayName = name
    }
  }
}
