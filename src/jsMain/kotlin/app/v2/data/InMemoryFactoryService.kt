package app.v2.data

import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

class InMemoryFactoryService : FactoryService {
    private val factories = mutableListOf<Factory>()

    override suspend fun createFactory(factory: Factory): Factory {
        // Simulate RPC latency.
        check(factories.none { it.id == factory.id }) { "Factory #${factory.id} already exists." }
        factories.add(factory)
        return factory.also { lag() }
    }

    override suspend fun getFactory(id: ULong) =
        (factories.singleOrNull { it.id == id } ?: throwNotFound(id)).also { lag() }

    override suspend fun updateFactory(factory: Factory): Factory {
        val existing =
            factories.withIndex().singleOrNull { (_, it) -> it.id == factory.id } ?: throwNotFound(factory.id)
        factories[existing.index] = factory
        return factory.also { lag() }
    }

    override suspend fun listFactories() = factories.toList().also { lag() }

    private suspend fun lag() = delay(Random.nextInt(750, 3500).milliseconds)

    private fun throwNotFound(id: ULong): Nothing = throw IllegalArgumentException("Factory #$id does not exist.")
}
