package bingogrid

import java.util.*

class GridGenerator(val seed: Double){

    private val gridSize: Int = 5

    fun generate(): List<List<Int>>{
        val first = numToList((seed*10).toInt())
        val second = numToList(((1-seed)*10).toInt())
        val third = generateComplexRow(first, second)
        val fourth = generateComplexRow(first, second, third)
        val fifth = generateComplexRow(first, second, third, fourth)
        return listOf(first, second, third, fourth, fifth)
    }



    fun numToList(num: Int): List<Int> =
        when (num) {
            0 -> 3
            1 -> 5
            2 -> 6
            3 -> 9
            4 -> 10
            5 -> 12
            6 -> 17
            7 -> 18
            8 -> 20
            9 -> 24
            else -> 3
        }.toString(2).padEnd(5, '0').map(Character::getNumericValue)

    private fun generateComplexRow(vararg numList: List<Int>): List<Int>{
        val rand = Random(seed.toLong())
        val safe = safeColumns(*numList)
        val randOne = safe[rand.nextInt(safe.size)]
        safe.removeAll{ it == randOne}
        val randTwo = safe[rand.nextInt(safe.size)]
        val nextRow = generateZeroList()
        nextRow[randOne] = 1
        nextRow[randTwo] = 1
        return nextRow
    }

    private fun generateZeroList(): MutableList<Int> = (1..gridSize).map{0}.toMutableList()

    private fun safeColumns(vararg numList: List<Int>): MutableList<Int>{
        val safeList = mutableListOf<Int>()
        for(i in 1..gridSize-1){
            if(numList.sumBy { it[i] } < 2) safeList.add(i)
            if(numList.sumBy { it[i] } < 1) safeList.add(i)
        }
        return safeList
    }




}