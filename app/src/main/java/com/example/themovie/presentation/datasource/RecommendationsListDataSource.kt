package com.example.themovie.presentation.datasource

import android.content.ContentValues
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.themovie.data.model.asDomain
import com.example.themovie.domain.model.Result
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.network.response.RecommendationsResponse

class RecommendationsListDataSource(
    private val movieRepository: MovieRepository,
    private val language: String,
    private val movieId: Int

) :
    PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val nextPageNumber = params.key ?: 0
            val requestPage = nextPageNumber + 1

            val recommendationsResponse: RecommendationsResponse = movieRepository.getRecommendations(
                language = language,
                movieId = movieId
            )

            val list = recommendationsResponse.results.map { it.asDomain() }

            LoadResult.Page(
                data = list,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < recommendationsResponse.results.size) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "launchJob: Exception: $e, ${e.cause}")
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

}