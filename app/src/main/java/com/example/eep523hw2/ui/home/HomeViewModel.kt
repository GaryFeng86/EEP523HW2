package com.example.eep523hw2.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "⬇️This is Sensor Data⬇️"
    }
    val text: LiveData<String> = _text
}