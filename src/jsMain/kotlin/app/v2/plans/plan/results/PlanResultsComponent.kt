package app.v2.plans.plan.results

import app.data.recipe.Recipe
import mui.material.Box
import mui.material.Stack
import react.FC
import react.Props
import util.math.Rational
import util.math.q

external interface PlanResultsComponentProps : Props {
    var results: Map<Recipe, Rational>?
}

val PlanResultsComponent = FC<PlanResultsComponentProps>("PlanResultsComponent") { props ->
    props.results?.also { results ->
        Stack {
            results.filterNot { (_, rate) -> rate == 0.q }.forEach { (recipe, rate) ->
                Box { +"${recipe.displayName} @${rate * 100.q}%" }
            }
        }
    } ?: Box {
        +"Plan not yet computed."
    }
}
