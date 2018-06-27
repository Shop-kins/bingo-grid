package bingogrid

class GridTasker {

    companion object {

        fun fillGrid(grid: List<List<Int>>): List<List<String>> =
                grid.map {
                    it.map {
                        if (it == 1) generateHardTask() else generateEasyTask()
                    }
                }

        private fun generateHardTask(): String{
            return "X"
        }

        private fun generateEasyTask(): String{
            return "-"
        }

    }


}