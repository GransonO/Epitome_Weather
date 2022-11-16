package com.epitome.gweather.ui.fragments

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epitome.gweather.BuildConfig
import com.epitome.gweather.R
import com.epitome.gweather.models.local.PlaceObj
import com.epitome.gweather.models.remote.places.autocomplete.AutoComplete
import com.epitome.gweather.ui.adapters.PlaceAdapter
import com.epitome.gweather.util.Utils
import com.epitome.gweather.util.Utils.baseLogger
import com.epitome.gweather.viewModels.WeatherViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StarterFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by activityViewModels()
    private val placeAdapter = PlaceAdapter()

    private val mapsApiKey = BuildConfig.maps_api_key

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_starter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText = view.findViewById<EditText>(R.id.place_text)
        editText.addTextChangedListener {
            if (it != null) {
                if(it.isNotEmpty() && it.toString().length > 3){
                    weatherViewModel.suggestPlace(it.toString(), mapsApiKey)
                }
                if(it.isEmpty()){
                    weatherViewModel.placeLists.clear()
                }
            }

        }
        previewSuggestedPlace(view)
    }

    private fun previewSuggestedPlace(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.place_recycler)
        val cardView = view.findViewById<CardView>(R.id.card_view)
        val progressBarLayout = view.findViewById<LinearLayout>(R.id.progressBarLayout)
        lifecycleScope.launch {
            weatherViewModel.suggestion.collect {
                when{
                    it.data != null -> {
                        val suggestions =  it.data as AutoComplete
                        suggestions.predictions.map { prediction ->
                            weatherViewModel.placeLists.add(
                                PlaceObj(
                                    name = prediction.description,
                                    placeId = prediction.placeId
                                )
                            )
                        }
                        cardView.apply {
                            visibility = View.VISIBLE
                        }
                        recyclerView.apply {
                            adapter = placeAdapter
                        }
                        placeAdapter.submitList(weatherViewModel.placeLists as List<PlaceObj>)
                        progressBarLayout.apply {
                            visibility = View.GONE
                        }
                    }
                    it.loading -> {
                        progressBarLayout.apply {
                            visibility = View.VISIBLE
                        }
                    }
                    it.error != null -> {
                        progressBarLayout.apply {
                            visibility = View.GONE
                        }
                        Toast.makeText(activity, "An error occurred while fetching the data", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

}