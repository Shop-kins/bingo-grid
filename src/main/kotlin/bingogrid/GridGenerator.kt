package bingogrid

import java.util.*

class GridGenerator(
        seed: Long,
        private val gridSize: Int = 5,
        private val difficulty: Int = 2
) : GridGenerateInterface {
    private val rand = Random(seed)

    init {
        if(difficulty > gridSize){
            throw IllegalStateException("Difficulty can not be greater than grid size")
        }
        if(gridSize > 20){
            throw IllegalStateException("Grid size too unwieldy")
        }
    }

    override fun generate(count: Int): List<List<Int>> {
        val gridRows = mutableListOf<List<Int>>()
        (1..gridSize).forEach {
            gridRows += generateGridRow(gridRows)
        }
        return if(isDiagonalFine(gridRows) || count == 5) gridRows else generate(count + 1)
    }

    private fun generateGridRow(gridRows: List<List<Int>>): List<Int> {
        val safe = safeColumns(gridRows)
        val nextRow = generateZeroList()
        for (i in 1..difficulty) {
            val necessary = necessaryColumn(safe, gridRows)
            if(necessary != null) {
                nextRow[necessary] = 1
                safe.removeAll { it == necessary }
            } else {
                val columnNum = safe[rand.nextInt(safe.size)]
                nextRow[columnNum] = 1
                safe.removeAll { it == columnNum }
            }
        }
        return nextRow
    }

    private fun generateZeroList(): MutableList<Int> = (1..gridSize).map { 0 }.toMutableList()

    private fun safeColumns(gridRows: List<List<Int>>): MutableList<Int> {
        val safeList = mutableListOf<Int>()
        for (i in 0 until gridSize) {
            for (j in 0 until (difficulty - gridRows.sumBy { it[i] })) {
                safeList.add(i)
            }
        }
        return safeList
    }

    private fun necessaryColumn(safe: List<Int>, gridRows: List<List<Int>>): Int? {
        val currentRowCount = gridRows.size
        if(currentRowCount == 0) return null
        for (i in 0 until gridSize) {
            if(safe.count { it == i } == (gridSize-currentRowCount)) return i
        }
        return null
    }

    private fun isDiagonalFine(grid: List<List<Int>>): Boolean {
        val diagDifficulty = (0 until gridSize).sumBy { grid[it][it] }
        val diag2Difficulty = (1..gridSize).sumBy { grid[it-1][gridSize-it] }
        return !(diagDifficulty < difficulty || diag2Difficulty < difficulty)
    }


}
