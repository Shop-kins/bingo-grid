package bingogrid

import org.testng.Assert
import org.testng.annotations.Test

class GridGeneratorTest{


    @Test(expectedExceptions = arrayOf(IllegalStateException::class))
    fun `difficulty cannot be greater than grid size`(){
        GridGenerator(1L, 5, 7)
    }

    @Test
    fun `new instance wth same input set always produces same result`(){
        val gg1 = GridGenerator(1L, 5, 2)
        val gg2 = GridGenerator(1L, 5, 2)
        Assert.assertEquals(gg1.generate(), gg2.generate())
    }

    @Test
    fun `each row and column has the correct difficulty`(){
        val gridSize = 7
        val difficulty = 3
        val gg1 = GridGenerator(1L, gridSize, difficulty)
        val grid = gg1.generate()
        grid.forEach {
            Assert.assertEquals(it.sum(), difficulty)
        }
        for (i in 0 until gridSize){
            Assert.assertEquals(grid.sumBy { it[i] }, difficulty)
        }
    }

}