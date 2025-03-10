package dev.fabiou.appvox.core.review.googleplay

import dev.fabiou.appvox.core.BaseRepositoryTest
import dev.fabiou.appvox.core.configuration.RequestConfiguration
import dev.fabiou.appvox.core.review.ReviewRequest
import dev.fabiou.appvox.core.review.googleplay.constant.GooglePlayLanguage
import dev.fabiou.appvox.core.review.googleplay.constant.GooglePlaySortType
import dev.fabiou.appvox.core.review.googleplay.domain.GooglePlayReviewRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GooglePlayReviewRepositoryTest : BaseRepositoryTest() {

    private val repository = GooglePlayReviewRepository(RequestConfiguration(requestDelay = 3000L))

    @ExperimentalCoroutinesApi
    @ParameterizedTest
    @CsvSource(
        "com.twitter.android, en, 1, 40, 40"
    )
    fun `Get Google Play reviews`(
        appId: String,
        language: String,
        sortType: Int,
        batchSize: Int,
        maxReviewCount: Int
    ) {

        val mockData = javaClass.getResource("/review/googleplay_reviews_mock_data.json").readText()
        stubHttpUrl(GooglePlayReviewRepository.REQUEST_URL_PATH, mockData)

        val request = GooglePlayReviewRequest(
            appId = appId,
            language = GooglePlayLanguage.fromValue(language),
            sortType = GooglePlaySortType.fromValue(sortType),
            batchSize = batchSize,
        )

        val response = repository.getReviewsByAppId(ReviewRequest(request))

        Assertions.assertEquals(maxReviewCount, response.results.size)
    }
}
