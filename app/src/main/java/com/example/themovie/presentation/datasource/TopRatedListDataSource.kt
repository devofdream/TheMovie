package com.example.themovie.presentation.datasource

import android.content.ContentValues
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.themovie.data.model.asDomain
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.network.response.TopRatedResponse
import java.lang.Exception

class TopRatedListDataSource(
    private val movieRepository: MovieRepository,
    private val language: String

) :
    PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPageNumber = params.key ?: 0
            val requestPage = nextPageNumber + 1

            val movieListResponse: TopRatedResponse = movieRepository.getTopRated(
                language = language,
                page = requestPage
            )

            val list = movieListResponse.results.map { it.asDomain() }

            LoadResult.Page(
                data = list,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < movieListResponse.totalPages) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "launchJob: Exception: $e, ${e.cause}")
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

}