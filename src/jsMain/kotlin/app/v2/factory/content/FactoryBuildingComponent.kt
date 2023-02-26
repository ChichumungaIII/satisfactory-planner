package app.v2.factory.content

import app.util.PropsDelegate
import app.v2.common.input.BUILDINGS
import app.v2.common.input.BuildingAutocomplete
import app.v2.common.input.RecipeAutocomplete
import app.v2.data.FactoryLeaf
import react.FC
import react.Props

external interface FactoryBuildingComponentProps : Props {
    var settings: FactoryLeaf
    var setSettings: (FactoryLeaf) -> Unit
}

val FactoryBuildingComponent = FC<FactoryBuildingComponentProps>("FactoryBuildingComponent") { props ->
    var settings by PropsDelegate(props.settings, props.setSettings)
    val (building, recipe, clock) = settings

    BuildingAutocomplete {
        model = building
        setModel = { next ->
            settings = settings.copy(
                building = next,
                recipe = next?.let { new -> recipe?.takeIf { new.recipes.contains(it) } })
        }
    }

    RecipeAutocomplete {
        model = recipe
        setModel = { next ->
            settings = settings.copy(
                recipe = next,
                building = building ?: next?.let {
                    BUILDINGS.associateWith { it.recipes }.filterValues { it.contains(next) }.keys.singleOrNull()
                })
        }

        this.building = building
    }
}
