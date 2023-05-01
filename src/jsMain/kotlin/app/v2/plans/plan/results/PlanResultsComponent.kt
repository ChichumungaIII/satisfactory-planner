package app.v2.plans.plan.results

import app.v2.plans.data.model.PlanResult
import app.v2.plans.plan.PlanComponentContext
import app.v2.plans.plan.common.PlanHeading
import csstype.ClassName
import mui.material.Stack
import react.FC
import react.PropsWithClassName
import react.useContext
import util.math.q

external interface PlanResultsComponentProps : PropsWithClassName {
  var results: List<PlanResult>
}

val PlanResultsComponent = FC<PlanResultsComponentProps>("PlanResultsComponent") { props ->
  var plan by useContext(PlanComponentContext)
  val results = props.results

  Stack {
    className = ClassName("plan-results ${props.className}")

    PlanHeading { +"Result" }

    results.forEachIndexed { i, result ->
      PlanResultComponent.takeUnless { result.clock == 0.q }?.invoke {
        this.result = result
        setResult = { next ->
          plan = plan.copy(results = results.subList(0, i) + next + results.subList(i + 1, results.size))
        }
      }
    }
  }
}
