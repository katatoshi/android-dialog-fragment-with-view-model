package io.github.katatoshi.android.dialogfragmentwithviewmodel.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _counter: MutableLiveData<Int> = MutableLiveData(0)

    val counter: LiveData<Int>
        get() = _counter

    private val _navigateToSub: MutableLiveData<Boolean> = MutableLiveData(false)

    val navigateToSub: LiveData<Boolean>
        get() = _navigateToSub

    private val _showResetCounterAlert: MutableLiveData<Boolean> = MutableLiveData(false)

    val showResetCounterAlert: LiveData<Boolean>
        get() = _showResetCounterAlert

    fun incrementCounter() {
        _counter.value?.let {
            _counter.value = it + 1
        }
    }

    fun resetCounter() {
        _counter.value = 0
    }

    fun onNavigateToSub() {
        _navigateToSub.value = true
    }

    fun doneNavigatingToSub() {
        _navigateToSub.value = false
    }

    fun tryToResetCounter() {
        _showResetCounterAlert.value = true
    }

    fun doneShowingResetCounterAlert() {
        _showResetCounterAlert.value = false
    }

    object Converter {

        @JvmStatic
        fun counterToString(counter: Int?) = counter?.toString()
    }
}