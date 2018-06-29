package bingogrid.model

data class ReturnData(
        val isBase64Encoded: Boolean = false,
        val statusCode: Int,
        val headers: Map<String, String>,
        val body: String
)