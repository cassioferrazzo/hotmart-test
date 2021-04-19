package com.br.cassioferrazzo.hotmarttest.ui.locations

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import com.br.cassioferrazzo.hotmarttest.R
import com.br.cassioferrazzo.hotmarttest.data.model.ResponseError
import com.br.cassioferrazzo.hotmarttest.databinding.ActivityLocationDetailBinding
import com.br.cassioferrazzo.hotmarttest.ui.ErrorHandler
import com.br.cassioferrazzo.hotmarttest.ui.LoadingHandler
import com.br.cassioferrazzo.hotmarttest.ui.locations.model.LocationDetailUiModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLocationDetailBinding
    private val arguments by navArgs<LocationDetailActivityArgs>()
    private val locationId by lazy { arguments.locationId }
    private val errorHandler by lazy { ErrorHandler(this@LocationDetailActivity) }
    private val loadingHandler by lazy { LoadingHandler(this@LocationDetailActivity) }
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
        loadingHandler.hideLoading()
    }

    private val locationDetailErrorObserver = Observer<ResponseError> {
        Log.i(TAG, "$it")
        loadingHandler.hideLoading()
        errorHandler.handleError(it, ::loadLocationDetail, ::onBackPressed)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationDetailBinding.inflate(layoutInflater)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.toolbar.setOnMenuItemClickListener { onMenuItemClickListener(it) }
        setContentView(binding.root)
        viewModel.locationDetailLiveData.observe(this, locationDetailObserver)
        viewModel.locationDetailErrorLiveData.observe(this, locationDetailErrorObserver)
        loadLocationDetail()
    }

    private fun onMenuItemClickListener(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.menu_share -> {
                share()
                true
            }
            else -> false
        }
    }

    private fun loadLocationDetail() {
        loadingHandler.showLoading()
        viewModel.getLocationDetail(locationId)
    }

    private fun share() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_text))
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.app_name))
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    companion object {
        private val TAG = LocationDetailActivity::class.java.simpleName
    }
}