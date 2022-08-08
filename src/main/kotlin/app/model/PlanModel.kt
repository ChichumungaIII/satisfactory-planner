package app.model

import kotlin.random.Random

data class PlanModel(
    private val title: String = "Production Plan",
    private val id: Int = Random.Default.nextInt(),
    private val inputs: List<PlanInputModel> = listOf(),
    private val products: List<PlanProductModel> = listOf(),
) {
    fun title() = title
    fun id() = id
    fun inputs() = inputs
    fun products() = products

    fun setTitle(title: String) =
        copy(title = title)

    fun addInput(input: PlanInputModel) =
        copy(inputs = inputs + input)

    fun setInput(i: Int, input: PlanInputModel) =
        copy(inputs = inputs.subList(0, i) + input + inputs.subList(i + 1, inputs.size))

    fun removeInput(i: Int) =
        copy(inputs = inputs.subList(0, i) + inputs.subList(i + 1, inputs.size))

    fun addProduct(product: PlanProductModel) =
        copy(products = products + product)

    fun setProduct(i: Int, product: PlanProductModel) =
        copy(products = products.subList(0, i) + product + products.subList(i + 1, products.size))

    fun removeProduct(i: Int) =
        copy(products = products.subList(0, i) + products.subList(i + 1, products.size))
}
