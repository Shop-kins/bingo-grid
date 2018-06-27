package bingogrid

fun main(args: Array<String>) {
    val doubleSeed = SeedTranslator.alphaNumericSeedToRandomDouble("10")
    val gridGenerator = GridGenerator(doubleSeed)
    val grid = gridGenerator.generate()
    val filledGrid = GridTasker.fillGrid(grid)
    filledGrid.forEach {
        println(it)
    }
}