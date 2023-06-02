package app.v2.api.factory

import app.v2.data.factory.Factory

interface FactoryService {
  suspend fun createFactory(factory: Factory): Factory
  suspend fun getFactory(id: ULong): Factory
  suspend fun updateFactory(factory: Factory): Factory
  suspend fun deleteFactory(id: ULong)
  suspend fun listFactories(): List<Factory>
}
