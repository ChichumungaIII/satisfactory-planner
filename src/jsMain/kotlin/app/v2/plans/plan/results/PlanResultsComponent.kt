package app.v2.plans.plan.results

import app.util.PropsDelegate
import app.v2.plans.data.model.PlanResult
import mui.material.Box
import mui.material.Stack
import react.FC
import react.Props
import util.math.q

external interface PlanResultsComponentProps : Props {
  var results: List<PlanResult>?
  var setResults: (List<PlanResult>?) -> Unit
}

val PlanResultsComponent = FC<PlanResultsComponentProps>("PlanResultsComponent") { props ->
  var results by PropsDelegate(props.results, props.setResults)

  results?.let {
    Stack {
      it.forEachIndexed { index, result ->
        PlanResultComponent.takeUnless { result.clock == 0.q }?.invoke {
          recipe = result.recipe
          clock = result.clock
          details = result.details
          setDetails = { next ->
            results = it.subList(0, index) + result.copy(details = next) + it.subList(index + 1, it.size)
          }
        }
      }
    }
  } ?: Box {
    +"Plan not yet computed."
  }
}
