package com.example.themovie

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.themovie.databinding.FragmentMovieDetailsBinding
import com.example.themovie.domain.model.Cast
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.model.Result
import com.example.themovie.presentation.adapter.RecommendationsAdapter
import com.example.themovie.viewmodels.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        val movie = application.selectedMovie

        if (movie != null) {
            // details
            binding.summary.composeDetailSummaryView.setContent {
                MaterialTheme {
                    DetailSummary(movie)
                }
            }

            // casts
            binding.cast.composeDetailCastView.setContent {
                MaterialTheme {
                    Cast(viewModel.castsList)
                }
            }/*
            val casts = viewModel.castsList
            val castAdapter = CastAdapter(requireContext())
            val rvCast = binding.cast.listCasts
            rvCast.adapter = castAdapter

            lifecycleScope.launch {
                casts.collectLatest {
                    castAdapter.submitData(it)
                }
            }
             */

            //recommendations
            binding.recommendations.composeDetailRecommendationsView.setContent {
                MaterialTheme {
                    Recommendations(viewModel.recommendationsList)
                }
            }
            /*
            val recommendations = viewModel.recommendationsList
            val recommendationAdapter = RecommendationsAdapter(requireContext())
            val rvRecommendations = binding.recommendations.listRecommendations
            rvRecommendations.adapter = recommendationAdapter

            lifecycleScope.launch {
                recommendations.collectLatest {
                    recommendationAdapter.submitData(it)
                }
            }

             */

        }

        return binding.root
    }

    @Composable
    fun DetailSummary(movie: Movie) {
        Surface {
            Column {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500" + movie.backdrop_path,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
                movie.title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .wrapContentWidth(Alignment.Start)
                    )
                }
                movie.original_title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .wrapContentWidth(Alignment.Start)
                    )
                }
                movie.release_date?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .wrapContentWidth(Alignment.Start)
                    )
                }
                movie.overview?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .wrapContentWidth(Alignment.Start)
                    )
                }
            }
        }
    }

    @Preview
    @Composable
    private fun DetailSummaryPreview() {
        MaterialTheme {
            val movie = Movie(
                id = 238,
                poster_path = "/I1fkNd5CeJGv56mhrTDoOeMc2r.jpg",
                adult = false,
                overview = "시실리에서 이민온 뒤, 정치권까지 영향력을 미치는 거물로 자리잡은 돈 꼴레오네는 갖가지 고민을 호소하는 사람들의 문제를 해결해주며 대부라 불리운다. 한편 솔로소라는 인물은 꼴레오네가와 라이벌인 탓타리아 패밀리와 손잡고 새로운 마약 사업을 제안한다. 돈 꼴레오네가 마약 사업에 참여하지 않기로 하자, 돈 꼴레오네를 저격해 그는 중상을 입고 사경을 헤매게 된다. 그 뒤, 돈 꼴레오네의 아들 소니는 조직력을 총 동원해 다른 패밀리들과 피를 부르는 전쟁을 시작하는데... 가족의 사업과 상관없이 대학에 진학한 뒤 인텔리로 지내왔던 막내 아들 마이클은 아버지가 총격을 당한 뒤, 아버지를 구하기 위해 위험천만한 협상 자리에 나선다.",
                release_date = "1972-03-14",
                genre_ids = listOf(18, 80),
                original_title = "The Godfather",
                original_language = "en",
                title = "대부",
                backdrop_path = "/rSPw7tgCH9c6NqICZef4kZjFOQ5.jpg",
                popularity = 144.864f,
                vote_count = 19174,
                video = false,
                vote_average = 8.708f
            )
            DetailSummary(movie = movie)
        }
    }

    @Composable
    fun Cast(casts: Flow<PagingData<Cast>>) {
        val castListItems: LazyPagingItems<Cast> = casts.collectAsLazyPagingItems()
        Surface {
            Column {
                Text(
                    text = "주요 출연진",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                        .wrapContentWidth(Alignment.Start)
                )
                LazyRow(contentPadding = PaddingValues(16.dp, 8.dp)) {
                    items(castListItems.itemCount) { index ->
                        castListItems[index]?.let { CastListItem(it) }
                    }
                }
            }

        }
    }

    @Composable
    fun CastListItem(cast: Cast) {
        Column(
            modifier = Modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.Bottom
        ) {

            if (cast.profilePath == null) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_question_mark),
                    contentDescription = null,
                    modifier = Modifier
                        .width(150.dp)
                        .height(225.dp)
                )
            } else {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500" + cast.profilePath,
                    placeholder = painterResource(id = R.drawable.baseline_question_mark),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(15.dp))
                        .width(150.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }

            Text(
                text = cast.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.width(150.dp)
            )
            Text(
                text = cast.character,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.width(150.dp)
            )
        }
    }

    @Composable
    fun Recommendations(recommendations: Flow<PagingData<Result>>) {
        val recommendationsListItems: LazyPagingItems<Result> = recommendations.collectAsLazyPagingItems()
        Surface {
            Column {
                Text(
                    text = "추천",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                        .wrapContentWidth(Alignment.Start)
                )
                LazyRow(contentPadding = PaddingValues(16.dp, 8.dp)) {
                    items(recommendationsListItems.itemCount) { index ->
                        recommendationsListItems[index]?.let { RecommendationsListItem(it) }
                    }
                }
            }

        }
    }

    @Composable
    fun RecommendationsListItem(result: Result) {
        Column(
            modifier = Modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.Bottom
        ) {

            if (result.posterPath == null) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_question_mark),
                    contentDescription = null,
                    modifier = Modifier
                        .width(222.dp)
                        .height(124.dp)
                )
            } else {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500" + result.backdropPath,
                    placeholder = painterResource(id = R.drawable.baseline_question_mark),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(15.dp))
                        .width(222.dp)
                        .height(124.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }

            Text(
                text = result.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .width(222.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }

}