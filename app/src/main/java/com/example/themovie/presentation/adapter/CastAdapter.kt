package com.example.themovie.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.themovie.R
import com.example.themovie.databinding.ItemCastBinding
import com.example.themovie.domain.model.Cast
import com.example.themovie.presentation.util.loadImage

class CastAdapter(context: Context) : PagingDataAdapter<Cast, CastAdapter.ViewHolder>(diffCallback) {

    val defaultDrawable = ContextCompat.getDrawable(context, R.drawable.baseline_question_mark)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tile = getItem(position)
        if (tile != null) {
            holder.bind(tile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastAdapter.ViewHolder {
        return ViewHolder(
            ItemCastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Cast>() {
            override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(cast: Cast) {
                binding.name.text = cast.name
                binding.character.text = cast.character

                cast.profilePath?.let { binding.profileImg.loadImage(it) }
                    ?: binding.profileImg.setImageDrawable(defaultDrawable)

            }
        }


}