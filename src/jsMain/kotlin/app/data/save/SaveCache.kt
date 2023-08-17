package app.data.save

import app.api.save.v1.Save
import app.data.common.ResourceCache

val SaveCache = ResourceCache(Save::name)
