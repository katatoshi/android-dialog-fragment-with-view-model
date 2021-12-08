package io.github.katatoshi.android.dialogfragmentwithviewmodel.ui.appendix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import io.github.katatoshi.android.dialogfragmentwithviewmodel.R
import io.github.katatoshi.android.dialogfragmentwithviewmodel.databinding.FragmentAppendixBinding

class AppendixFragment : Fragment(),
    ResetCounterAlertDialogFragment.ResetCounterAlertDialogListener {

    private lateinit var binding: FragmentAppendixBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_appendix, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        val viewModel by viewModels<AppendixViewModel>()
        binding.viewModel = viewModel

        viewModel.showResetCounterAlert.observe(viewLifecycleOwner) {
            if (it) {
                ResetCounterAlertDialogFragment().show(childFragmentManager, null)
                viewModel.doneShowingResetCounterAlert()
            }
        }

        viewModel.navigateToMain.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(AppendixFragmentDirections.actionAppendixFragmentToMainFragment())
                viewModel.doneNavigatingToMain()
            }
        }

        return binding.root
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        binding.viewModel?.resetCounter()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
    }
}
