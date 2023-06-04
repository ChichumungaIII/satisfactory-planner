package app.v2.factory.content

import app.util.PropsDelegate
import app.v2.common.input.BUILDINGS
import app.v2.common.input.BuildingAutocomplete
import app.v2.common.input.ClockSpeedInput
import app.v2.common.input.CountToggleComponent
import app.v2.common.input.DetailsToggleButton
import app.v2.common.input.FactoryItemRateInput
import app.v2.common.input.RecipeAutocomplete
import app.v2.common.layout.ControlBar
import app.v2.data.FactoryLeaf
import app.v2.data.FactoryNode
import csstype.ClassName
import csstype.px
import mui.icons.material.ArrowForward
import mui.material.Box
import mui.material.Stack
import mui.system.responsive
import react.FC
import react.Props
import util.math.q

external interface FactoryBuildingComponentProps : Props {
  var settings: FactoryLeaf
  var setNode: (FactoryNode) -> Unit
}

val FactoryBuildingComponent = FC<FactoryBuildingComponentProps>("FactoryBuildingComponent") { props ->
  var settings by PropsDelegate(props.settings, props.setNode)
  val (count, building, recipe, clock, details) = settings

  Box {
    className = ClassName("factory-building")

    ControlBar {
      CountToggleComponent {
        this.count = count
        setCount = { next -> settings = settings.copy(count = next) }
      }

      BuildingAutocomplete {
        model = building
        setModel = { next ->
          settings = settings.copy(
            building = next,
            recipe = next?.let { new -> recipe?.takeIf { new.recipes.contains(it) } },
            details = details && recipe != null
          )
        }
      }

      RecipeAutocomplete {
        model = recipe
        setModel = { next ->
          settings = settings.copy(
            recipe = next,
            building = building ?: next?.let {
              BUILDINGS.associateWith { it.recipes }
                .filterValues { it.contains(next) }.keys.singleOrNull()
            },
            details = details && recipe != null
          )
        }

        this.building = building
      }

      ClockSpeedInput {
        model = clock
        setModel = { next -> settings = settings.copy(clock = next) }
      }

      if (recipe != null) {
        DetailsToggleButton {
          this.details = details
          setDetails = { next -> settings = settings.copy(details = next) }
        }
      }
    }

    if (recipe != null && details) {
      Box {
        className = ClassName("factory-building__details")

        Stack {
          spacing = responsive(2.px)

          recipe.inputs.takeUnless { it.isEmpty() }?.also {
            it.entries.forEach { (item, rate) ->
              Box {
                className = ClassName("factory-building__item-details")

                FactoryItemRateInput {
                  this.clock = clock
                  setClock = { next -> settings = settings.copy(clock = next) }

                  this.count = count
                  this.rate = rate * 60.q / recipe.time
                }

                +item.displayName
              }
            }
          } ?: run {
            Box { +"Nothing" }
          }
        }

        ArrowForward {
          className = ClassName("factory-building__recipe-divider")
        }

        Stack {
          spacing = responsive(2.px)

          recipe.outputs.takeUnless { it.isEmpty() }?.also {
            it.entries.forEach { (item, rate) ->
              Box {
                className = ClassName("factory-building__item-details")

                FactoryItemRateInput {
                  this.clock = clock
                  setClock = { next -> settings = settings.copy(clock = next) }

                  this.count = count
                  this.rate = rate * 60.q / recipe.time
                }

                +item.displayName
              }
            }
          } ?: run {
            Box { +"Nothing" }
          }
        }
      }
    }
  }
}
