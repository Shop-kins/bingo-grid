package bingogrid

import bingogrid.Sheets.SheetsClient
import bingogrid.Sheets.SheetsData
import java.util.*

class GridTaskerLatinSquare(
        seed: Long,
        sheetsClient: SheetsClient
) {

    private val rand = Random(seed)
    private val oneTasks = sheetsClient.getColumnData("A")
    private val twoTasks = sheetsClient.getColumnData("B")
    private val threeTasks = sheetsClient.getColumnData("C")
    private val fourTasks = sheetsClient.getColumnData("D")
    private val fiveTasks = sheetsClient.getColumnData("E")

    fun fillGrid(grid: List<List<Int>>): List<List<String>> {
        canTaskGrid(grid)
        return grid.map {
            it.map {
                when(it){
                    1 -> generateTask(oneTasks)
                    2 -> generateTask(twoTasks)
                    3 -> generateTask(threeTasks)
                    4 -> generateTask(fourTasks)
                    else -> generateTask(fiveTasks)
                }
            }
        }
    }

    private fun generateTask(taskObject: SheetsData): String {
        val position = rand.nextInt(taskObject.values.size)
        val task = taskObject.values[position].first()
        taskObject.values.removeAt(position)
        return task
    }

    private fun canTaskGrid(grid: List<List<Int>>){
        if(oneTasks.values.size < 5 || twoTasks.values.size < 5 || threeTasks.values.size < 5 || fourTasks.values.size < 5 || fiveTasks.values.size < 5){
            throw IllegalStateException("Not Enough tasks to build grid, need five of each number")
        }
    }



}