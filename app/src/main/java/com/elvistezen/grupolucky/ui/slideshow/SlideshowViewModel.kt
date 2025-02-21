package com.elvistezen.grupolucky.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SlideshowViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Hola grupo lucky. Me faltó tiempo para cerrar la sesión. :("
    }
    val text: LiveData<String> = _text
}