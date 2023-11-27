package com.example.themovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.themovie.databinding.FragmentMovieDetailsBinding
import com.example.themovie.presentation.adapter.CastAdapter
import com.example.themovie.presentation.adapter.RecommendationsAdapter
import com.example.themovie.presentation.util.loadImage
import com.example.themovie.viewmodels.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailViewModel by viewModels()

    @Inject
    lateinit var application: TheMovieApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        val movie = application.selectedMovie

        if (movie != null) {
            // details
            binding.summary.name.text = movie.title
            binding.summary.releaseDate.text = movie.release_date
            binding.summary.overview.text = movie.overview
            binding.summary.originalTitle.text = movie.original_title
            movie.backdrop_path?.let { binding.summary.poster.loadImage(it) }


            // casts
            val casts = viewModel.castsList
            val castAdapter = CastAdapter()
            val rvCast = binding.cast.listCasts
            rvCast.adapter = castAdapter

            lifecycleScope.launch {
                casts.collectLatest {
                    castAdapter.submitData(it)
                }
            }

            //recommendations
            val recommendations = viewModel.recommendationsList
            val recommendationAdapter = RecommendationsAdapter()
            val rvRecommendations = binding.recommendations.listRecommendations
            rvRecommendations.adapter = recommendationAdapter

            lifecycleScope.launch {
                recommendations.collectLatest {
                    recommendationAdapter.submitData(it)
                }
            }

        }

        return binding.root
    }

}