package util.math

/**
 * A MutableMatrix is a variable-size 2D grid containing [T].
 *
 * @property cells
 * @constructor
 */
class MutableMatrix<T> private constructor(
  private val cells: MutableList<MutableList<T>>,
) {
  companion object {
    /** Creates a new [rows] by [columns] [MutableMatrix] filled with [initial]. */
    fun <T> create(rows: Int, columns: Int, initial: T) = create(rows, columns) { _, _ -> initial }

    /** Creates a new [rows] by [columns] [MutableMatrix] where initial cell contents are computed by [filler]. */
    fun <T> create(
      rows: Int,
      columns: Int,
      filler: (row: Int, column: Int) -> T,
    ): MutableMatrix<T> {
      val cells = mutableListOf<MutableList<T>>()
      for (row in 0 until rows) {
        cells.add(mutableListOf())
        for (column in 0 until columns) {
          cells[row].add(filler(row, column))
        }
      }
      return MutableMatrix(cells)
    }
  }

  /* Matrix information */

  fun rows() = cells.size
  fun columns() = if (rows() == 0) 0 else cells[0].size

  /* Cell manipulation */

  fun get(row: Int, column: Int) = cells[row][column]
  fun set(row: Int, column: Int, value: T) = update(row, column) { value }
  fun update(row: Int, column: Int, transform: (T) -> T) {
    cells[row][column] = transform(cells[row][column])
  }

  /* Matrix manipulation */

  fun row(row: Int) = cells[row]

  fun addRow(row: Int, initial: T) = addRow(row) { initial }
  fun addRow(row: Int, filler: (column: Int) -> T) {
    cells.add(row, mutableListOf())
    for (column in 0 until columns()) {
      cells[row].add(filler(column))
    }
  }

  fun column(column: Int) = cells.map { it[column] }

  fun addColumn(column: Int, initial: T) = addColumn(column) { initial }
  fun addColumn(column: Int, filler: (row: Int) -> T) {
    for (row in 0 until rows()) {
      cells[row].add(column, filler(row))
    }
  }
}
