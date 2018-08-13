package bingogrid.Sheets

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.IllegalStateException

class SheetsClient(
        spreadsheetId: String
){

    private val client = OkHttpClient()
    private val objectMapper = jacksonObjectMapper()
    private val sheetsUrl: String =
            "https://sheets.googleapis.com/v4/spreadsheets/$spreadsheetId/values/"

    fun getColumnData(column: String, sheet: Int = 1): SheetsData{
        val fullUrl = sheetsUrl + "Sheet$sheet!${column}2:$column?key=AIzaSyBZ306luTw34m-8uRMCE4rQYPU9CVgAXTY"
        val request = Request.Builder().url(fullUrl)
        val result = client.newCall(request.build())
        val resultRespoonse = result.execute().body()
        resultRespoonse ?: throw IllegalStateException("Could not retrieve values")
        val sheetDataNotFiltered: SheetsData = objectMapper.readValue(resultRespoonse.string())
        return SheetsData(
                sheetDataNotFiltered.range,
                sheetDataNotFiltered.majorDimension,
                sheetDataNotFiltered.values.filter { it.isNotEmpty() }.toMutableList()
        )
    }
}