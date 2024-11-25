import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EarthquakeViewModel : ViewModel() {
    private val _earthquakes = MutableLiveData<List<Earthquake>>()
    val earthquakes: LiveData<List<Earthquake>> get() = _earthquakes

    fun fetchEarthquakes() {
        CoroutineScope(Dispatchers.IO).launch {
            val data = EarthquakeApi.fetchEarthquakes()
            _earthquakes.postValue(data)
        }
    }
}
