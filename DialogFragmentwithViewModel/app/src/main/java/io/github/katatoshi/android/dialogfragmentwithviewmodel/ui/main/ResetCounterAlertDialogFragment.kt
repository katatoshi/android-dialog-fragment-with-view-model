package io.github.katatoshi.android.dialogfragmentwithviewmodel.ui.main

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels

class ResetCounterAlertDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val viewModel by viewModels<MainViewModel>({ requireParentFragment() })
        val builder = AlertDialog.Builder(requireActivity())
        builder
            .setMessage("Reset counter? (SubFragment)")
            .setPositiveButton("Yes") { _, _ -> viewModel.resetCounter() }
            .setNegativeButton("No") { _, _ -> }
        return builder.create()
    }
}