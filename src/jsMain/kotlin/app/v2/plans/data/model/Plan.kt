package app.v2.plans.data.model

import app.data.Item
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q

@Serializable
data class Plan(
    val id: ULong,
    val title: String,
    val activeStep: Int = 0,
    val inputs: List<PlanInput> = listOf(),
)

@Serializable
data class PlanInput(
    val item: Item? = null,
    val amount: Rational = 0.q,
)
