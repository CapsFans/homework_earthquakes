import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_earthquake.R

class EarthquakeListAdapter(
    private val onItemClick: (Earthquake) -> Unit
) : RecyclerView.Adapter<EarthquakeListAdapter.EarthquakeViewHolder>() {

    private val earthquakes = mutableListOf<Earthquake>()

    fun submitList(data: List<Earthquake>) {
        earthquakes.clear()
        earthquakes.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthquakeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_item, parent, false)
        return EarthquakeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EarthquakeViewHolder, position: Int) {
        val earthquake = earthquakes[position]
        holder.placeTextView.text = earthquake.place
        holder.magnitudeTextView.text = "Magnitude: ${earthquake.magnitude}"

        if (earthquake.magnitude >= 7.5) {
            holder.placeTextView.setTextColor(holder.itemView.context.getColor(android.R.color.holo_red_dark))
        } else {
            holder.placeTextView.setTextColor(holder.itemView.context.getColor(android.R.color.black))
        }
        holder.itemView.setOnClickListener {
            onItemClick(earthquake)
        }
    }

    override fun getItemCount(): Int = earthquakes.size

    class EarthquakeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val placeTextView: TextView = itemView.findViewById(R.id.tv_place)
        val magnitudeTextView: TextView = itemView.findViewById(R.id.tv_magnitude)
    }
}
