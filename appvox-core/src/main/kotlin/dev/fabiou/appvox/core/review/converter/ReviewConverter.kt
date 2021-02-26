package dev.fabiou.appvox.core.review.converter

import dev.fabiou.appvox.core.review.domain.response.AppStoreReviewResponse
import dev.fabiou.appvox.core.review.domain.response.GooglePlayReviewResponse
import dev.fabiou.appvox.core.review.domain.response.ReviewResponse
import dev.fabiou.appvox.core.review.domain.response.ReviewResponse.AppReview
import dev.fabiou.appvox.core.review.domain.result.AppStoreRecentReviewResult
import dev.fabiou.appvox.core.review.domain.result.AppStoreReviewResult
import dev.fabiou.appvox.core.review.domain.result.GooglePlayReviewResult
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime

class ReviewConverter {
    companion object {
        fun toResponse(reviewResult: GooglePlayReviewResult): ReviewResponse {
            var reviews = ArrayList<AppReview>()
            val googlePlayReviews = reviewResult.reviews
            for (googlePlayReview in googlePlayReviews) {
                val reviewResponse = AppReview(
                        id = googlePlayReview.reviewId,
                        userName = googlePlayReview.userName,
                        userAvatar = googlePlayReview.userProfilePicUrl,
                        rating = googlePlayReview.rating,
                        comment = googlePlayReview.comment,
                        commentTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(googlePlayReview.submitTime), ZoneOffset.UTC),
                        replyComment = googlePlayReview.replyComment,
//                      replyTime = if (googlePlayReview != null && googlePlayReview.replySubmitTime != 0) googlePlayReview.replySubmitTime?.let { ZonedDateTime.ofInstant(Instant.ofEpochSecond(googlePlayReview.replySubmitTime), ZoneOffset.UTC) } else null,
                        likeCount = googlePlayReview.likeCount,
                        appVersion = googlePlayReview.appVersion,
                        url = googlePlayReview.reviewUrl
                )
                reviews.add(reviewResponse)
            }

            return ReviewResponse(
                reviews = reviews,
                nextToken = reviewResult.token
            )
        }

        fun toResponse(reviewResult: AppStoreReviewResult): ReviewResponse {
            var reviews = ArrayList<AppReview>()
            val appStoreReviews = reviewResult.data
            for (appStoreReview in appStoreReviews) {
                val reviewResponse = AppReview(
                    id = appStoreReview.id,
                    userName = appStoreReview.attributes.userName,
                    rating = appStoreReview.attributes.rating,
                    title = appStoreReview.attributes.title,
                    comment = appStoreReview.attributes.review,
                    commentTime = ZonedDateTime.parse(appStoreReview.attributes.date),
                    replyComment = appStoreReview.attributes.developerResponse?.body,
                    replyTime = ZonedDateTime.parse(appStoreReview.attributes.developerResponse?.modified),
//                        url =
                )
                reviews.add(reviewResponse)
            }

            return ReviewResponse(
                reviews = reviews,
                nextToken = reviewResult.next
            )
        }

        internal fun toResponse(reviewResult: AppStoreRecentReviewResult): ReviewResponse {
            var reviews = ArrayList<AppReview>()
            val appStoreReviews = reviewResult.entry
            for (appStoreReview in appStoreReviews!!) {
                val reviewResponse = AppReview(
                    id = appStoreReview.id!!,
                    userName = appStoreReview.author?.name!!,
                    rating = appStoreReview.rating!!,
                    title = appStoreReview.title,
                    comment = appStoreReview.content?.find { it.type == "text" }?.content!!,
                    commentTime = appStoreReview.updated?.toGregorianCalendar()?.toZonedDateTime(),
                    appVersion = appStoreReview.version,
                    url = appStoreReview.link?.href,
                    likeCount = appStoreReview.voteCount,
//                    replyComment =
//                    replySubmitTime =
                )
                reviews.add(reviewResponse)
            }

            return ReviewResponse(
                reviews = reviews,
                nextToken = reviewResult.link!!.find { it.rel == "next" }?.href!!
            )
        }
    }
}