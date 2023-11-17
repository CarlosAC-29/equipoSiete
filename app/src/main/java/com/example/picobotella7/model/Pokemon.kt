package com.example.picobotella7.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id")
    val id:Int,

    @SerializedName("name")
    val title: String,

    @SerializedName("img")
    val image: String
)
