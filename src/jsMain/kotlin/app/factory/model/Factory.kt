package app.factory.model

import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class Factory(
    val title: String = "New Factory",
    val id: Int = Random.nextInt(),
    val container: FactoryUnitContainer = FactoryUnitContainer(),
)
