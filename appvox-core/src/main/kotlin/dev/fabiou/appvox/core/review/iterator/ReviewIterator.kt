package dev.fabiou.appvox.core.review.iterator

import dev.fabiou.appvox.core.exception.AppVoxErrorCode
import dev.fabiou.appvox.core.exception.AppVoxException
import dev.fabiou.appvox.core.review.domain.request.GooglePlayReviewRequest
import dev.fabiou.appvox.core.review.domain.request.ReviewRequest
import dev.fabiou.appvox.core.review.domain.response.GooglePlayReviewResponse
import dev.fabiou.appvox.core.review.domain.response.ReviewResponse
import dev.fabiou.appvox.core.review.facade.GooglePlayReviewFacade
import dev.fabiou.appvox.core.review.facade.ReviewFacade

class ReviewIterator(
    val facade: ReviewFacade,
    val appId: String,
    val request: ReviewRequest
) : Iterable<ReviewResponse.AppReview> {

    @Throws(AppVoxException::class)
    override fun iterator(): Iterator<ReviewResponse.AppReview> {
        return object : Iterator<ReviewResponse.AppReview> {

            var reviewIndex : Int = 0

            var iterator: Iterator<ReviewResponse.AppReview>

            init {
                val response = facade.getReviewsByAppId(appId, request)
                iterator = response.reviews.iterator()
                request.nextToken = response.nextToken
            }

            override fun hasNext(): Boolean {

                if (facade.config.requestDelay < 500) {
                    throw AppVoxException(AppVoxErrorCode.REQ_DELAY_TOO_SHORT)
                }

                if (request.maxCount != 0 && reviewIndex == request.maxCount) {
                    return false
                }

                if (request.nextToken == null && !iterator.hasNext()) {
                    return false
                }

                if (!iterator.hasNext()) {
                    Thread.sleep(facade.config.requestDelay)
                    val response = facade.getReviewsByAppId(appId, request)
                    if (response.reviews.isEmpty()) {
                        return false
                    }
                    iterator = response.reviews.iterator()
                    request.nextToken = response.nextToken
                }

                return true
            }

            override fun next(): ReviewResponse.AppReview {
                reviewIndex++
                return iterator.next()
            }
        }
    }
}
