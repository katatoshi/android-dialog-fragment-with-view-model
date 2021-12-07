package io.github.katatoshi.android.dialogfragmentwithviewmodel.ui.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

@Deprecated("Use screen specific DialogFragment.")
class AlertDialogViewModel : ViewModel() {

    private val _positive: MutableLiveData<Boolean> = MutableLiveData(false)

    val positive: LiveData<Boolean>
        get() = _positive

    private val _negative: MutableLiveData<Boolean> = MutableLiveData(false)

    val negative: LiveData<Boolean>
        get() = _negative

    fun onPositive() {
        _positive.value = true
    }

    fun donePositive() {
        _positive.value = false
    }

    fun onNegative() {
        _negative.value = true
    }

    fun doneNegative() {
        _negative.value = false
    }
}
