package app

import app.factory.Factories
import app.plan.ProductionPlans
import react.FC
import react.Props
import react.create
import react.createElement
import react.router.Navigate
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
                    path = ""
                    element = Navigate.create { to = "/production" }
                }
                Route {
                    path = "production"
                    element = ProductionPlans.create()
                }
                Route {
                    path = "factories"
                    element = Factories.create()
                }
            }
        }
    }
}