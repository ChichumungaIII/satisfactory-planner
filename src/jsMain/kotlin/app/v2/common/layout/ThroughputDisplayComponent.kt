package app.v2.common.layout

import app.data.Item
import csstype.ClassName
import csstype.px
import mui.icons.material.ArrowForward
import mui.material.Box
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import react.FC
import react.Props
import util.math.Rational

external interface ThroughputDisplayComponentProps : Props {
  var inputs: List<ThroughputDatum>
  var outputs: List<ThroughputDatum>
}

data class ThroughputDatum(val item: Item, val rate: Rational)

val ThroughputDisplayComponent = FC<ThroughputDisplayComponentProps>("ThroughputDisplayComponent") { props ->
  Stack {
    className = ClassName("throughput-display")
    direction = responsive(StackDirection.row)
    spacing = responsive(2.px)

    ThroughputList {
      data = props.inputs
    }

    ArrowForward {}

    ThroughputList {
      data = props.outputs
    }
  }
}

external interface ThroughputListProps : Props {
  var data: List<ThroughputDatum>
}

private val ThroughputList = FC<ThroughputListProps>("ThroughputList") { props ->
  props.data.takeUnless { it.isEmpty() }
    ?.also { data ->
      Stack {
        direction = responsive(StackDirection.column)
        spacing = responsive(8.px)

        data.forEach { datum ->
          Stack {
            direction = responsive(StackDirection.row)
            spacing = responsive(6.px)

            FauxInputDisplay {
              variant = FauxInputDisplayVariant.RATE
              value = datum.rate
            }

            FauxInputDisplay {
              variant = FauxInputDisplayVariant.ITEM
              value = datum.item
            }
          }
        }
      }
    }
    ?: Box { +"Nothing" }
}
