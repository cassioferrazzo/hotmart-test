package com.br.cassioferrazzo.hotmarttest.ui.locations

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.br.cassioferrazzo.hotmarttest.R
import com.br.cassioferrazzo.hotmarttest.data.model.ResponseError
import com.br.cassioferrazzo.hotmarttest.ui.locations.model.LocationUiModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationsFragment : Fragment() {

    private val viewModel: LocationsViewModel by viewModel()

    private val locationsObserver = Observer<List<LocationUiModel>> {
        Log.i(TAG, "$it")
    }

    private val locationErrorObserver = Observer<ResponseError> {
        Log.i(TAG, "$it")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.locations_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.locationsLiveData.observe(viewLifecycleOwner, locationsObserver)
        viewModel.locationErrorLiveData.observe(viewLifecycleOwner, locationErrorObserver)
        viewModel.getLocations()
    }

    companion object {
        private val TAG = LocationsFragment::class.java.simpleName
        fun newInstance() = LocationsFragment()
    }
}