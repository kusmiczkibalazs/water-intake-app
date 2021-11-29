package com.example.assignmentapp.fluidintake

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.assignmentapp.R
import com.example.assignmentapp.database.FluidIntakeDatabase
import com.example.assignmentapp.databinding.FragmentFluidIntakeBinding

class FluidIntakeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : FragmentFluidIntakeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_fluid_intake, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = FluidIntakeDatabase.getInstance(application).fluidIntakeDatabaseDao
        val viewModelFactory = FluidIntakeViewModelFactory(dataSource, application)

        val fluidIntakeViewModel =
            ViewModelProvider(
                    this, viewModelFactory).get(FluidIntakeViewModel::class.java)

        binding.fluidIntakeViewModel = fluidIntakeViewModel

        binding.setLifecycleOwner(this)


        fluidIntakeViewModel.submitPushed.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                try {
                    val input: Editable? = binding.intakeInput.text
                    if (!input.isNullOrEmpty() && input.toString().toInt() > 0) {
                        fluidIntakeViewModel.inputIntakeQuantity = input.toString().toInt()
                        binding.intakeInput.text.clear()
                        hideKeyboard()
                    }
                } catch (e: NumberFormatException) {
                    Log.e("FluidIntakeFragment", e.toString())
                }

                fluidIntakeViewModel.doneGettingInputFromFragment()
            }
        })

        fluidIntakeViewModel.clearPushed.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                hideKeyboard()
                fluidIntakeViewModel.doneHidingKeyboard()
            }
        })

        fluidIntakeViewModel.navigateToResultFragment.observe(viewLifecycleOwner, Observer { intakeQuantity ->
            intakeQuantity?.let {
                this.findNavController().navigate(FluidIntakeFragmentDirections.actionFluidIntakeFragmentToGoalAchievedFragment(intakeQuantity))
                fluidIntakeViewModel.doneNavigatingToResultFragment()
            }
        })

        fluidIntakeViewModel.clearButtonVisibility.observe(viewLifecycleOwner, Observer {
            val clearButton :Button = binding.clearButton
            if (it == true) {
                clearButton.visibility = View.VISIBLE
            } else {
                clearButton.visibility = View.INVISIBLE
            }
        })

        val adapter = FluidIntakeAdapter()
        binding.intakeList.adapter = adapter

        fluidIntakeViewModel.intakes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}