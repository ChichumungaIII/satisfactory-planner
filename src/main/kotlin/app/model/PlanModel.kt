package app.model

import kotlin.random.Random

data class PlanModel(
    private val title: String = "Production Plan",
    private val id: Int = Random.Default.nextInt(),
    private val inputs: List<PlanInputModel> = listOf(),
) {
    fun title() = title
    fun id() = id
    fun inputs() = inputs

    fun setTitle(newTitle: String) =
        copy(title = newTitle)

    fun addInput(input: PlanInputModel) =
        copy(inputs = inputs + input)

    fun setInput(i: Int, input: PlanInputModel) =
        copy(inputs = inputs.subList(0, i) + input + inputs.subList(i + 1, inputs.size))
}
