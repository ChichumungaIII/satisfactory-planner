package app

import app.common.layout.RootComponent
import app.v2.AppV2
import app.v2.factories.FactoriesComponent
import app.v2.factory.FactoryRouteComponent
import app.v2.plans.PlansRouteComponent
import react.FC
import react.Props
import react.ReactElement
import react.create
import react.router.Navigate
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter

enum class AppRoute(
    val path: String,
    val parent: AppRoute?,
    val element: (() -> ReactElement<*>)? = null,
    val default: (() -> ReactElement<*>)? = null,
    val redirect: (() -> String)? = null,
) {
    ROOT("", parent = null, redirect = { V1.url }),

    V1("v1", ROOT, { App.create {} }, redirect = { V2.url }),
    V1_FACTORIES("factories", V1, {
        RootComponent.create {
            title = "Factories"
            app.factory.FactoriesComponent {}
        }
    }),
    V1_PRODUCTION("production", V1, {
        RootComponent.create {
            title = "Production Plans"
            app.plan.Plans {}
        }
    }),

    V2("v2", ROOT, { AppV2.create {} }, redirect = { FACTORIES.url }),
    FACTORIES("factories", V2, default = { FactoriesComponent.create {} }),
    FACTORY(":factoryId", FACTORIES, { FactoryRouteComponent.create {} }),
    PLANS("plans", V2, default = { PlansRouteComponent.create {} });

    val url = url()
    fun url(vararg pairs: Pair<String, String>) = url(mapOf(*pairs))

    private fun url(vars: Map<String, String>): String =
        segment(vars).let { segment -> parent?.let { "${it.url(vars)}/$segment" } ?: segment }

    private fun segment(vars: Map<String, String>): String =
        path.takeIf { it.startsWith(":") }?.let { vars[it.substring(1)] } ?: path
}

val Routing = FC<Props>("Routing") {
    BrowserRouter { Routes { +render(AppRoute.ROOT) } }
}

private fun render(route: AppRoute): ReactElement<*> = Route.create {
    path = route.path
    route.element?.also { element = it() }
    route.default?.also { +Route.create { path = ""; element = it() } }
    route.redirect?.also { +Route.create { path = ""; element = Navigate.create { to = it() } } }

    AppRoute.values().filter { it.parent == route }.map { render(it) }.forEach { +it }
}
