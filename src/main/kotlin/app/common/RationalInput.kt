package app.common

import react.FC
import react.Props
import react.useState
import util.math.Rational

external interface RationalInputProps : Props {
    var value: Rational?
    var setValue: (Rational?) -> Unit
}

val RationalInput = FC<RationalInputProps>("RationalInput") { props ->
    var text by useState(props.value?.toString() ?: "")

}
