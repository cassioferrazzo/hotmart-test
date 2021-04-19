package com.br.cassioferrazzo.hotmarttest.ui.locations

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import com.br.cassioferrazzo.hotmarttest.data.model.ResponseError
import com.br.cassioferrazzo.hotmarttest.databinding.ActivityLocationDetailBinding
import com.br.cassioferrazzo.hotmarttest.ui.locations.model.LocationDetailUiModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLocationDetailBinding
    private val arguments by navArgs<LocationDetailActivityArgs>()
    private val locationId by lazy { arguments.locationId }
    private val viewModel: LocationsViewModel by viewModel()

    private val locationDetailObserver = Observer<LocationDetailUiModel> {
        Log.i(TAG, "$it")
        binding.rbRating.rating = it.review
        binding.tvRating.text = it.reviewText
        binding.tvAbout.text = it.about
        binding.tvName.text = it.name
        binding.tvAddress.text = it.address
        binding.tvPhone.text = it.phone
        binding.tvSchedule.text = it.schedules.joinToString(separator = "\n")
    }

    private val locationDetailErrorObserver = Observer<ResponseError> {
        Log.i(TAG, "$it")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.locationDetailLiveData.observe(this, locationDetailObserver)
        viewModel.locationDetailErrorLiveData.observe(this, locationDetailErrorObserver)
        viewModel.getLocationDetail(locationId)
    }

    companion object {
        private val TAG = LocationDetailActivity::class.java.simpleName
    }
}