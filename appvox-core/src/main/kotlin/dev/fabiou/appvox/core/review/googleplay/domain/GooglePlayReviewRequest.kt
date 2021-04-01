package dev.fabiou.appvox.core.review.googleplay.domain

import dev.fabiou.appvox.core.review.googleplay.constant.GooglePlayLanguage
import dev.fabiou.appvox.core.review.googleplay.constant.GooglePlaySortType

class GooglePlayReviewRequest(
    val appId: String,
    var nextToken: String? = null,
    val language: GooglePlayLanguage,
    val sortType: GooglePlaySortType,
    val batchSize: Int,
)