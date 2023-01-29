package app.v2.data

import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

class InMemoryFactoryService : FactoryService {
    private val factories = mutableListOf<Factory>()

    override suspend fun createFactory(factory: Factory): Factory {
        check(factories.none { it.id == factory.id }) { "Factory #${factory.id} already exists." }
        factories.add(factory)
        return factory
    }

    override suspend fun getFactory(id: ULong): Factory {
        // Simulate RPC latency.
        delay(Random.nextInt(750, 3000).milliseconds)
        return factories.singleOrNull { it.id == id } ?: throwNotFound(id)
    }

    override suspend fun updateFactory(factory: Factory): Factory {
        val existing =
            factories.withIndex().singleOrNull { (_, it) -> it.id == factory.id } ?: throwNotFound(factory.id)
        factories[existing.index] = factory
        return factory
    }

    override suspend fun listFactories() = factories.toList()

    private fun throwNotFound(id: ULong): Nothing = throw IllegalArgumentException("Factory #$id does not exist.")
}
