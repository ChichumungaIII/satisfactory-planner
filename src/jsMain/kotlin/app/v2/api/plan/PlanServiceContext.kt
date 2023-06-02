package app.v2.api.plan

import react.FC
import react.PropsWithChildren
import react.createContext

val PlanServiceContext = createContext<PlanService>()
val PlanServiceContextProvider = FC<PropsWithChildren>("PlanServiceContextProvider") {
  PlanServiceContext(LocalStoragePlanService()) { +it.children }
}
