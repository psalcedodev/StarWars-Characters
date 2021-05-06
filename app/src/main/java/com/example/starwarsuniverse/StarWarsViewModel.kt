package com.example.starwarsuniverse

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.starwarsuniverse.starwarsapi.Result

class StarWarsViewModel:ViewModel() {
    val peopleLiveData: LiveData<List<Result>> = StarWarsFetch().fetchPeople()
}