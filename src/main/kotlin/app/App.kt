package app

import app.model.PlanModel
import app.themes.ThemeModule
import react.FC
import react.Props
import react.StateInstance
import react.createContext
import react.router.Outlet
import react.useState

val PlansContext = createContext<StateInstance<List<PlanModel>>>()

val App = FC<Props> {
    val plans = useState(listOf<PlanModel>())

    ThemeModule {
        PlansContext(plans) {
            Outlet {}
        }
    }
}
