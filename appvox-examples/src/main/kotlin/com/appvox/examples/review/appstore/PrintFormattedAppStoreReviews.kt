package com.appvox.examples.review.appstore

import dev.fabiou.appvox.core.AppStore
import dev.fabiou.appvox.core.configuration.RequestConfiguration
import dev.fabiou.appvox.core.review.itunesrss.constant.AppStoreRegion
import dev.fabiou.appvox.core.review.itunesrss.constant.AppStoreSortType
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.collect

/**
 * In this example, we print the 100 most relevant App Store Reviews of the Twitter App
 * Link: https://apps.apple.com/us/app/twitter/id333903271
 *
 * Network requests are made through a proxy with a delay of 3 seconds between each request.
 * The proxy is optional and can be removed from AppReview constructor.
 * AppVox is polite by default, request delay cannot be inferior to 500 ms
 */
fun main() = runBlocking {

    val appId = "333903271"
    val userRegion = "us"
    val maxReviewCount = 100

    val config = RequestConfiguration(
        requestDelay = 3000L
    )
    val appStore = AppStore(config)
    appStore.reviews(
            appId = appId,
            region = AppStoreRegion.fromValue(userRegion),
            sortType = AppStoreSortType.RECENT
        )
        .take(maxReviewCount)
        .collect { review ->
            val formattedReview =
                """
                            Id: ${review.id}
                            Rating: ${review.rating}
                            User Name: ${review.userName}
                            Title: ${review.title}
                            Comment: ${review.comment}
                            Comment Time: ${review.commentTime}
                            Reply Comment: ${review.replyComment}
                            Reply Time: ${review.replyTime}
                            Review Url: ${review.url}
                        """.trimIndent()
            println(formattedReview)
        }
}
