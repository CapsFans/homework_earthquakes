import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object EarthquakeApi {
    private const val API_URL =
        "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2023-01-01&endtime=2024-01-01&minmagnitude=7"

    fun fetchEarthquakes(): List<Earthquake> {
        val earthquakes = mutableListOf<Earthquake>()
        val url = URL(API_URL)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        connection.inputStream.bufferedReader().use { reader ->
            val response = reader.readText()
            val json = JSONObject(response)
            val features = json.getJSONArray("features")
            for (i in 0 until features.length()) {
                val feature = features.getJSONObject(i)
                val properties = feature.getJSONObject("properties")
                val geometry = feature.getJSONObject("geometry")
                val coordinates = geometry.getJSONArray("coordinates")

                val earthquake = Earthquake(
                    id = feature.getString("id"),
                    place = properties.getString("place"),
                    magnitude = properties.getDouble("mag"),
                    longitude = coordinates.getDouble(0),
                    latitude = coordinates.getDouble(1)
                )
                earthquakes.add(earthquake)
            }
        }

        return earthquakes
    }
}
