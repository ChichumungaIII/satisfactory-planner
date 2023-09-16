package app

import app.routes.createsave.CreateSaveRoute
import app.routes.home.HomeRoute
import app.routes.save.SaveRoute
import app.v2.AppV2
import app.v2.factories.FactoriesComponent
import app.v2.factory.FactoryRouteComponent
import app.v2.plans.PlansRouteComponent
import app.v2.plans.plan.PlanRouteComponent
import react.FC
import react.Props
import react.ReactElement
import react.create
import react.router.IndexRoute
import react.router.Navigate
import react.router.PathRoute
import react.router.Routes
import react.router.dom.BrowserRouter

enum class AppRoute(
  val path: String,
  val parent: AppRoute?,
  val element: ReactElement<*>? = null,
  val index: (() -> ReactElement<*>)? = null,
) {
  ROOT("", parent = null, index = { redirect(V2) }),

  V2("v2", ROOT, AppV2.create(), index = { redirect(V2_FACTORIES) }),
  V2_FACTORIES("factories", V2, index = { FactoriesComponent.create() }),
  V2_FACTORY(":factoryId", V2_FACTORIES, FactoryRouteComponent.create()),
  V2_PLANS("plans", V2, index = { PlansRouteComponent.create() }),
  V2_PLAN(":planId", V2_PLANS, PlanRouteComponent.create()),

  V3("v3", ROOT, AppV3.create(), index = { HomeRoute.create() }),
  V3_SAVES("saves", V3),
  V3_SAVES_CREATE("create", V3_SAVES, CreateSaveRoute.create()),
  V3_SAVE(":${RouteParams.SAVE_ID.key}", V3_SAVES, index = { SaveRoute.create() }),
  V3_SAVE_PLANS("plans", V3_SAVE);

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

private fun render(route: AppRoute): ReactElement<*> = PathRoute.create {
  path = route.path
  route.element?.also { element = it }

  route.index?.also { +IndexRoute.create { element = it(); index = true } }
  AppRoute.entries.filter { it.parent == route }.forEach { +render(it) }
}

private fun redirect(route: AppRoute) = Navigate.create { to = route.url }
