package bingogrid.Sheets

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import okhttp3.OkHttpClient
import okhttp3.Request

class SheetsClient(
        spreadsheetId: String
){

    private val client = OkHttpClient()
    private val objectMapper = ObjectMapper().registerKotlinModule()
    private val sheetsUrl: String =
            "https://sheets.googleapis.com/v4/spreadsheets/$spreadsheetId/values/"

    fun getColumnData(column: String): SheetsData{
        val fullUrl = sheetsUrl + "$column:$column?key="
        val request = Request.Builder().url(fullUrl)
        val result = client.newCall(request.build())
        val resultRespoonse = result.execute().body()
        resultRespoonse ?: throw IllegalStateException("Could not retrieve values")
        return objectMapper.readValue(resultRespoonse.string())
    }
}