package app

import app.themes.ThemeModule
import react.FC
import react.Props
import react.createElement
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter

val Routing = FC<Props> {
    BrowserRouter {
        Routes {
            Route {
                path = "/"
                element = createElement(App)

                Route {
                    path = "production"
                    element = createElement(ProductionPlans)
                }
                Route {
                    path = "factories"
                    element = createElement(Factories)
                }
            }
        }
    }
}
