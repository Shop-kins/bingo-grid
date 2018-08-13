package bingogrid

import bingogrid.Sheets.SheetsClient
import bingogrid.model.InputData
import bingogrid.model.ReturnData
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.InputStream
import java.io.OutputStream
import java.util.*


class Mains {

    private val mapper = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    private val defaultSheetId: String = ""

    fun handler(input: InputStream, output: OutputStream){
        try {
            val inputData = mapper.readValue<InputData>(input)
            val seed = inputData.queryStringParameters?.seed ?: Random().nextInt().toString()
            val spreadsheetId = inputData.queryStringParameters?.sheetId ?: defaultSheetId
            val client = SheetsClient(spreadsheetId)
            val longSeed = SeedTranslator.alphaNumericSeedToRandomLong(seed)
            val tasker = GridTaskerLatinSquare(longSeed, client)
            val gridGenerator = GridGeneratorLatinSquare(longSeed)
            val grid = gridGenerator.generate()
            val filledGrid = tasker.fillGrid(grid)
            val response = ReturnData(
                    false,
                    200,
                    mapOf("Access-Control-Allow-Origin" to "http://awesome-kirch-d8bb59.bitballoon.com"),
                    mapper.writeValueAsString(filledGrid)
            )
            mapper.writeValue(output, response)
        } catch (e: Exception){
            val response = ReturnData(
                    false,
                    500,
                    mapOf("Access-Control-Allow-Origin" to "http://awesome-kirch-d8bb59.bitballoon.com"),
                    e.message ?: "Unknown Error"
            )
            mapper.writeValue(output, response)
        }
    }

}