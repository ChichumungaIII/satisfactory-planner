package app.v2.plans.data

import app.v2.plans.data.model.Plan
import react.FC
import react.PropsWithChildren
import react.ReducerInstance
import react.createContext
import react.useReducer

sealed interface PlanStoreContextAction

val PlanStoreContext = createContext<ReducerInstance<Map<ULong, Plan>, PlanStoreContextAction>>()

data class StorePlan(val plan: Plan) : PlanStoreContextAction
data class StorePlans(val plans: List<Plan>) : PlanStoreContextAction

val PlanStoreContextProvider = FC<PropsWithChildren>("PlanStoreContextProvider") { props ->
    val store = useReducer<Map<ULong, Plan>, PlanStoreContextAction>({ state, action ->
        val next = state.toMutableMap()
        when (action) {
            is StorePlan -> next[action.plan.id] = action.plan
            is StorePlans -> next.putAll(action.plans.associateBy { it.id })
        }
        next.toMap()
    }, initialState = mapOf())

    PlanStoreContext(store) { +props.children }
}
