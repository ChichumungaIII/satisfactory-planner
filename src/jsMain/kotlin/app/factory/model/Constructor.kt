package app.factory.model

import kotlinx.serialization.Serializable

@Serializable
class Constructor : ProductionBuilding() {
    override val basePower get() = 4
}
