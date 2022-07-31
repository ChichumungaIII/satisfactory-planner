package app

import app.model.Plan
import app.themes.ThemeModule
import react.FC
import react.Props
import react.StateInstance
import react.createContext
import react.router.Outlet
import react.useState

val PlansContext = createContext<StateInstance<List<Plan>>>()

val App = FC<Props> {
    val plans = useState(listOf<Plan>())

    ThemeModule {
        PlansContext(plans) {
            Outlet {}
        }
    }
}
