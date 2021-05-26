package com.albertabdullin.twitchtopgames.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.albertabdullin.twitchtopgames.R
import com.albertabdullin.twitchtopgames.database.GameEntity
import com.albertabdullin.twitchtopgames.databinding.AdapterItemBinding
import com.bumptech.glide.Glide

class GamesAdapter : PagingDataAdapter<GameEntity, GamesAdapter.GameItemViewHolder>(
    GameItemDiffUtilCallback()) {

    class GameItemViewHolder private constructor(private val binding: AdapterItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): GameItemViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = AdapterItemBinding.inflate(inflater, parent, false)
                return GameItemViewHolder(binding)
            }
        }

        fun bind(item: GameEntity?) {
            item?.also {
                binding.gameName.text = it.name
                binding.viewersCount.text = it.viewersCount.toString()
                binding.channelsCount.text = it.channelCount.toString()
                val imgUri = it.cover.toUri().buildUpon().scheme("https").build()
                Glide.with(binding.gameCover.context)
                        .load(imgUri)
                        .error(R.drawable.ic_baseline_broken_image_24)
                        .into(binding.gameCover)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameItemViewHolder {
        return GameItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GameItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

class GameItemDiffUtilCallback : DiffUtil.ItemCallback<GameEntity>() {

    override fun areItemsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean {
        return oldItem == newItem
    }

}

