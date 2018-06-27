package bingogrid

class GridGenerator{

    companion object {
        fun generate(seed: Double): List<List<Int>>{
            return if(seed < 0.5) boringTemplateOne else boringTemplateTwo
        }

        private val boringTemplateOne =
                listOf(
                        listOf(1, 0, 0, 1, 0),
                        listOf(0, 1, 1, 0, 0),
                        listOf(0, 0, 0, 1, 1),
                        listOf(0, 1, 0, 0, 1),
                        listOf(1, 0, 1, 0, 0)
                )

        private val boringTemplateTwo =
                listOf(
                        listOf(1, 0, 1, 0, 0),
                        listOf(0, 0, 1, 0, 1),
                        listOf(0, 1, 0, 0, 1),
                        listOf(0, 1, 0, 1, 0),
                        listOf(1, 0, 0, 1, 0)
                )
    }



}