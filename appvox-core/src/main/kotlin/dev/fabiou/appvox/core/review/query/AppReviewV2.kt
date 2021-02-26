package dev.fabiou.appvox.core.review.query

import dev.fabiou.appvox.core.configuration.Configuration
import dev.fabiou.appvox.core.review.constant.AppStoreSortType
import dev.fabiou.appvox.core.review.constant.GooglePlayLanguage
import dev.fabiou.appvox.core.review.constant.GooglePlaySortType
import dev.fabiou.appvox.core.review.domain.request.AppStoreReviewRequest
import dev.fabiou.appvox.core.review.domain.request.GooglePlayReviewRequest
import dev.fabiou.appvox.core.review.facade.AppStoreReviewFacade
import dev.fabiou.appvox.core.review.facade.GooglePlayReviewFacade
import dev.fabiou.appvox.core.review.facade.ReviewFacade
import dev.fabiou.appvox.core.review.iterator.AppStoreReviewIterator
import dev.fabiou.appvox.core.review.iterator.GooglePlayReviewIterator
import dev.fabiou.appvox.core.review.iterator.ReviewIterator

class AppReviewV2(
    configuration: Configuration = Configuration()
) {
    private var reviewFacade = ReviewFacade(configuration)

    fun appStore(appId: String, sortType: AppStoreSortType, region: String, maxCount: Int = 0) : ReviewIterator {
        val request = AppStoreReviewRequest(
                region = region,
                sortType = sortType,
                maxCount = maxCount
        )
        return ReviewIterator(reviewFacade, appId, request)
    }

    fun googlePlay(
            appId: String,
            language: GooglePlayLanguage,
            sortType: GooglePlaySortType = GooglePlaySortType.RECENT,
            maxCount: Int = 0,
            batchSize: Int = 40) : ReviewIterator {
        val request = GooglePlayReviewRequest(
            language = language,
            sortType = sortType,
            maxCount = maxCount,
            batchSize = batchSize
        )
        return ReviewIterator(reviewFacade, appId, request)
    }
}