package com.br.cassioferrazzo.hotmarttest.ui.locations

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.br.cassioferrazzo.hotmarttest.R
import com.br.cassioferrazzo.hotmarttest.data.model.ResponseError
import com.br.cassioferrazzo.hotmarttest.ui.locations.model.LocationDetailUiModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationDetailActivity : AppCompatActivity() {

    private val viewModel: LocationsViewModel by viewModel()

    private val locationDetailObserver = Observer<LocationDetailUiModel> {
        Log.i(TAG, "$it")
        //binding.rvLocations.adapter = LocationsListAdapter(it)
    }

    private val locationDetailErrorObserver = Observer<ResponseError> {
        Log.i(TAG, "$it")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)
        viewModel.locationDetailLiveData.observe(this, locationDetailObserver)
        viewModel.locationDetailErrorLiveData.observe(this, locationDetailErrorObserver)
        viewModel.getLocationDetail(2)
    }

    companion object {
        private val TAG = LocationDetailActivity::class.java.simpleName
    }
}