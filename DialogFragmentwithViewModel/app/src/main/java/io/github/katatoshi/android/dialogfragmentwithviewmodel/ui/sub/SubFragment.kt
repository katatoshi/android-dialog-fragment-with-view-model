package io.github.katatoshi.android.dialogfragmentwithviewmodel.ui.sub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import io.github.katatoshi.android.dialogfragmentwithviewmodel.R
import io.github.katatoshi.android.dialogfragmentwithviewmodel.databinding.FragmentSubBinding
import io.github.katatoshi.android.dialogfragmentwithviewmodel.ui.dialog.AlertDialogFragment
import io.github.katatoshi.android.dialogfragmentwithviewmodel.ui.dialog.AlertViewModel

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
                AlertDialogFragment.newInstance("Reset counter? (SubFragment)", "Yes", "No")
                    .show(childFragmentManager, null)
                viewModel.doneShowingResetCounterAlert()
            }
        }

        viewModel.navigateToMain.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(SubFragmentDirections.actionSubFragmentToMainFragment())
                viewModel.doneNavigatingToMain()
            }
        }

        viewModel.navigateToAppendix.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(SubFragmentDirections.actionSubFragmentToAppendixFragment())
                viewModel.doneNavigatingToAppendix()
            }
        }

        val alertViewModel by viewModels<AlertViewModel>()

        alertViewModel.positive.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.resetCounter()
                alertViewModel.donePositive()
            }
        }

        alertViewModel.negative.observe(viewLifecycleOwner) {
            if (it) {
                alertViewModel.doneNegative()
            }
        }

        return binding.root
    }
}