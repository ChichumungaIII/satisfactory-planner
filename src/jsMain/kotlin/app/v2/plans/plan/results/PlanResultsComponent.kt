package app.v2.plans.plan.results

import app.data.Item
import app.data.recipe.componentRate
import app.util.PropsDelegate
import app.v2.common.layout.ThroughputDatum
import app.v2.common.layout.ThroughputDisplayComponent
import app.v2.plans.data.model.PlanProduct
import app.v2.plans.data.model.PlanResult
import mui.material.Box
import mui.material.Stack
import react.FC
import react.Props
import react.useMemo
import util.math.Rational
import util.math.q

external interface PlanResultsComponentProps : Props {
  var results: List<PlanResult>?
  var setResults: (List<PlanResult>?) -> Unit

  var products: List<PlanProduct>
}

val PlanResultsComponent = FC<PlanResultsComponentProps>("PlanResultsComponent") { props ->
  var results by PropsDelegate(props.results, props.setResults)

  val items = useMemo(results) {
    results?.let {
      val order = props.products.map { it.item }
      Item.values()
        .map { item ->
          val rate = it
            .map { result -> result.recipe.componentRate(item, result.clock) }
            .reduceOrNull(Rational::plus)
            ?: 0.q
          ThroughputDatum(item, rate)
        }
        .filter { it.rate != 0.q }
        .sortedBy {
          order.indexOf(it.item).takeUnless { index -> index == -1 }
            ?: (order.size + Item.values().indexOf(it.item))
        }
    }
  }
  items?.also {
    ThroughputDisplayComponent {
      inputs = it.filter { it.rate < 0.q }.map { ThroughputDatum(it.item, -it.rate) }
      outputs = it.filter { it.rate > 0.q }
    }
  }

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
