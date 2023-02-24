package app.v2.factory.content

import app.data.building.Building
import app.data.recipe.Recipe
import app.util.PropsDelegate
import app.v2.common.input.BuildingAutocomplete
import app.v2.data.FactoryLeaf
import react.FC
import react.Props
import util.math.Rational

external interface FactoryBuildingComponentProps : Props {
    var settings: FactoryLeaf

    var setBuilding: (Building?) -> Unit
    var setRecipe: (Recipe?) -> Unit
    var setClock: (Rational) -> Unit
}

val FactoryBuildingComponent = FC<FactoryBuildingComponentProps>("FactoryBuildingComponent") { props ->
    var building by PropsDelegate(props.settings.building, props.setBuilding)
    var recipe by PropsDelegate(props.settings.recipe, props.setRecipe)
    var clock by PropsDelegate(props.settings.clock, props.setClock)

    BuildingAutocomplete {
        model = building
        setModel = { next -> building = next }
    }
}
