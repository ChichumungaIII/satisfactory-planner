package app.data.save

import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.data.common.ResourceCache

val SaveCache = ResourceCache<SaveName, Save>()
