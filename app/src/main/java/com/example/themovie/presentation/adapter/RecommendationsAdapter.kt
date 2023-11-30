package com.example.themovie.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.themovie.R
import com.example.themovie.databinding.ItemRecommendationsBinding
import com.example.themovie.domain.model.Result
import com.example.themovie.presentation.util.loadImage

class RecommendationsAdapter(context: Context) : PagingDataAdapter<Result, RecommendationsAdapter.ViewHolder>(diffCallback) {

    val defaultDrawable = ContextCompat.getDrawable(context, R.drawable.baseline_question_mark)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tile = getItem(position)
        if (tile != null) {
            holder.bind(tile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationsAdapter.ViewHolder {
        return ViewHolder(
            ItemRecommendationsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val binding: ItemRecommendationsBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(result: Result) {
                binding.title.text = result.title
                result.backdropPath?.let { binding.posterRecommendation.loadImage(it)} ?: binding.posterRecommendation.setImageDrawable(defaultDrawable)

            }
        }

}