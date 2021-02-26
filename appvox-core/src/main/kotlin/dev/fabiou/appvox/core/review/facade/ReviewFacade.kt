package dev.fabiou.appvox.core.review.facade

import dev.fabiou.appvox.core.configuration.Configuration
import dev.fabiou.appvox.core.configuration.ProxyConfiguration
import dev.fabiou.appvox.core.review.constant.AppStoreSortType
import dev.fabiou.appvox.core.review.converter.AppStoreReviewConverter
import dev.fabiou.appvox.core.review.converter.GooglePlayReviewConverter
import dev.fabiou.appvox.core.review.converter.ReviewConverter
import dev.fabiou.appvox.core.review.domain.request.AppStoreReviewRequest
import dev.fabiou.appvox.core.review.domain.request.GooglePlayReviewRequest
import dev.fabiou.appvox.core.review.domain.response.AppStoreReviewResponse
import dev.fabiou.appvox.core.review.domain.response.GooglePlayReviewResponse
import dev.fabiou.appvox.core.review.domain.response.ReviewResponse
import dev.fabiou.appvox.core.review.service.AppStoreRecentReviewService
import dev.fabiou.appvox.core.review.service.AppStoreReviewService
import dev.fabiou.appvox.core.review.service.GooglePlayReviewService
import dev.fabiou.appvox.core.translation.TranslationService

class ReviewFacade(
    val config : Configuration = Configuration()
) {
    private var googlePlayReviewService = GooglePlayReviewService(config)

    private var appStoreRecentReviewService = AppStoreRecentReviewService(config)

    private var appStoreReviewService = AppStoreReviewService(config)

    fun getReviewsByAppId(appId: String, request: GooglePlayReviewRequest) : ReviewResponse {
        val reviews = googlePlayReviewService.getReviewsByAppId(appId = appId, request = request)
        return ReviewConverter.toResponse(reviews)
    }

    fun getReviewsByAppId(appId : String, request: AppStoreReviewRequest) : ReviewResponse {
        var response: ReviewResponse
        if (request.sortType == AppStoreSortType.RECENT) {
            var reviews = appStoreRecentReviewService.getReviewsByAppId(appId = appId, request = request)
            response = ReviewConverter.toResponse(reviews)
        } else {
            if (request.bearerToken == null) {
                val bearerToken = appStoreReviewService.getBearerToken(appId, request.region)
                request.bearerToken = bearerToken
            }
            val reviews = appStoreReviewService.getReviewsByAppId(
                appId = appId,
                request = request
            )
            response = ReviewConverter.toResponse(reviews)
        }
        return response
    }
}