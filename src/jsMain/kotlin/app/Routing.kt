package app

import app.common.layout.RootComponent
import app.plan.Plans
import app.v2.AppV2
import app.v2.factories.FactoriesComponent
import app.v2.factory.FactoryRouteComponent
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.create
import react.router.Navigate
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter

enum class AppRoutes(
    val segment: String,
    parent: AppRoutes? = null,
) {
    ROOT(""),
    V1("v1", ROOT),
    V1_FACTORIES("factories", V1),
    V1_PRODUCTION("production", V1),
    V2("v2"),
    FACTORIES("factories", V2),
    PLANS("plans", V2);

    val path: String = "${parent?.let { it.path + "/" } ?: ""}$segment"
}

val Routing = FC<Props>("Routing") {
    BrowserRouter {
        Routes {
            Route {
                path = AppRoutes.ROOT.segment
                Route { path = ""; element = Navigate.create { to = AppRoutes.V1.segment } }

                Route {
                    path = AppRoutes.V1.segment
                    element = App.create()
                    Route { path = ""; element = Navigate.create { to = AppRoutes.V1_FACTORIES.segment } }

                    Route {
                        path = AppRoutes.V1_PRODUCTION.segment
                        element = RootComponent.create {
                            title = "Production Plans"
                            Plans {}
                        }
                    }
                    Route {
                        path = AppRoutes.V1_FACTORIES.segment
                        element = RootComponent.create {
                            title = "Factories"
                            app.factory.FactoriesComponent {}
                        }
                    }
                }

                Route {
                    path = AppRoutes.V2.segment
                    element = AppV2.create {}
                    Route { path = ""; element = Navigate.create { to = AppRoutes.FACTORIES.segment } }

                    Route {
                        path = AppRoutes.FACTORIES.segment

                        Route {
                            path = ""
                            element = FactoriesComponent.create {}
                        }
                        Route {
                            path = ":factoryId"
                            element = FactoryRouteComponent.create {}
                        }
                    }
                    Route {
                        path = AppRoutes.PLANS.segment
                        element = Typography.create {
                            variant = TypographyVariant.h2
                            +"Plans"
                        }
                    }
                }
            }
        }
    }
}
