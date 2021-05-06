package com.example.starwarsuniverse.starwarsapi


import com.google.gson.annotations.SerializedName

data class Result(
    //Comments are examples of the data that I can extract from the API :D
    @SerializedName("birth_year")
    val birthYear: String = "", // 19BBY
    val created: String = "", // 2014-12-09T13:50:51.644Z
    val edited: String = "", // 2014-12-20T21:17:56.891Z
    @SerializedName("eye_color")
    val eyeColor: String = "", // blue
    val films: List<Int> = listOf(),
    val gender: String = "", // male
    @SerializedName("hair_color")
    val hairColor: String = "", // blond
    val height: String = "", // 172
    val homeworld: Int = 0, // 1
    val id: Int = 0, // 1
    val image: String = "", // https://swapiclone.herokuapp.com/luke_skywalker.jpg
    val mass: String = "", // 77
    val name: String = "", // Luke Skywalker
    @SerializedName("skin_color")
    val skinColor: String = "", // fair
    val starships: List<Int> = listOf(),
    val vehicles: List<Int> = listOf()
)