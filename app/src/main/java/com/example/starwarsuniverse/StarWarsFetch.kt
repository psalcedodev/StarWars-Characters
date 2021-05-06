package com.example.starwarsuniverse

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.starwarsuniverse.starwarsapi.Result
import com.example.starwarsuniverse.starwarsapi.StarWarsApi
import com.example.starwarsuniverse.starwarsapi.StarWarsJson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "StarWarsApi"
class StarWarsFetch {
    private val starwarsApi: StarWarsApi
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://swapiclone.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        starwarsApi = retrofit.create(StarWarsApi::class.java)
    }

    fun fetchPeople(): MutableLiveData<List<com.example.starwarsuniverse.starwarsapi.Result>> {
        val responseLiveData: MutableLiveData<List<com.example.starwarsuniverse.starwarsapi.Result>> = MutableLiveData()
        val pokemonRequest: Call<StarWarsJson> = starwarsApi.fetchData()

        pokemonRequest.enqueue(object : Callback<StarWarsJson> {

            override fun onFailure(call: Call<StarWarsJson>, t: Throwable) {
                Log.e(TAG, "Failed to fetch data!", t)
            }

            override fun onResponse(
                call: Call<StarWarsJson>,
                response: Response<StarWarsJson>
            ) {

                val starwarsApiResponse: StarWarsJson? = response.body()
                var starwarsResponse: List<Result>? = starwarsApiResponse?.results

                if (starwarsResponse != null) {
                    starwarsResponse = starwarsResponse.filterNot {
                        it.name.isBlank()
                    }
                }
                responseLiveData.value = starwarsResponse
            }
        })

        return responseLiveData
    }
}