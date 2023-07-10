package app.data.save

import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.data.common.ResourceCache

object SaveCache : ResourceCache<SaveName, Save>() {
  override fun getName(resource: Save) = resource.name
}
