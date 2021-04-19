package com.br.cassioferrazzo.hotmarttest.ui.locations

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import com.br.cassioferrazzo.hotmarttest.data.model.ResponseError
import com.br.cassioferrazzo.hotmarttest.databinding.ActivityLocationDetailBinding
import com.br.cassioferrazzo.hotmarttest.ui.ErrorHandler
import com.br.cassioferrazzo.hotmarttest.ui.locations.model.LocationDetailUiModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLocationDetailBinding
    private val arguments by navArgs<LocationDetailActivityArgs>()
    private val locationId by lazy { arguments.locationId }
    private val errorHandler by lazy { ErrorHandler(this@LocationDetailActivity) }
    private val viewModel: LocationsViewModel by viewModel()

    private val locationDetailObserver = Observer<LocationDetailUiModel> {
        Log.i(TAG, "$it")
        binding.rbRating.rating = it.review
        binding.tvRating.text = it.reviewText
        binding.tvAbout.text = it.about
        binding.tvName.text = it.name
        binding.tvAddress.text = it.address
        binding.tvPhone.text = it.phone
        val schedulesStr = it.schedules.joinToString(separator = "\n")
        binding.tvSchedule.text = schedulesStr
    }

    private val locationDetailErrorObserver = Observer<ResponseError> {
        Log.i(TAG, "$it")
        errorHandler.handleError(it, ::loadLocationDetail, ::onBackPressed)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationDetailBinding.inflate(layoutInflater)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        setContentView(binding.root)
        viewModel.locationDetailLiveData.observe(this, locationDetailObserver)
        viewModel.locationDetailErrorLiveData.observe(this, locationDetailErrorObserver)
        loadLocationDetail()
    }

    private fun loadLocationDetail(){
        viewModel.getLocationDetail(locationId)
    }

    companion object {
        private val TAG = LocationDetailActivity::class.java.simpleName
    }
}