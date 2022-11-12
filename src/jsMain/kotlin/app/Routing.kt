package app

import app.common.layout.RootComponent
import app.factory.FactoriesComponent
import app.plan.Plans
import react.FC
import react.Props
import react.create
import react.router.Navigate
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter

val Routing = FC<Props>("Routing") {
    BrowserRouter {
        Routes {
            Route {
                path = "/"
                element = App.create()

                Route {
                    path = ""
                    element = Navigate.create { to = "/production" }
                }
                Route {
                    path = "production"
                    element = RootComponent.create {
                        title = "Production Plans"
                        Plans {}
                    }
                }
                Route {
                    path = "factories"
                    element = RootComponent.create {
                        title = "Factories"
                        FactoriesComponent {}
                    }
                }
            }
        }
    }
}
