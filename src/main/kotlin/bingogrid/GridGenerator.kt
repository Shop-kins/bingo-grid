package bingogrid

import java.util.*

class GridGenerator(val seed: Double) {

    private val gridSize: Int = 5
    private val difficulty: Int = 4

    fun generate(): List<List<Int>> {
        val gridRows = mutableListOf<List<Int>>()
        (1..gridSize).forEach {
            gridRows += generateGridRow(gridRows)
        }
        return gridRows
    }

    private fun generateGridRow(gridRows: List<List<Int>>): List<Int> {
        val rand = Random(seed.toLong())
        val safe = safeColumns(gridRows)
        val nextRow = generateZeroList()
        for (i in 1..difficulty) {
            val necessary = necessaryColumn(gridRows, safe)
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

    private fun necessaryColumn(gridRows: List<List<Int>>, safe: List<Int>): Int? {
        for (i in 0 until gridSize) {
            if(safe.contains(i)){
                if (difficulty - gridRows.sumBy { it[i] } == gridSize) return i
            }
        }
        return null
    }


}