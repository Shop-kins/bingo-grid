package bingogrid.Sheets

data class SheetsData(
        val range: String,
        val majorDimension: String,
        val values: MutableList<List<String>>
)