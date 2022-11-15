package com.epitome.gweather.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.epitome.gweather.R
import com.epitome.gweather.models.remote.weather.Values
import com.epitome.gweather.util.Utils.convertDate

class WeatherAdapter(private val dataSet: List<Values>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val temperature: TextView
        val wind: TextView
        val visibility: TextView
        val rain: TextView
        val humidity: TextView
        val cloud: TextView
        val dateText: TextView

        init {
            temperature = view.findViewById(R.id.temperature)
            wind = view.findViewById(R.id.wind)
            visibility = view.findViewById(R.id.visibility)
            rain = view.findViewById(R.id.rainText)
            humidity = view.findViewById(R.id.water)
            cloud = view.findViewById(R.id.cloud)
            dateText = view.findViewById(R.id.dateText)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.weather_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.temperature.text = dataSet[position].temperature.let { it.toString() }
        viewHolder.wind.text = dataSet[position].windSpeed.let { it.toString() }
        viewHolder.humidity.text = dataSet[position].humidity.let { it.toString() }
        viewHolder.rain.text = dataSet[position].precipitationIntensity.let { it.toString() }
        viewHolder.cloud.text = dataSet[position].cloudCover.let { it.toString() }
        viewHolder.visibility.text = dataSet[position].visibility.let { it.toString() }
        viewHolder.dateText.text = convertDate(dataSet[position].dateTime.toString())

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}