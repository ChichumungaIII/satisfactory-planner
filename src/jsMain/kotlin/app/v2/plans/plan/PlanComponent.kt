package app.v2.plans.plan

import app.api.OptimizeRequest
import app.api.OptimizeResponse
import app.api.client.optimize
import app.data.recipe.ProductionRecipe
import app.util.PropsDelegate
import app.v2.AppScope
import app.v2.plans.data.model.Plan
import app.v2.plans.data.model.PlanResult
import app.v2.plans.plan.inputs.PlanInputsComponent
import app.v2.plans.plan.products.PlanProductsComponent
import app.v2.plans.plan.results.PlanResultsComponent
import csstype.ClassName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mui.icons.material.Circle
import mui.material.Box
import mui.material.ButtonProps
import mui.material.Orientation
import mui.material.Step
import mui.material.StepButton
import mui.material.StepContent
import mui.material.Stepper
import mui.material.SvgIconColor
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.useEffect
import kotlin.time.Duration.Companion.milliseconds

external interface PlanComponentProps : Props {
  var plan: Plan
  var setPlan: (Plan) -> Unit
}

private var pending: OptimizeRequest? = null
private var requested: OptimizeRequest? = null
private var latest: OptimizeRequest? = null

private suspend fun conditionalSend(request: OptimizeRequest): OptimizeResponse? {
  if (request != pending || requested != null) return null

  requested = request
  return optimize(request).takeIf { request == requested }
    .also {
      requested = null
      latest = request
    }
}

val PlanComponent = FC<PlanComponentProps>("PlanComponent") { props ->
  var plan by PropsDelegate(props.plan, props.setPlan)

  useEffect(plan.inputs, plan.products) {
    val current = OptimizeRequest(
      // TODO: Use contextual recipes
      recipes = ProductionRecipe.values().toSet(),
      inputs = plan.inputs.mapNotNull { it.item?.let { item -> OptimizeRequest.Input(item, it.amount) } },
      outcomes = plan.products.mapNotNull { product ->
        product.item?.let { item ->
          if (product.exact) OptimizeRequest.Exact(item, product.amount)
          else if (product.maximum == null) OptimizeRequest.Minimum(item, product.amount)
          else OptimizeRequest.Range(item, product.amount, product.maximum)
        }
      },
    )
    if (current == latest) return@useEffect

    pending = current
    AppScope.launch {
      delay(200.milliseconds)
      conditionalSend(current)?.also { response ->
        // TODO: Use contextual recipes
        val results = ProductionRecipe.values()
          .associateWith { response.outcome[it] }
          .mapNotNull { (recipe, rate) -> rate?.let { recipe to it } }
          .map { (recipe, rate) ->
            PlanResult(recipe, rate, details = plan.getResult(recipe)?.details ?: false)
          }
        plan = plan.copy(
          results = results,
          minimums = response.minimums,
          maximums = response.maximums,
        )
      }
    }
  }

  Stepper {
    className = ClassName("plan")
    orientation = Orientation.vertical
    nonLinear = true
    activeStep = plan.activeStep

    listOf(
      PlanStepData(
        title = "Inputs",
        content = PlanInputsComponent.create {
          inputs = plan.inputs
          setInputs = { next -> plan = plan.copy(inputs = next) }

          consumed = plan.consumed
          minimums = plan.minimums
        },
      ), PlanStepData(
        title = "Products",
        content = PlanProductsComponent.create {
          products = plan.products
          setProducts = { next -> plan = plan.copy(products = next) }

          produced = plan.produced
          maximums = plan.maximums
        },
      ), PlanStepData(
        title = "Recipes",
        content = ReactNode("Recipes (content)"),
      ), PlanStepData(
        title = "Results",
        content = PlanResultsComponent.create {
          results = plan.results
          setResults = { next -> plan = plan.copy(results = next) }
        },
      )
    ).withIndex().forEach { (index, step) ->
      Step {
        className = ClassName("plan__step")

        StepButton {
          unsafeCast<ButtonProps>().onClick = { plan = plan.copy(activeStep = index) }
          icon = Circle.create {
            color = SvgIconColor.primary?.takeIf { plan.activeStep == index } ?: SvgIconColor.disabled
          }
          +step.title
        }

        step.summary?.takeUnless { plan.activeStep == index }?.let { summary ->
          Box {
            className = ClassName("plan__step__summary")
            +summary
          }
        }

        StepContent { +step.content }
      }
    }
  }
}

data class PlanStepData(
  var title: String,
  var summary: ReactNode? = null,
  var content: ReactNode,
)
