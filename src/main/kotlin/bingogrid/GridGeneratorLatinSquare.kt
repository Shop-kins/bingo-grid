package bingogrid

import java.util.*

class GridGeneratorLatinSquare(
        seed: Long
) : GridGenerateInterface {
    private val gridSize = 5
    private val rand = Random(seed)
    private val grid : List<Int> = (0 until gridSize).map { tens -> (1..gridSize).map { (tens * 10) + it } }.flatten().toMutableList()

    override fun generate(count: Int): List<List<Int>>{
        val piecePlacements = placePieces()
        val resultGrid: List<MutableList<Int>> = piecePlacements.map { it.toMutableList() }
        var piece = 0
        piecePlacements.forEach { p ->
            piece ++
            p.forEach{
                resultGrid[it/10][(it%10)-1] = piece
            }
        }
        return resultGrid
    }

    fun placePieces(count: Int = 0): List<List<Int>>{
        return try {
            var alterableGrid = grid.toMutableList()
            (0 until gridSize).map {
                val piecePlacement = placePiece(alterableGrid)
                alterableGrid.removeAll(piecePlacement)
                piecePlacement
            }
        } catch (e: Exception) {
            placePieces(count + 1)
        }
    }

    private tailrec fun placePiece(grid: MutableList<Int>, rowNum: Int = 0): MutableList<Int> {
        if(rowNum == gridSize - 1) return grid
        val position = randPosition(grid, rowNum)
        val newGrid = removeUnusableDirections(grid, position)
        return placePiece(newGrid, rowNum+1)
    }

    private fun randPosition(grid: MutableList<Int>, rowNum: Int): Int {
        val options = grid.filter { it-(rowNum*10) in 1..9 }
        return options[rand.nextInt(options.size)]
    }

    private fun removeUnusableDirections(grid: MutableList<Int>, position: Int): MutableList<Int> {
        val newGrid0 = removeRestOfRow(grid, position)
        val newGrid1 = removeUnusableDirection(newGrid0, position, 9)
        val newGrid2 = removeUnusableDirection(newGrid1, position, 10)
        return removeUnusableDirection(newGrid2, position, 11)
    }

    private tailrec fun removeUnusableDirection(grid: MutableList<Int>, position: Int, increment: Int) : MutableList<Int> {
        val newPosition = position + increment
        if(newPosition % 10 == 0 || newPosition % 10 > 5 || position/10 > 4) return grid
        if(grid.contains(newPosition))
            grid.remove(newPosition)
        return removeUnusableDirection(grid, newPosition, increment)
    }

    private fun removeRestOfRow(grid: MutableList<Int>, position: Int) = grid.filter{ it/10 != position/10 || it == position }.toMutableList()

}