package app.v2.factory.content

import app.util.PropsDelegate
import app.v2.common.input.CountToggleComponent
import app.v2.common.input.DetailsToggleButton
import app.v2.common.input.ToggleIconButton
import app.v2.common.input.TooltipIconButton
import app.v2.common.layout.ControlBar
import app.v2.data.FactoryLeaf
import app.v2.data.FactoryNode
import app.v2.data.FactoryTree
import csstype.ClassName
import csstype.Margin
import csstype.number
import csstype.px
import js.core.jso
import mui.icons.material.Add
import mui.icons.material.ArrowDropDown
import mui.icons.material.ArrowDropUp
import mui.icons.material.ArrowForward
import mui.icons.material.Clear
import mui.icons.material.FormatIndentDecrease
import mui.icons.material.KeyboardDoubleArrowDown
import mui.icons.material.KeyboardDoubleArrowUp
import mui.icons.material.Layers
import mui.icons.material.LayersClear
import mui.icons.material.LayersOutlined
import mui.material.Box
import mui.material.Card
import mui.material.Checkbox
import mui.material.CloseReason
import mui.material.Divider
import mui.material.FabColor
import mui.material.FormControlVariant
import mui.material.IconButton
import mui.material.Orientation
import mui.material.Paper
import mui.material.PaperVariant
import mui.material.Size
import mui.material.SpeedDial
import mui.material.SpeedDialAction
import mui.material.SpeedDialDirection
import mui.material.Stack
import mui.material.StackDirection
import mui.material.SvgIconSize
import mui.material.TextField
import mui.material.Tooltip
import mui.material.TooltipPlacement
import mui.material.ZoomProps
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.dom.onChange

external interface FactoryContentComponentProps : Props {
  var content: FactoryTree
  var setContent: (FactoryTree) -> Unit

  var onFlatten: (() -> Unit)?
}

