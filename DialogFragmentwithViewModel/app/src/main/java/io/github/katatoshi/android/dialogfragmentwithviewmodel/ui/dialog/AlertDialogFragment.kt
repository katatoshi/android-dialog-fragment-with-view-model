package io.github.katatoshi.android.dialogfragmentwithviewmodel.ui.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels

class AlertDialogFragment : DialogFragment() {

    val message: String by lazy {
        arguments?.getString(MESSAGE_KEY, "") ?: ""
    }

    val positiveText: String by lazy {
        arguments?.getString(POSITIVE_TEXT_KEY, "") ?: ""
    }

    val negativeText: String by lazy {
        arguments?.getString(NEGATIVE_TEXT_KEY, "") ?: ""
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val viewModel by activityViewModels<AlertDialogViewModel>()
        val builder = AlertDialog.Builder(requireActivity())
        builder
            .setMessage(message)
            .setPositiveButton(positiveText) { _, _ -> viewModel.onPositive() }
            .setNegativeButton(negativeText) { _, _ -> viewModel.onNegative() }
        return builder.create()
    }

    companion object {

        val MESSAGE_KEY = "message"

        val POSITIVE_TEXT_KEY = "positive_text"

        val NEGATIVE_TEXT_KEY = "negative_text"

        fun newInstance(
            message: String,
            positiveText: String,
            negativeText: String
        ): AlertDialogFragment {
            val fragment = AlertDialogFragment()

            val args = Bundle()
            args.putString(MESSAGE_KEY, message)
            args.putString(POSITIVE_TEXT_KEY, positiveText)
            args.putString(NEGATIVE_TEXT_KEY, negativeText)
            fragment.arguments = args

            return fragment
        }
    }
}
