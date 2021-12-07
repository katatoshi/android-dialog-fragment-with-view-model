package io.github.katatoshi.android.dialogfragmentwithviewmodel.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import io.github.katatoshi.android.dialogfragmentwithviewmodel.R
import io.github.katatoshi.android.dialogfragmentwithviewmodel.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        val viewModel by viewModels<MainViewModel>()
        binding.viewModel = viewModel

        viewModel.showResetCounterAlert.observe(viewLifecycleOwner) {
            if (it) {
                ResetCounterAlertDialogFragment().show(parentFragmentManager, "reset_counter_alert")
                viewModel.doneShowingResetCounterAlert()
            }
        }

        viewModel.navigateToSub.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToSubFragment())
                viewModel.doneNavigatingToSub()
            }
        }

        val resetCounterAlertViewModel by activityViewModels<ResetCounterAlertViewModel>()

        resetCounterAlertViewModel.positive.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.resetCounter()
                resetCounterAlertViewModel.donePositive()
            }
        }

        return binding.root
    }
}