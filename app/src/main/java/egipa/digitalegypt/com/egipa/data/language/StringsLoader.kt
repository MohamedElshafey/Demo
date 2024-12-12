package egipa.digitalegypt.com.egipa.data.language

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

@Singleton
class StringsLoader @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val englishMap: MutableMap<String, String> = mutableMapOf()
    private val arabicMap: MutableMap<String, String> = mutableMapOf()

    fun loadStrings() {
        try {
            val files = context.assets.list("strings") ?: return

            for (fileName in files) {
                val jsonContent = readFileContent("strings/$fileName")
                parseJson(jsonContent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun readFileContent(filePath: String): String {
        val builder = StringBuilder()
        val inputStream = context.assets.open(filePath)
        val reader = BufferedReader(InputStreamReader(inputStream))

        var line: String? = reader.readLine()
        while (line != null) {
            builder.append(line)
            line = reader.readLine()
        }

        reader.close()
        return builder.toString()
    }

    private fun parseJson(jsonContent: String) {
        val jsonObject = JSONObject(jsonContent)

        for (key in jsonObject.keys()) {
            val value = jsonObject.getJSONObject(key)
            val en = value.optString("en", "")
            val ar = value.optString("ar", "")

            if (en.isNotEmpty()) englishMap[key] = en
            if (ar.isNotEmpty()) arabicMap[key] = ar
        }
    }

    fun getEnglishStrings(): Map<String, String> = englishMap
    fun getArabicStrings(): Map<String, String> = arabicMap
}
