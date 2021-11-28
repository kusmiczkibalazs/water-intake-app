package com.example.assignmentapp.goalachieved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.assignmentapp.R
import com.example.assignmentapp.databinding.FragmentGoalAchievedBinding

class GoalAchievedFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding : FragmentGoalAchievedBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_goal_achieved, container, false)

        val arguments = GoalAchievedFragmentArgs.fromBundle(requireArguments())

        val viewModelFactory = GoalAchievedViewModelFactory()
        val goalAchievedViewModel = ViewModelProvider(this, viewModelFactory).get(GoalAchievedViewModel::class.java)
        binding.goalAchievedViewModel = goalAchievedViewModel

        binding.setLifecycleOwner(this)

        if (goalAchievedViewModel.isGoalAchieved(arguments.intakeQuantity)) {
            val message = "Napi cél elérve (" + arguments.intakeQuantity.toString() + "dl)!"
            binding.message.setText(message)
            binding.achievementImage.setImageResource(R.drawable.green_check_mark)
        } else {
            val message =
                "Még további ${goalAchievedViewModel.calculateRemainingQuantity(arguments.intakeQuantity)}dl folyadék kell a napi cél eléréséhez!"
            binding.message.setText(message)
            binding.achievementImage.setImageResource(R.drawable.red_cross)
        }

        goalAchievedViewModel.navigateBack.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController()
                    .navigate(GoalAchievedFragmentDirections.actionGoalAchievedFragmentToFluidIntakeFragment())
                goalAchievedViewModel.doneNavigateBack()
            }
        })

        return binding.root
    }
}