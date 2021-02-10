package com.bhavik.moviesdb.data.local

enum class MovieSort(val sort: String) {
    TITLE_ASC("Title Ascending"),
    TITLE_DSC("Title Descending"),
    RELEASE_ASC("Date Ascending"),
    RELEASE_DSC("Date Descending"),
    POPULARITY_ASC("Popularity Ascending"),
    POPULARITY_DSC("Popularity Descending");

    override fun toString(): String = sort

    companion object {
        @JvmStatic
        fun valueOfOrDefault(myValue: String): MovieSort {
            for (type in MovieSort::class.java.enumConstants) {
                if (type.toString() == myValue) {
                    return type
                }
            }
            throw IllegalArgumentException("MovieSort not found")
        }
    }
}