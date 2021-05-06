package com.example.starwarsuniverse.starwarsapi

import retrofit2.Call
import retrofit2.http.GET

interface StarWarsApi {

    @GET("people")
    fun fetchData(): Call<StarWarsJson>

}