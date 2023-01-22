package app.v2.factory

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
import react.router.useParams
import react.useContext
import react.useEffect
import react.useState

private val Scope = MainScope()

external interface FactoryRouteComponentProps : Props

val FactoryRouteComponent = FC<FactoryRouteComponentProps>("FactoryRouteComponent") { props ->
    val navigate = useNavigate()

    val factoryService = useContext(FactoryServiceContext)
    val factoryId = useParams()["factoryId"]!!.toULong()

    var factory by useState<Factory?>(null)
    var loading by useState(true)

    useEffect {
        Scope.launch {
            try {
                factory = factoryService.getFactory(factoryId)
            } finally {
                loading = false
            }
        }
    }

    if (loading) {
        ZeroStateComponent { CircularProgress {} }
    } else {
        when (val loaded = factory) {
            null -> ZeroStateComponent {
                Typography {
                    variant = TypographyVariant.subtitle1
                    +"Factory #$factoryId could not be found."
                }
                Button {
                    className = ClassName("factory-route__return-button")

                    variant = ButtonVariant.contained
                    +"Factory List"

                    onClick = { navigate("..") }
                }
            }

            else -> FactoryComponent { this.factory = loaded }
        }
    }
}
