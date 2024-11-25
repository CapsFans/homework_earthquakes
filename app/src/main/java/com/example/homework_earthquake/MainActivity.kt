package com.example.homework_earthquake

import EarthquakeListAdapter
import EarthquakeViewModel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {
    private val viewModel: EarthquakeViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = EarthquakeListAdapter { earthquake ->
            startActivity(
                MapActivity.newIntent(this, earthquake.latitude, earthquake.longitude)
            )
        }
        recyclerView.adapter = adapter

        viewModel.earthquakes.observe(this) { earthquakes ->
            adapter.submitList(earthquakes)
        }

        viewModel.fetchEarthquakes()
    }
}
