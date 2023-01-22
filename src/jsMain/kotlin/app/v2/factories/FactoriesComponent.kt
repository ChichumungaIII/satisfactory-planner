package app.v2.factories

import app.v2.common.layout.ZeroStateComponent
import app.v2.data.Factory
import app.v2.data.FactoryServiceContext
import csstype.ClassName
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
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

private val Scope = MainScope()

external interface FactoriesComponentProps : Props

val FactoriesComponent = FC<FactoriesComponentProps>("FactoriesComponent") { props ->
    val navigate = useNavigate()

    val factoryService = useContext(FactoryServiceContext)

    var factories by useState(listOf<Factory>())
    var loading by useState(true)

    useEffectOnce {
        Scope.launch {
            factories = factoryService.listFactories()
            loading = false
        }
    }

    if (loading) {
        ZeroStateComponent { CircularProgress { size = 80; thickness = 4.8 } }
    } else {
        when (factories.size) {
            0 -> ZeroStateComponent {
                Typography {
                    variant = TypographyVariant.subtitle1
                    +"It doesn't look like you've made any factories yet."
                }
                Button {
                    className = ClassName("factories__create-button")

                    variant = ButtonVariant.contained
                    +"Create a factory"

                    onClick = {
                        Scope.launch {
                            val factory = Factory(Random.nextULong(), "New Factory")
                            factoryService.createFactory(factory)
                            navigate("${factory.id}")
                        }
                    }
                }
            }

            else -> ZeroStateComponent { +"TODO" }
        }
    }
}
