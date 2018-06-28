package bingogrid

import bingogrid.Sheets.SheetsClient

fun main(args: Array<String>) {
    val spreadsheetId = "1oTC43QsM1h7QxP_L7irkAJXiyVIOt1tc1qvQ-Al-KRs"
    val client = SheetsClient(spreadsheetId)
    val longSeed = SeedTranslator.alphaNumericSeedToRandomLong("101")
    val tasker = GridTasker(longSeed, client)
    val gridGenerator = GridGenerator(longSeed)
    val grid = gridGenerator.generate()
    val filledGrid = tasker.fillGrid(grid)

    filledGrid.forEach {
        println(it)
    }
}