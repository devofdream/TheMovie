package com.example.themovie.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.themovie.R
import com.example.themovie.databinding.ItemMovieBinding
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.model.posterDate
import com.example.themovie.presentation.util.loadImage

class MovieAdapter(context: Context) : PagingDataAdapter<Movie, MovieAdapter.ViewHolder>(diffCallback) {

    lateinit var onItemClick: (Movie) -> Unit

    val defaultDrawable = ContextCompat.getDrawable(context, R.drawable.baseline_question_mark)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tile = getItem(position)
        if (tile != null) {
            holder.bind(tile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        return ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(movie: Movie) {
                binding.title.text = movie.title
                binding.date.text = movie.posterDate
                movie.poster_path?.let { binding.postImg.loadImage(it) } ?: binding.postImg.setImageDrawable(defaultDrawable)

                binding.root.setOnClickListener {
                    onItemClick(movie)
                }

            }
        }

}