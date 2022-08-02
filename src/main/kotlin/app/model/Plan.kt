package app.model

import kotlin.random.Random

data class Plan(
    private val title: String = "Production Plan",
    private val id: Int = Random.Default.nextInt(),
    private val inputs: List<PlanInput> = listOf(),
) {
    fun title() = title
    fun id() = id
    fun inputs() = inputs;
}
