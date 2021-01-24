package com.appvox.core.review.service

import com.appvox.core.configuration.Configuration
import com.appvox.core.exception.AppVoxException
import com.appvox.core.review.domain.request.AppStoreReviewRequest
import com.appvox.core.review.domain.result.AppStoreReviewResult
import com.appvox.core.utils.HttpUtils
import com.appvox.core.utils.impl.HttpUtilsImpl
import com.fasterxml.jackson.databind.ObjectMapper


internal class AppStoreReviewService(
    private val config: Configuration? = null
) {
    companion object {
        internal const val REQUEST_REVIEW_SIZE = 10
        internal const val APP_HP_URL_PATTERN = "https://apps.apple.com/%s/app/id%s"
        internal const val REQUEST_URL_WITH_PARAMETERS = "https://amp-api.apps.apple.com/v1/catalog/%s/apps/%s/reviews?offset=%d&platform=web&additionalPlatforms=appletv,ipad,iphone,mac"
        internal const val REQUEST_URL_WITH_NEXT = "https://amp-api.apps.apple.com%s&platform=web&additionalPlatforms=appletv,ipad,iphone,mac"
        private const val BEARER_TOKEN_REGEX_PATTERN = "token%22%3A%22(.+?)%22"
    }

    private var httpUtils : HttpUtils = HttpUtilsImpl

    @Throws(AppVoxException::class)
    fun getReviewsByAppId(appId: String, request: AppStoreReviewRequest): AppStoreReviewResult {
        val requestUrl = if (request.nextToken.isNullOrEmpty()) {
            REQUEST_URL_WITH_PARAMETERS.format(request.region, appId, REQUEST_REVIEW_SIZE)
        } else {
            REQUEST_URL_WITH_NEXT.format(request.nextToken)
        }
        val responseContent = httpUtils.getRequest(
                requestUrl = requestUrl, bearerToken = request.bearerToken, proxyConfig = config?.proxy)
        val result = ObjectMapper().readValue(responseContent, AppStoreReviewResult::class.java)
        return result
    }

    fun getBearerToken(appId: String, region: String): String {
        val requestUrl = APP_HP_URL_PATTERN.format(region, appId)
        val responseContent = httpUtils.getRequest(requestUrl = requestUrl, proxyConfig = config?.proxy)
        val regex = BEARER_TOKEN_REGEX_PATTERN.toRegex()
        val tokenMatches = regex.find(responseContent)
        val tokenMatch = tokenMatches?.groupValues?.get(1)
        return tokenMatch.orEmpty()
    }
}