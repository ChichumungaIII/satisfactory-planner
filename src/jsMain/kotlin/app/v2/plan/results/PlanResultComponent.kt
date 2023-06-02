package app.v2.plan.results

import app.data.recipe.inputRate
import app.data.recipe.outputRate
import app.util.PropsDelegate
import app.v2.common.input.DetailsToggleButton
import app.v2.common.layout.FauxInputDisplay
import app.v2.common.layout.FauxInputDisplayVariant
import app.v2.common.layout.ThroughputDatum
import app.v2.common.layout.ThroughputDisplayComponent
import app.v2.data.plan.PlanResult
import app.v2.plan.common.PlanContentRow
import csstype.ClassName
import react.FC
import react.Props

external interface PlanResultComponentProps : Props {
  var result: PlanResult
  var setResult: (PlanResult) -> Unit
}

val PlanResultComponent = FC<PlanResultComponentProps>("PlanResultComponent") { props ->
  var result by PropsDelegate(props.result, props.setResult)

  PlanContentRow {
    FauxInputDisplay {
      variant = FauxInputDisplayVariant.RECIPE
      value = result.recipe
    }

    FauxInputDisplay {
      variant = FauxInputDisplayVariant.CLOCK
      value = result.clock
    }

    DetailsToggleButton {
      this.details = result.details
      setDetails = { next -> result = result.copy(details = next) }
    }
  }

  PlanContentRow.takeIf { result.details }?.invoke {
    ThroughputDisplayComponent {
      className = ClassName("plan-result__throughput")

      inputs = result.recipe.inputs.keys.map { item ->
        ThroughputDatum(item, result.recipe.inputRate(item, result.clock))
      }
      outputs = result.recipe.outputs.keys.map { item ->
        ThroughputDatum(item, result.recipe.outputRate(item, result.clock))
      }
    }
  }
}
