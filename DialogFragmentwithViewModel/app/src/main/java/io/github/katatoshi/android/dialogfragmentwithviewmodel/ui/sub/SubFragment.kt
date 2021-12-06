package io.github.katatoshi.android.dialogfragmentwithviewmodel.ui.sub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import io.github.katatoshi.android.dialogfragmentwithviewmodel.R
import io.github.katatoshi.android.dialogfragmentwithviewmodel.databinding.FragmentSubBinding
import io.github.katatoshi.android.dialogfragmentwithviewmodel.ui.dialog.AlertDialogFragment
import io.github.katatoshi.android.dialogfragmentwithviewmodel.ui.dialog.AlertDialogViewModel

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
                AlertDialogFragment.newInstance("Reset counter? (Sub Fragment)", "Yes", "No")
                    .show(parentFragmentManager, "reset_counter_alert")
                viewModel.doneShowingResetCounterAlert()
            }
        }

        viewModel.navigateToMain.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(SubFragmentDirections.actionSubFragmentToMainFragment())
                viewModel.doneNavigatingToMain()
            }
        }

        val alertDialogViewModel by activityViewModels<AlertDialogViewModel>()

        alertDialogViewModel.positive.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.resetCounter()
                alertDialogViewModel.donePositive()
            }
        }

        alertDialogViewModel.negative.observe(viewLifecycleOwner) {
            if (it) {
                alertDialogViewModel.doneNegative()
            }
        }

        return binding.root
    }
}