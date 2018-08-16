package bingogrid

import bingogrid.Sheets.SheetsClient
import bingogrid.Sheets.SheetsData
import java.util.*

class GridTasker(
        seed: Long,
        sheetsClient: SheetsClient
) : GridTaskerInterface {

    private val rand = Random(seed)
    private val easyTasks = sheetsClient.getColumnData("A")
    private val hardTasks = sheetsClient.getColumnData("B")

    override fun fillGrid(grid: List<List<Int>>): List<List<String>> {
        canTaskGrid(grid)
        return grid.map {
            it.map {
                if (it == 1) generateTask(hardTasks) else generateTask(easyTasks)
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
        val total = grid.sumBy { it.sumBy { 1 } }
        val hard = grid.sumBy { it.sum() }
        val easy = total - hard
        if(hard > hardTasks.values.size || easy > easyTasks.values.size){
            throw IllegalStateException("Not Enough tasks to build grid, need HARD: $hard & EASY: $easy")
        }
    }



}