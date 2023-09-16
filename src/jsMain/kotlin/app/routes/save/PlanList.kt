package app.routes.save

import app.api.plan.v1.CreatePlanRequest
import app.api.plan.v1.DeletePlanRequest
import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.api.plan.v1.getPlanService
import app.api.save.v1.Save
import app.common.input.NamedResourceDialog
import app.common.util.LoadingIndicator
import app.common.util.LoadingIndicatorVariant
import app.redux.state.resource.ResourceState.Empty
import app.redux.state.resource.ResourceState.Loaded
import app.redux.state.resource.ResourceState.Loading
import app.redux.state.resource.plan.DeletePlan
import app.redux.state.resource.plan.InsertPlan
import app.redux.state.resource.plan.LoadPlans
import app.redux.state.resource.plan.usePlans
import app.redux.useAppDispatch
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
import react.useState
import web.cssom.Position
import web.cssom.px
import kotlin.time.Duration.Companion.seconds

external interface PlanListProps : Props {
  var save: Save
}

val PlanList = FC<PlanListProps>("PlanList") { props ->
  val navigate = useNavigate()
  val dispatch = useAppDispatch()

  val plans = usePlans(props.save.name)

  var createPlanDisplayName by useState<String?>(null)
  val createPlanDialogState = useState(false)
  var createPlanDialog by createPlanDialogState

  Paper {
    variant = PaperVariant.outlined

    mui.material.List {
      when (plans) {
        is Empty -> dispatch(LoadPlans(props.save.name))

        is Loading -> {
          ListItem {
            ListItemText {
              LoadingIndicator { variant = LoadingIndicatorVariant.Small }
            }
          }
        }

        is Loaded -> {
          plans.resource.forEach { plan ->
            ListItem {
              ListItemButton {
                onClick = {
                }
                ListItemIcon { Schema {} }
                ListItemText { +plan.displayName }
              }

              secondaryAction = IconButton.create {
                size = Size.small
                edge = IconButtonEdge.end
                onClick = {
                  launchMain {
                    getPlanService().deletePlan(DeletePlanRequest(plan.name))
                  }
                  dispatch(DeletePlan(plan.name))
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
      }
    }
  }

  NamedResourceDialog.takeIf { createPlanDialog }?.invoke {
    openState = createPlanDialogState
    title = "Create New Plan"
    onCreate = { name ->
      launchMain {
        val request = CreatePlanRequest(
          parent = props.save.name,
          plan = Plan(
            name = PlanName.createRandom(props.save.name),
            displayName = name,
            partition = Plan.Partition.empty(),
          )
        )
        val plan = getPlanService().createPlan(request)
        delay(2.seconds)
        dispatch(InsertPlan(plan))
      }
      createPlanDisplayName = name
    }
  }
}
