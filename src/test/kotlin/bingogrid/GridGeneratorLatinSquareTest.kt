package bingogrid

import org.testng.Assert
import org.testng.annotations.Test

class GridGeneratorLatinSquareTest{

    @Test
    fun `every grid row has one of 1 to grid size in it`(){
        val size = 5
        val grid = GridGeneratorLatinSquare(1L).generate()
        grid.forEach {
            Assert.assertEquals(it.sorted(), (1..size).map { it })
        }
    }

    @Test
    fun `every grid column has one of 1 to grid size in it`(){
        val size = 5
        val grid = GridGeneratorLatinSquare(1L).generate()
        val flippedGrid = (0 until size).map {r ->
            (0 until size).map {c ->
                grid[r][c]
            }
        }
        flippedGrid.forEach {
            Assert.assertEquals(it.sorted(), (1..size).map { it })
        }
    }

    @Test
    fun `TR to BL diagonal has one of 1 to grid size in it`(){
        val size = 5
        val grid = GridGeneratorLatinSquare(1L).generate()
        val diagonal = (0 until size).map {
                grid[it][it]
        }
        Assert.assertEquals(diagonal.sorted(), (1..size).map { it })
    }

    @Test
    fun `BL to TR diagonal has one of 1 to grid size in it`(){
        val size = 5
        val grid = GridGeneratorLatinSquare(129846L).generate()
        val diagonal = (1..size).map { grid[it-1][size-it] }
        Assert.assertEquals(diagonal.sorted(), (1..size).map { it })
    }
}
