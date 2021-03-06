package io.github.katatoshi.android.dialogfragmentwithviewmodel.ui.sub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SubViewModel : ViewModel() {

    private val _counter: MutableLiveData<Int> = MutableLiveData(0)

    val counter: LiveData<Int>
        get() = _counter

    private val _navigateToMain: MutableLiveData<Boolean> = MutableLiveData(false)

    val navigateToMain: LiveData<Boolean>
        get() = _navigateToMain

    private val _navigateToAppendix: MutableLiveData<Boolean> = MutableLiveData(false)

    val navigateToAppendix: LiveData<Boolean>
        get() = _navigateToAppendix

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

    fun onNavigateToMain() {
        _navigateToMain.value = true
    }

    fun doneNavigatingToMain() {
        _navigateToMain.value = false
    }

    fun onNavigateToAppendix() {
        _navigateToAppendix.value = true
    }

    fun doneNavigatingToAppendix() {
        _navigateToAppendix.value = false
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