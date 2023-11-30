package com.example.themovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.themovie.databinding.FragmentTopRatedBinding
import com.example.themovie.presentation.adapter.MovieAdapter
import com.example.themovie.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class TopRatedFragment : Fragment() {

    private var _binding: FragmentTopRatedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModels()

    @Inject
    lateinit var application: TheMovieApplication

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTopRatedBinding.inflate(inflater, container, false)

        val adapter = MovieAdapter(requireContext())
        val items = viewModel.movieList

        val recyclerView = binding.list

        recyclerView.adapter = adapter
        adapter.onItemClick = { movie ->
            application.selectedMovie = movie
            try {
                val action = TopRatedFragmentDirections.actionTopRatedFragmentToMovieDetailsFragment(movie)
                findNavController().navigate(action)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                items.collectLatest {
                    adapter.submitData(it)
                }
            }
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}