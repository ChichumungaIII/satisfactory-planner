package app.factory.selectunit

import app.factory.model.FactoryUnit
import app.factory.model.FactoryUnitArray
import app.factory.model.FactoryUnitContainer
import csstype.ClassName
import mui.icons.material.ElectricBolt
import mui.icons.material.Factory
import mui.icons.material.Loop
import mui.icons.material.ViewList
import mui.material.CloseReason
import mui.material.OpenReason
import mui.material.SpeedDial
import mui.material.SpeedDialAction
import mui.material.SpeedDialDirection
import mui.material.SpeedDialIcon
import org.w3c.dom.Element
import react.FC
import react.Props
import react.create
import react.key
import react.useState

external interface SelectUnitSpeedDialProps : Props {
    var onSelect: (FactoryUnit) -> Unit
}

val SelectUnitSpeedDial = FC<SelectUnitSpeedDialProps>("SelectUnitSpeedDial") { props ->
    var active by useState(false)

    var buildingsAnchor by useState<Element?>(null)
    var generatorsAnchor by useState<Element?>(null)

    SpeedDial {
        open = active
        className = ClassName("select-unit-speed-dial")
        ariaLabel = "Select New Factory Component"
        direction = SpeedDialDirection.right
        icon = SpeedDialIcon.create()

        onOpen = { _, reason ->
            when (reason) {
                OpenReason.toggle -> {
                    active = true
                    buildingsAnchor = null
                    generatorsAnchor = null
                }

                else -> {}
            }
        }
        onClose = { _, reason ->
            when (reason) {
                CloseReason.toggle -> {
                    active = false
                }

                else -> {}
            }
        }

        SpeedDialAction {
            key = "buildings"
            icon = Factory.create()
            onClick = { event -> buildingsAnchor = if (buildingsAnchor == null) event.currentTarget else null }
        }
        SpeedDialAction {
            key = "generators"
            icon = ElectricBolt.create()
            onClick = { event -> generatorsAnchor = if (generatorsAnchor == null) event.currentTarget else null }
        }
        SpeedDialAction {
            key = "container"
            icon = ViewList.create()
            onClick = { props.onSelect(FactoryUnitContainer()) }
        }
        SpeedDialAction {
            key = "array"
            icon = Loop.create()
            onClick = { props.onSelect(FactoryUnitArray()) }
        }
    }

    SelectBuildingMenu {
        parent = buildingsAnchor
        onClose = { buildingsAnchor = null }
        onSelect = {
            props.onSelect(it)
            active = false
            buildingsAnchor = null
        }
    }

    SelectGeneratorMenu {
        parent = generatorsAnchor
        onClose = { generatorsAnchor = null }
        onSelect = {
            props.onSelect(it)
            active = false
            generatorsAnchor = null
        }
    }
}
