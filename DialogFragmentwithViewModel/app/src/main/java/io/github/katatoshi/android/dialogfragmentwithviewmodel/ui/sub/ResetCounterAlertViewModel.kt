package io.github.katatoshi.android.dialogfragmentwithviewmodel.ui.sub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResetCounterAlertViewModel : ViewModel() {

    private val _positive: MutableLiveData<Boolean> = MutableLiveData(false)

    val positive: LiveData<Boolean>
        get() = _positive

    fun onPositive() {
        _positive.value = true
    }

    fun donePositive() {
        _positive.value = false
    }
}