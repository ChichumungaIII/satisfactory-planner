package app.v2

import app.themes.ThemeModule
import app.v2.common.layout.DrawerContextProvider
import app.v2.data.FactoryStoreContextProvider
import app.v2.data.service.FactoryServiceContextProvider
import app.v2.factories.FactoriesContextProvider
import app.v2.factory.FactoryContextProvider
import app.v2.plans.data.PlanContextProvider
import app.v2.plans.data.PlanStoreContextProvider
import app.v2.plans.data.PlansListContextProvider
import app.v2.plans.data.model.PlanServiceContextProvider
import kotlinx.coroutines.MainScope
import react.FC
import react.Props
import react.PropsWithChildren
import react.ReactElement
import react.create
import react.router.Outlet

external interface AppV2Props : Props

val AppScope = MainScope()

val AppV2 = FC<AppV2Props>("AppV2") {
  +listOf(
    // Global theme
    ThemeModule,
    // Global data
    PlanServiceContextProvider,
    PlanStoreContextProvider,
    FactoryServiceContextProvider,
    FactoryStoreContextProvider,
    // Frame data
    DrawerContextProvider,
    // Specific data
    PlansListContextProvider,
    PlanContextProvider,
    FactoriesContextProvider,
    FactoryContextProvider,
  ).nest(Outlet.create {})
}

private fun List<FC<PropsWithChildren>>.nest(element: ReactElement<*>): ReactElement<*> =
  foldRight(element) { parent, child -> parent.create { +child } }
