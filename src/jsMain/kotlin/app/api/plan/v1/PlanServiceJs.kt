package app.api.plan.v1

import react.FC
import react.PropsWithChildren
import react.createContext

object PlanServiceJs {
  val Context = createContext<PlanService>()
  val Provider = FC<PropsWithChildren>("PlanServiceJs") { props ->
    Context.Provider {
      value = PlanServiceLocalStorage
      +props.children
    }
  }
}
