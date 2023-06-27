package app.factory.unit.generation

import app.common.RationalInput
import app.common.RationalValidation
import app.common.input.RecipeAutocomplete
import app.factory.model.FactoryUnit
import app.factory.model.Generator
import mui.material.Box
import react.FC
import react.Props
import react.ReactNode
import util.math.q
import web.cssom.ClassName

private val CLOCK_INPUT_SCALE = 100.q
private val MAX_CLOCK_VALUE = 250.q

external interface GeneratorModuleProps : Props {
  var generator: Generator
  var setUnit: (FactoryUnit) -> Unit
}

val GeneratorModule = FC<GeneratorModuleProps>("GeneratorModule") { props ->
  Box {
    className = ClassName("production-building")

    RecipeAutocomplete {
      className = ClassName("production-building__recipe-input")

      recipe = props.generator.recipe
      setRecipe = { props.setUnit(props.generator.copy(recipe = it)) }

      building = props.generator.building
    }

    RationalInput {
      className = ClassName("production-building__clock-input")
      label = ReactNode("Clock speed")

      value = props.generator.clock * CLOCK_INPUT_SCALE
      setValue = { next -> next?.let { props.setUnit(props.generator.copy(clock = it / CLOCK_INPUT_SCALE)) } }

      validators = listOf(
        { value ->
          if (value >= 0.q) RationalValidation.pass()
          else RationalValidation.fail("Clock speed must be positive.")
        },
        { value ->
          if (value <= MAX_CLOCK_VALUE) RationalValidation.pass()
          else RationalValidation.fail("Clock speed cannot exceed 250%.")
        })
    }
  }
}
