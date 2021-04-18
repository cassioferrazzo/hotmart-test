package com.br.cassioferrazzo.hotmarttest.ui.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.cassioferrazzo.hotmarttest.databinding.FragmentLocationItemBinding
import com.br.cassioferrazzo.hotmarttest.ui.locations.model.LocationUiModel

class LocationsListAdapter(
    private val locations: List<LocationUiModel>,
    private val onClickListener: LocationsFragment.OnLocationClickListener
) : RecyclerView.Adapter<LocationsListAdapter.LocationsViewHodler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHodler {
        val view = FragmentLocationItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LocationsViewHodler(view)
    }

    override fun onBindViewHolder(holder: LocationsViewHodler, position: Int) {
        holder.bind(locations[position])
    }

    override fun getItemCount(): Int = locations.size

    inner class LocationsViewHodler(
        private val binding: FragmentLocationItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(locationUiModel: LocationUiModel) {
            binding.rbItem.rating = locationUiModel.review
            binding.tvName.text = locationUiModel.name
            binding.tvType.text = locationUiModel.type
            binding.tvRating.text = locationUiModel.reviewText
            binding.cvLocation.setOnClickListener { onClickListener.onClick(locationUiModel) }
        }
    }
}