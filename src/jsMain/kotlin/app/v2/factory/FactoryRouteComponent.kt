package app.v2.factory

import react.FC
import react.Props
import react.router.useParams
import react.useState

external interface FactoryRouteComponentProps : Props

val FactoryRouteComponent = FC<FactoryRouteComponentProps>("FactoryRouteComponent") { props ->
    val factoryId = useParams()["factoryId"]!!.toULong()

    val factory by useState(Factory(factoryId, "Factory #$factoryId"))

    FactoryComponent { this.factory = factory }
}
