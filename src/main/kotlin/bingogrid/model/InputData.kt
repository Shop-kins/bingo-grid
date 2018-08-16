package bingogrid.model


data class InputData(
        val queryStringParameters: QueryString?
)

data class QueryString(
        val seed: String?,
        val sheetId: String?,
        val mode: String?
)