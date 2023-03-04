package app.v2.common.input

import app.util.PropsDelegate
import mui.icons.material.Repeat
import mui.icons.material.RepeatOn
import react.FC
import react.Props

external interface CountToggleComponentProps : Props {
    var count: UInt?
    var setCount: (UInt?) -> Unit
}

val CountToggleComponent = FC<CountToggleComponentProps>("CountToggleComponent") { props ->
    var count by PropsDelegate(props.count, props.setCount)

    ToggleIconButton {
        toggle = count != null
        setToggle = { on -> count = 1.toUInt().takeIf { on } }

        titleOn = "Repeat"
        iconOn = RepeatOn

        titleOff = "Repeat"
        iconOff = Repeat
    }

    count?.also {
        CountInput {
            model = it
            setModel = { next -> count = next }
        }
    }
}
