package app.v2.plans.plan.results

import app.data.recipe.Recipe
import app.data.recipe.inputRate
import app.data.recipe.outputRate
import app.util.PropsDelegate
import app.v2.common.input.DetailsToggleButton
import app.v2.common.layout.ControlBar
import app.v2.common.layout.FauxInputDisplay
import app.v2.common.layout.FauxInputDisplayVariant
import app.v2.common.layout.ThroughputDatum
import app.v2.common.layout.ThroughputDisplayComponent
import react.FC
import react.Props
import util.math.Rational

external interface PlanResultComponentProps : Props {
  var recipe: Recipe
  var clock: Rational
  var details: Boolean
  var setDetails: (Boolean) -> Unit
}

val PlanResultComponent = FC<PlanResultComponentProps>("PlanResultComponent") { props ->
  var details by PropsDelegate(props.details, props.setDetails)

  ControlBar {
    FauxInputDisplay {
      variant = FauxInputDisplayVariant.RECIPE
      value = props.recipe
    }

    FauxInputDisplay {
      variant = FauxInputDisplayVariant.CLOCK
      value = props.clock
    }

    DetailsToggleButton {
      this.details = details
      setDetails = { next -> details = next }
    }
  }

  ThroughputDisplayComponent.takeIf { details }?.invoke {
    inputs = props.recipe.inputs.keys.map { item ->
      ThroughputDatum(item, props.recipe.inputRate(item, props.clock))
    }
    outputs = props.recipe.outputs.keys.map { item ->
      ThroughputDatum(item, props.recipe.outputRate(item, props.clock))
    }
  }
}
