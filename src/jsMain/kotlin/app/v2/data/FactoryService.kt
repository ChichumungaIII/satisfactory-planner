package app.v2.data

interface FactoryService {
    suspend fun createFactory(factory: Factory): Factory
    suspend fun getFactory(id: ULong): Factory
    suspend fun updateFactory(factory: Factory): Factory
    suspend fun listFactories(): List<Factory>
}
