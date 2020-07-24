package com.appvox.api.converter

import com.appvox.core.domain.response.review.ReviewResponse
import com.appvox.core.domain.response.review.ReviewsResponse
import com.appvox.core.domain.result.appstore.AppStoreReviewsResult
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class AppStoreReviewConverter {

    fun toResponse(reviewResult: AppStoreReviewsResult, nextCursor: String?) : ReviewsResponse {
        var reviews = ArrayList<ReviewResponse>()
        val appStoreReviews = reviewResult.data
        for (appStoreReview in appStoreReviews) {

            val reviewResponse = ReviewResponse(
                    type = "AppStore",
                    id = appStoreReview.id,
                    userName = appStoreReview.attributes.userName,
                    rating = appStoreReview.attributes.rating,
                    title = appStoreReview.attributes.title,
                    comment = appStoreReview.attributes.review,
                    submitTime = Instant.parse(appStoreReview.attributes.date).toEpochMilli()
//                    replyComment = appStoreReview.attributes.developerResponse?.body,
//                    replySubmitTime = Instant.parse(appStoreReview.attributes.developerResponse?.modified?:"").toEpochMilli()
//                    url = appStoreReview.reviewUrl
            )
            reviews.add(reviewResponse)
        }

        return ReviewsResponse(
                nextCursor = nextCursor,
                reviews = reviews
        )
    }
}