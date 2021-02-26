package dev.fabiou.appvox.core.review.domain.request

import dev.fabiou.appvox.core.review.constant.AppLanguage
import dev.fabiou.appvox.core.review.constant.GooglePlayLanguage
import dev.fabiou.appvox.core.review.constant.GooglePlaySortType
import dev.fabiou.appvox.core.review.constant.ReviewSortType

abstract class ReviewRequest(

    var bearerToken: String? = null,

    val region: String,

    val language: AppLanguage,

    val sortType: ReviewSortType,

    /**
     * Only used by Google Play
     */
    val batchSize: Int,

    val maxCount: Int = 0,

    var nextToken: String? = null
)