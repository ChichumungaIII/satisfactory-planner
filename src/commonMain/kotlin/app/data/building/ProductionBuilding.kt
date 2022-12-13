package app.data.building

import kotlinx.serialization.Serializable
import util.math.q

@Serializable
sealed interface ProductionBuilding : Building {
    override val generation get() = 0.q
}
