package com.example.restaurants.model

data class Data(
    val name: String,
    val score: Number,
    val snippet: String,
    val images: MutableList<Images>?
)