package app.routes.editsave

import app.api.save.v1.Save
import react.FC
import react.Props

external interface EditSavePageProps : Props {
  var save: Save
}

val EditSavePage = FC<EditSavePageProps>("EditSavePage") { props ->
  +props.save.displayName
}
