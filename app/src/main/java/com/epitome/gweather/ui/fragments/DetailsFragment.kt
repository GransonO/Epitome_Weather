package com.epitome.gweather.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.epitome.gweather.BuildConfig
import com.epitome.gweather.R
import com.epitome.gweather.models.remote.places.details.PlaceDetails
import com.epitome.gweather.models.remote.weather.Timelines
import com.epitome.gweather.ui.adapters.WeatherAdapter
import com.epitome.gweather.util.Utils.baseLogger
import com.epitome.gweather.util.WeatherFields
import com.epitome.gweather.viewModels.WeatherViewModel
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private val weatherViewModel: WeatherViewModel by activityViewModels()

    private val mapsApiKey = BuildConfig.maps_api_key
    private val weatherApiKey = BuildConfig.weather_api_key

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            view.findViewById<TextView>(R.id.place_name_txt).text = it.getString("place_name").toString()
            fetchPlaceDetails(it.getString("place_id").toString(), view)
        }

        view.findViewById<ImageView>(R.id.back_arrow).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_detailsFragment_to_starterFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }


    private fun fetchPlaceDetails(placeId: String, view: View){
        val progressBar = view.findViewById<ProgressBar>(R.id.detailsProgressBar)
        lifecycleScope.launch {
            weatherViewModel.getPlaceDetails(id = placeId, key = mapsApiKey)
            weatherViewModel.place.collect {
                when{
                    it.data != null -> {
                        val placeData = it.data as PlaceDetails
                        val location = placeData.result.geometry.location
                        baseLogger("The place", location)
                        Toast.makeText(activity, "Fetching weather details", Toast.LENGTH_LONG).show()
                        fetchWeatherDetails(latitude = location.lat, longitude = location.lng, view)
                    }
                    it.loading -> {
                        progressBar.apply {
                            visibility = View.VISIBLE
                        }
                    }
                    it.error != null -> {
                        progressBar.apply {
                            visibility = View.GONE
                        }
                        Toast.makeText(activity, "An error occurred while fetching the place data", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun fetchWeatherDetails(latitude: Double, longitude: Double, view: View){
        val progressBar = view.findViewById<ProgressBar>(R.id.detailsProgressBar)
        val recyclerView = view.findViewById<RecyclerView>(R.id.weather_recycler)
        lifecycleScope.launch {
            var fields = "temperature"
            WeatherFields.values().map { fields = fields.plus("&fields=$it")}

            weatherViewModel.getWeatherForecast(
                key = weatherApiKey,
                lat = latitude,
                lon = longitude,
                field = fields
            )
            weatherViewModel.weather.collect { it ->
                when{
                    it.data != null -> {
                        val timeLineData = it.data as Timelines
                        baseLogger("The weather", timeLineData.data.timelines[0].intervals)
                        val standardPoint = timeLineData.data.timelines[0]
                        val values = standardPoint.intervals.map { x ->
                            x.values.copy(dateTime = x.startTime)
                        }
                        baseLogger("The values are: ::> ", values)
                        progressBar.apply {
                            visibility = View.GONE
                        }
                        recyclerView.apply {
                            adapter = WeatherAdapter(values)
                        }
                    }
                    it.loading -> {
                        progressBar.apply {
                            visibility = View.VISIBLE
                        }
                    }
                    it.error != null -> {
                        progressBar.apply {
                            visibility = View.GONE
                        }
                        Toast.makeText(activity, "An error occurred while fetching the place data", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}