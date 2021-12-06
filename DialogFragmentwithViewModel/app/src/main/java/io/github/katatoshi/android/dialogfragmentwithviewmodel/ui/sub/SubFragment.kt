package io.github.katatoshi.android.dialogfragmentwithviewmodel.ui.sub

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import io.github.katatoshi.android.dialogfragmentwithviewmodel.R
import io.github.katatoshi.android.dialogfragmentwithviewmodel.databinding.FragmentSubBinding

class SubFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSubBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sub, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        val viewModel by viewModels<SubViewModel>()
        binding.viewModel = viewModel

        viewModel.showResetCounterAlert.observe(viewLifecycleOwner) {
            if (it) {
                ResetCounterDialogFragment().show(parentFragmentManager, "reset_counter_alert")
                viewModel.doneShowingResetCounterAlert()
            }
        }

        viewModel.navigateToMain.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(SubFragmentDirections.actionSubFragmentToMainFragment())
                viewModel.doneNavigatingToMain()
            }
        }

        val resetCounterDialogFragmentVM by activityViewModels<ResetCounterDialogFragment.VM>()

        resetCounterDialogFragmentVM.positive.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.resetCounter()
                resetCounterDialogFragmentVM.donePositive()
            }
        }

        return binding.root
    }

    class ResetCounterDialogFragment : DialogFragment() {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val viewModel by activityViewModels<VM>()
            val builder = AlertDialog.Builder(requireActivity())
            builder
                .setMessage("Reset counter? (SubFragment)")
                .setPositiveButton("Yes") { _, _ -> viewModel.onPositive() }
                .setNegativeButton("No") { _, _ -> }
            return builder.create()
        }

        class VM : ViewModel() {

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
    }
}