val FactoryContentComponent: FC<FactoryContentComponentProps> = FC("FactoryContentComponent") { props ->
  var content by PropsDelegate(props.content, props.setContent)
  val (count, title, nodes, details, expanded, newGroup) = content

  Stack {
    className = ClassName("factory-content")
    spacing = responsive(4.px)

    ControlBar {
      CountToggleComponent {
        this.count = count
        setCount = { next -> content = content.copy(count = next) }
      }

      title?.also {
        TextField {
          variant = FormControlVariant.outlined
          size = Size.small

          value = it
          onChange = { event ->
            content = content.copy(title = event.target.asDynamic().value as String)
          }
        }
      }

      DetailsToggleButton {
        this.details = details
        setDetails = { next -> content = content.copy(details = next) }
      }

      ToggleIconButton {
        toggle = expanded
        setToggle = { next -> content = content.copy(expanded = next) }

        titleOn = "Collapse"
        iconOn = KeyboardDoubleArrowUp

        titleOff = "Expand"
        iconOff = KeyboardDoubleArrowDown
      }

      if (expanded) {
        props.onFlatten?.also { onFlatten ->
          TooltipIconButton {
            this.title = "Flatten Group"
            icon = FormatIndentDecrease
            onClick = { onFlatten() }
          }
        }

        ToggleIconButton {
          toggle = newGroup == null
          setToggle = { confirm ->
            if (confirm) {
              var next = content
              newGroup?.takeUnless { it.isEmpty() }?.also { group ->
                val insert = mutableListOf<FactoryNode>()
                group.reversed().forEach { i ->
                  next = next.removeNode(i)
                  insert.add(0, nodes[i])
                }
                next = next.splice(group[0], 0, FactoryTree(title = "Factory Group", nodes = insert))
              }
              content = next.copy(newGroup = null)
            } else {
              content = content.copy(newGroup = listOf())
            }
          }

          titleOn = "Create Group"
          iconOn = LayersOutlined

          titleOff = "Confirm Group"
          iconOff = Layers
        }

        if (newGroup != null) {
          TooltipIconButton {
            this.title = "Cancel Group"
            icon = LayersClear
            onClick = { content = content.copy(newGroup = null) }
          }
        }
      }
    }

    Stack.takeIf { details }?.invoke {
      className = ClassName("factory-content__details")
      direction = responsive(StackDirection.row)

      Stack {
        spacing = responsive(2.px)

        content.inputs.takeUnless { it.isEmpty() }?.forEach { (item, rate) ->
          Stack {
            className = ClassName("factory-content__detail-list")
            direction = responsive(StackDirection.row)

            Paper {
              className = ClassName("factory-content__detail-display")
              variant = PaperVariant.outlined

              +"$rate"
              Box { sx { flexGrow = number(1.0) } }
              +"/ min"
            }
            +item.displayName
          }
        } ?: run {
          Box { +"Nothing" }
        }
      }

      ArrowForward {
        className = ClassName("factory-content__detail-divider")
      }

      Stack {
        spacing = responsive(2.px)

        content.outputs.takeUnless { it.isEmpty() }?.forEach { (item, rate) ->
          Stack {
            className = ClassName("factory-content__detail-list")
            direction = responsive(StackDirection.row)

            Paper {
              className = ClassName("factory-content__detail-display")
              variant = PaperVariant.outlined

              +"$rate"
              Box { sx { flexGrow = number(1.0) } }
              +"/ min"
            }
            +item.displayName
          }
        } ?: run {
          Box { +"Nothing" }
        }
      }
    }

    Card.takeIf { expanded }?.invoke {
      className = ClassName("factory-content__card")
      variant = PaperVariant.outlined

      Stack {
        spacing = responsive(6.px)

        nodes.withIndex().forEach { (index, node) ->
          Box {
            className = ClassName("factory-content__item")

            newGroup?.also { group ->
              Tooltip {
                val helptext = "Add to Group".takeUnless { group.contains(index) } ?: "Remove from Group"
                this.title = ReactNode(helptext)
                placement = TooltipPlacement.right

                Checkbox {
                  className = ClassName("factory-content__group-checkbox")
                  size = Size.small

                  checked = group.contains(index)
                  onChange = { _, include ->
                    val nextGroup = if (include) {
                      (group + index).sorted()
                    } else {
                      group - index
                    }
                    content = content.copy(newGroup = nextGroup)
                  }
                }
              }
            } ?: run {
              TooltipIconButton {
                this.title = "Delete"
                icon = Clear
                onClick = { content = content.removeNode(index) }
              }
            }

            Box {
              className = ClassName("factory-content__item__controls")

              IconButton {
                className = ClassName("factory-content__item__controls__button")

                size = Size.small
                ArrowDropUp { fontSize = SvgIconSize.medium }

                disabled = index == 0 || newGroup != null
                onClick = {
                  content = content.splice(index - 1, 2, nodes[index], nodes[index - 1])
                }
              }

              IconButton {
                className = ClassName("factory-content__item__controls__button")

                size = Size.small
                ArrowDropDown { fontSize = SvgIconSize.medium }

                disabled = index == nodes.size - 1 || newGroup != null
                onClick = {
                  content = content.splice(index, 2, nodes[index + 1], nodes[index])
                }
              }
            }

            Divider {
              sx { margin = Margin(4.px, 0.px, 4.px, 2.px) }

              orientation = Orientation.vertical
              flexItem = true
            }

            Box {
              className = ClassName("factory-content__child")

              when (node) {
                is FactoryTree -> RecursiveFactoryContentComponent {
                  this.content = node
                  this.setContent = { next -> content = content.setNode(index, next) }

                  onFlatten = {
                    val one = 1.toUInt()
                    val children = node.nodes.map { child ->
                      child.clone(count = node.count?.takeUnless { it == one }?.let { (child.count ?: one) * it }
                        ?: child.count)
                    }
                    content = content.splice(index, 1, children)
                  }
                }

                is FactoryLeaf -> FactoryBuildingComponent {
                  settings = node
                  setNode = { next -> content = content.setNode(index, next) }
                }
              }
            }
          }
        }

        SpeedDial {
          ariaLabel = "Add building"
          className = ClassName("factory-content__add-building")

          TransitionProps = jso<ZoomProps> { appear = false }

          direction = SpeedDialDirection.right
          icon = Tooltip.create {
            this.title = ReactNode("Add Building")
            placement = TooltipPlacement.top

            Add { fontSize = SvgIconSize.medium }
          }

          FabProps = jso {
            color = FabColor.primary
            size = Size.small
          }

          onClose = { _, reason ->
            if (reason == CloseReason.toggle) {
              content = content.addNode(FactoryLeaf())
            }
          }

          SpeedDialAction {
            key = "group"
            icon = LayersOutlined.create()
            tooltipTitle = ReactNode("Add Group")

            onClick = { content = content.addNode(FactoryTree(title = "Factory Group", nodes = listOf())) }
          }
        }
      }
    }
  }
}

val RecursiveFactoryContentComponent = FactoryContentComponent
