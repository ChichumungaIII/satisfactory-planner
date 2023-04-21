package app.v2.plans.plan.results

import app.v2.plans.plan.PlanComponentContext
import mui.material.Stack
import react.FC
import react.Props
import react.useContext
import util.math.q

external interface PlanResultsComponentProps : Props

val PlanResultsComponent = FC<PlanResultsComponentProps>("PlanResultsComponent") { props ->
  var plan by useContext(PlanComponentContext)

  plan.results?.also { results ->
    Stack {
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
}
