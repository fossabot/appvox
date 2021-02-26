package dev.fabiou.appvox.core.review.constant

enum class ReviewSortType(val sortType: Int) {
    RELEVANT(1),
    RECENT(2),
    RATING(3);

    companion object {
        fun fromValue(sortType: String) : ReviewSortType {
            for (googlePlaySortType in ReviewSortType.values()) {
                if (googlePlaySortType.name == sortType) {
                    return googlePlaySortType
                }
            }
            return RELEVANT
        }
    }
}