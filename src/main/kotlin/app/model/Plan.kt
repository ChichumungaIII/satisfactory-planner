package app.model

import kotlin.random.Random

data class Plan(
    private val title: String = "Production Plan",
    private val id: Int = Random.Default.nextInt(),
) {
    fun title() = title
    fun id() = id
}
