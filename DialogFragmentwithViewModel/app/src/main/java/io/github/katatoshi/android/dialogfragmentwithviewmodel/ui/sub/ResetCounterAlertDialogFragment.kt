package io.github.katatoshi.android.dialogfragmentwithviewmodel.ui.sub

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels

class ResetCounterAlertDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val viewModel by activityViewModels<ResetCounterAlertViewModel>()
        val builder = AlertDialog.Builder(requireActivity())
        builder
            .setMessage("Reset counter? (SubFragment)")
            .setPositiveButton("Yes") { _, _ -> viewModel.onPositive() }
            .setNegativeButton("No") { _, _ -> }
        return builder.create()
    }
}