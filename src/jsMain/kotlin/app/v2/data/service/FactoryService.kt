package app.v2.data.service

import app.v2.data.Factory

interface FactoryService {
  suspend fun createFactory(factory: Factory): Factory
  suspend fun getFactory(id: ULong): Factory
  suspend fun updateFactory(factory: Factory): Factory
  suspend fun deleteFactory(id: ULong)
  suspend fun listFactories(): List<Factory>
}
