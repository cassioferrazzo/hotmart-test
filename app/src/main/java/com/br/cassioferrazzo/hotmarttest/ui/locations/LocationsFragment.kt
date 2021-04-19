package com.br.cassioferrazzo.hotmarttest.ui.locations

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.cassioferrazzo.hotmarttest.data.model.ResponseError
import com.br.cassioferrazzo.hotmarttest.databinding.LocationsFragmentBinding
import com.br.cassioferrazzo.hotmarttest.ui.ErrorHandler
import com.br.cassioferrazzo.hotmarttest.ui.locations.model.LocationUiModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationsFragment : Fragment() {

    private lateinit var binding: LocationsFragmentBinding
    private val navController by lazy { findNavController() }
    private val errorHandler by lazy { ErrorHandler(requireContext()) }
    private val viewModel: LocationsViewModel by viewModel()

    private val onItemLocationClick = object : OnLocationClickListener {
        override fun onClick(location: LocationUiModel) {
            val directions =
                    LocationsFragmentDirections.actionNavigatoLocationsToLocationDetails(location.id)
            navController.navigate(directions)
        }
    }

    private val locationsObserver = Observer<List<LocationUiModel>> {
        Log.i(TAG, "$it")
        binding.rvLocations.adapter = LocationsListAdapter(it, onItemLocationClick)
    }

    private val locationErrorObserver = Observer<ResponseError> {
        Log.i(TAG, "$it")
        errorHandler.handleError(it, ::loadLocations)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LocationsFragmentBinding.inflate(inflater, container, false)
        GridLayoutManager(
                requireContext(), 2,
                LinearLayoutManager.VERTICAL, false,
        ).also { binding.rvLocations.layoutManager = it }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.locationsLiveData.observe(viewLifecycleOwner, locationsObserver)
        viewModel.locationErrorLiveData.observe(viewLifecycleOwner, locationErrorObserver)
        loadLocations()
    }

    private fun loadLocations() {
        viewModel.getLocations()
    }

    companion object {
        private val TAG = LocationsFragment::class.java.simpleName
    }

    interface OnLocationClickListener {
        fun onClick(location: LocationUiModel)
    }
}