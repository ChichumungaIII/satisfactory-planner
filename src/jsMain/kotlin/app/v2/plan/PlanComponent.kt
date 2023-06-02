package app.v2.plan

import app.AppRoute
import app.util.PropsDelegate
import app.v2.common.input.DeletePlanDialog
import app.v2.common.input.EditDisplayNameDialog
import app.v2.common.input.TooltipIconButton
import app.v2.common.layout.AppTitleComponent
import app.v2.common.layout.FrameComponent
import app.v2.common.layout.TitleBarComponent
import app.v2.data.plan.Plan
import app.v2.plan.inputs.PlanInputsComponent
import app.v2.plan.products.PlanProductsComponent
import app.v2.plan.results.PlanResultsComponent
import app.v2.plans.PlansListContext
import app.v2.plans.RemovePlan
import csstype.ClassName
import csstype.Margin
import csstype.px
import js.core.jso
import mui.icons.material.ArrowForward
import mui.icons.material.Delete
import mui.icons.material.Edit
import mui.icons.material.MoreVert
import mui.material.Box
import mui.material.Divider
import mui.material.IconButton
import mui.material.ListItemIcon
import mui.material.ListItemText
import mui.material.Menu
import mui.material.MenuItem
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.create
import react.createContext
import react.router.useNavigate
import react.useContext
import react.useState
import web.dom.Element

external interface PlanComponentProps : Props {
  var plan: Plan
  var setPlan: (Plan) -> Unit
}

val PlanComponentContext = createContext<PropsDelegate<Plan>>()

val PlanComponent = FC<PlanComponentProps>("PlanComponent") { props ->
  val navigate = useNavigate()
  val (_, updatePlans) = useContext(PlansListContext)

  val (_, computeOutcome) = useContext(ComputeOutcomeContext)
  val delegate = PropsDelegate(props.plan) { next ->
    props.setPlan(next)
    computeOutcome(ForPlan(next))
  }
  var plan by delegate

  var menuElement by useState<Element?>(null)
  var displayName by useState<String?>(null)
  var planToDelete by useState<Plan?>(null)

  PlanComponentContext(delegate) {
    FrameComponent {
      titleBar = TitleBarComponent.create {
        title = AppTitleComponent.create { title = plan.title }
        controls = IconButton.create {
          className = ClassName("title-bar__icon")
          MoreVert {}
          onClick = { event -> menuElement = event.currentTarget }
        }
      }

      Box {
        className = ClassName("plan")

        Stack {
          className = ClassName("plan__section")
          direction = responsive(StackDirection.row)
          spacing = responsive(4.px)

          PlanInputsComponent {}

          Box {
            className = ClassName("plan__select-recipes-container")

            TooltipIconButton {
              className = ClassName("plan__select-recipes")
              title = "Select recipes"
              icon = ArrowForward
              onClick = {}
            }
          }

          PlanProductsComponent {}
        }

        plan.results?.also { results ->
          Divider { sx { margin = Margin(2.px, 0.px) } }

          PlanResultsComponent {
            className = ClassName("plan__section")
            this.results = results
          }
        }
      }
    }
  }

  Menu {
    open = menuElement != null
    menuElement?.also { anchorEl = { it } }
    anchorOrigin = jso { vertical = "top"; horizontal = "right" }
    transformOrigin = jso { vertical = "top"; horizontal = "right" }

    onClose = { menuElement = null }

    MenuItem {
      ListItemIcon { Edit {} }
      ListItemText { +"Rename Plan" }
      onClick = {
        menuElement = null
        displayName = plan.title
      }
    }
    MenuItem {
      ListItemIcon { Delete {} }
      ListItemText { +"Delete Plan" }
      onClick = {
        menuElement = null
        planToDelete = plan
      }
    }
  }

  displayName?.also {
    EditDisplayNameDialog {
      title = "Rename Factory"
      this.displayName = it
      onCancel = { displayName = null }
      onAccept = {
        displayName = null
        plan = plan.copy(title = it)
      }
    }
  }

  planToDelete?.also {
    DeletePlanDialog {
      this.plan = it
      onCancel = { planToDelete = null }
      onDelete = {
        planToDelete = null
        updatePlans(RemovePlan(it.id))
        navigate(AppRoute.PLANS.url)
      }
    }
  }
}
