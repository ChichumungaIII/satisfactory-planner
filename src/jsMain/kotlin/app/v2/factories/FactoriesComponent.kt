package app.v2.factories

import app.AppRoute
import app.v2.AppScope
import app.v2.common.layout.ZeroStateComponent
import app.v2.data.Factory
import app.v2.data.FactoryStoreContext
import app.v2.data.LoadState
import app.v2.data.SetFactory
import app.v2.data.service.FactoryServiceContext
import app.v2.frame.FrameMenuOptionsContext
import app.v2.frame.title.TitleContext
import csstype.ClassName
import kotlinx.coroutines.launch
import mui.material.Backdrop
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.CircularProgress
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.router.useNavigate
import react.useContext
import react.useEffectOnce
import react.useState
import kotlin.random.Random
import kotlin.random.nextULong

external interface FactoriesComponentProps : Props

val FactoriesComponent = FC<FactoriesComponentProps>("FactoriesComponent") { _ ->
    var title by useContext(TitleContext)
    var frameMenuOptions by useContext(FrameMenuOptionsContext)
    useEffectOnce {
        title = "All Factories"
        frameMenuOptions = listOf()
    }

    val (factories, updateFactories) = useContext(FactoriesContext)

    when (factories) {
        is LoadState.Loaded -> when (factories.data.size) {
            0 -> ZeroFactoriesPlaceholderComponent { }
            else -> FactoriesListComponent {
                this.factories = factories.data
            }
        }

        is LoadState.Loading -> ZeroStateComponent { CircularProgress { size = 80; thickness = 4.8 } }

        else -> updateFactories(LoadList)
    }
}

private val ZeroFactoriesPlaceholderComponent = FC<Props>("ZeroFactoriesPlaceholderComponent") { _ ->
    val navigate = useNavigate()
    val factoryService = useContext(FactoryServiceContext)
    val (_, updateStore) = useContext(FactoryStoreContext)
    val (_, updateFactories) = useContext(FactoriesContext)

    var creating by useState(false)

    ZeroStateComponent {
        Typography {
            variant = TypographyVariant.subtitle1
            +"It doesn't look like you've made any factories yet."
        }
        Button {
            className = ClassName("factories__create-button")

            variant = ButtonVariant.contained
            disabled = creating
            +"Create a factory"

            onClick = {
                creating = true
                AppScope.launch {
                    val factory = Factory(Random.nextULong(), "New Factory")
                    updateStore(SetFactory(factoryService.createFactory(factory)))
                    updateFactories(AddFactory(factory))
                    navigate.invoke(AppRoute.FACTORY.url("factoryId" to "${factory.id}"))
                }
            }
        }
    }

    Backdrop {
        open = creating
        CircularProgress { size = 80; thickness = 4.8 }
    }
}
