package dev.fabiou.appvox.core.review.service

import dev.fabiou.appvox.core.configuration.Configuration
import dev.fabiou.appvox.core.review.domain.request.AppStoreReviewRequest
import dev.fabiou.appvox.core.review.domain.request.ItunesRssReviewRequest
import dev.fabiou.appvox.core.review.domain.result.AppStoreRecentReviewResult
import dev.fabiou.appvox.core.review.domain.result.AppStoreReviewResult
import dev.fabiou.appvox.core.review.repository.ItunesRssReviewRepository
import dev.fabiou.appvox.core.review.repository.AppStoreReviewRepository

class AppStoreReviewService(
        val config: Configuration
) {
    private var itunesRssReviewRepository = ItunesRssReviewRepository(config)

    private var appStoreReviewRepository = AppStoreReviewRepository(config)

    fun getReviewsByAppId(request: ItunesRssReviewRequest) : AppStoreRecentReviewResult {
        var reviews = itunesRssReviewRepository.getReviewsByAppId(request)
        return reviews
    }

    fun getReviewsByAppId(request: AppStoreReviewRequest) : AppStoreReviewResult {
        request.bearerToken = request.bearerToken ?: appStoreReviewRepository.getBearerToken(request.appId, request.region)
        val result = appStoreReviewRepository.getReviewsByAppId(request)
        return result
    }
}