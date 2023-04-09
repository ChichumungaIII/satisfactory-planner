package app.v2.common.layout

import app.data.Item
import csstype.ClassName
import csstype.number
import mui.icons.material.ArrowForward
import mui.material.Box
import mui.material.Paper
import mui.material.PaperVariant
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import mui.system.sx
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
    ?.also { data -> Stack { data.forEach { ThroughputItem { datum = it } } } }
    ?: Box { +"Nothing" }
}

external interface ThroughputItemProps : Props {
  var datum: ThroughputDatum
}

private val ThroughputItem = FC<ThroughputItemProps>("ThroughputItem") { props ->
  Paper {
    variant = PaperVariant.outlined

    +"${props.datum.rate}"
    Box { sx { flexGrow = number(1.0) } }
    +"/ min"
  }

  +props.datum.item.displayName
}
