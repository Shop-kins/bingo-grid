package bingogrid

fun main(args: Array<String>) {
    val longSeed = SeedTranslator.alphaNumericSeedToRandomLong("10")
    val gridGenerator = GridGenerator(longSeed)
    val grid = gridGenerator.generate()
    val filledGrid = GridTasker.fillGrid(grid)
    filledGrid.forEach {
        println(it)
    }
}