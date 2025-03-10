package dev.fabiou.appvox.core.review

internal class ReviewIterator<Request, Result, Response>(
    private val service: ReviewService<Request, Result>,
    private val converter: ReviewConverter<Result, Response>,
    private var request: ReviewRequest<Request>
) : Iterator<List<Response>> {

    private val results: MutableList<Result>

    init {
        val response = service.getReviewsByAppId(request)
        results = response.results.toMutableList()
        request = request.copy(request.parameters, response.nextToken)
    }

    override fun hasNext(): Boolean {
        if (request.nextToken == null) {
            return false
        }

        val response = service.getReviewsByAppId(request)
        if (response.results.isEmpty()) {
            return false
        }
        results.clear()
        results.addAll(response.results)
        request = request.copy(request.parameters, response.nextToken)

        return true
    }

    override fun next(): List<Response> {
        return converter.toResponse(results)
    }
}
