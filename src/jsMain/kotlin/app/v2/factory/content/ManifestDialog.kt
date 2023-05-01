package app.v2.factory.content

import app.data.building.Extractor
import app.data.building.Manufacturer
import app.v2.data.FactoryNode
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.Dialog
import mui.material.DialogActions
import mui.material.DialogContent
import mui.material.DialogTitle
import mui.material.Table
import mui.material.TableBody
import mui.material.TableCell
import mui.material.TableContainer
import mui.material.TableHead
import mui.material.TableRow
import react.FC
import react.Props
import react.dom.html.TdAlign

external interface ManifestDialogProps : Props {
  var open: Boolean
  var onClose: () -> Unit

  var node: FactoryNode
}

val ManifestDialog = FC<ManifestDialogProps>("ManifestDialog") { props ->
  Dialog {
    open = props.open

    DialogTitle { +"Factory Manifest" }

    DialogContent {
      TableContainer {
        Table {

          Extractor.values()
            .associateWith { props.node.buildings[it] ?: 0.toUInt() }
            .filter { (_, count) -> count > 0.toUInt() }
            .takeIf { it.isNotEmpty() }
            ?.also { extractors ->
              TableHead {
                TableRow {
                  TableCell { +"Count" }
                  TableCell { +"Extractor" }
                }
              }

              TableBody {
                extractors.forEach { (extractor, count) ->
                  TableRow {
                    TableCell {
                      align = TdAlign.center
                      +"$count"
                    }
                    TableCell { +extractor.displayName }
                  }
                }
              }
            }

          Manufacturer.values()
            .associateWith { props.node.buildings[it] ?: 0.toUInt() }
            .filter { (_, count) -> count > 0.toUInt() }
            .takeIf { it.isNotEmpty() }
            ?.also { manufacturers ->
              TableHead {
                TableRow {
                  TableCell { +"Count" }
                  TableCell { +"Manufacturer" }
                }
              }

              TableBody {
                manufacturers.forEach { (manufacturer, count) ->
                  TableRow {
                    TableCell {
                      align = TdAlign.center
                      +"$count"
                    }
                    TableCell { +manufacturer.displayName }
                  }
                }
              }
            }

        }
      }
    }

    DialogActions {
      Button {
        variant = ButtonVariant.outlined
        onClick = { props.onClose() }
        +"Close"
      }
    }
  }
}
