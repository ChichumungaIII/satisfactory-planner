package app.model

import kotlin.random.Random

data class PlanModel(
    private val title: String = "Production Plan",
    private val id: Int = Random.Default.nextInt(),
    private val inputs: List<PlanInputModel> = listOf(),
) {
    fun title() = title
    fun id() = id
    fun inputs() = inputs;
}
