package io.github.katatoshi.android.dialogfragmentwithviewmodel.ui.appendix

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ResetCounterAlertDialogFragment : DialogFragment() {

    private lateinit var listener: ResetCounterAlertDialogListener

    interface ResetCounterAlertDialogListener {

        fun onDialogPositiveClick(dialog: DialogFragment)

        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val parentFragment = requireParentFragment()
        try {
            listener = parentFragment as ResetCounterAlertDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$parentFragment must implement ResetCounterAlertDialogListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder
            .setMessage("Reset counter? (AppendixFragment)")
            .setPositiveButton("Yes") { _, _ -> listener.onDialogPositiveClick(this) }
            .setNegativeButton("No") { _, _ -> listener.onDialogNegativeClick(this) }
        return builder.create()
    }
}