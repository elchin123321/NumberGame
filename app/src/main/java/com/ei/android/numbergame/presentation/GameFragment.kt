package com.ei.android.numbergame.presentation

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ei.android.numbergame.R
import com.ei.android.numbergame.databinding.FragmentGameBinding
import com.ei.android.numbergame.domain.entity.GameResult
import com.ei.android.numbergame.domain.entity.Level

class GameFragment : Fragment() {

    private val args by navArgs<GameFragmentArgs>()


    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        )[GameViewModel::class.java]
    }
    private val viewModelFactory by lazy {
        GameViewModelFactory(args.level,requireActivity().application)

    }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding = null")

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tvOption1)
            add(binding.tvOption2)
            add(binding.tvOption3)
            add(binding.tvOption4)
            add(binding.tvOption5)
            add(binding.tvOption6)
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setClickListenerToOptions()

    }

    private fun setClickListenerToOptions(){
        for(option in tvOptions){
            option.setOnClickListener{
                viewModel.chooseAnswer(option.text.toString().toInt())
            }
        }
    }

    private fun observeViewModel(){
        viewModel.formattedTime.observe(viewLifecycleOwner){
            binding.tvTimer.text = it
        }
        viewModel.gameResult.observe(viewLifecycleOwner){
            launchGameFinishedFragment(it)
        }
        viewModel.question.observe(viewLifecycleOwner){
            binding.tvSum.text = it.sum.toString()
            binding.tvLeftNumber.text = it.visibleNumber.toString()
            for (i in 0 until tvOptions.size){
                tvOptions[i].text = it.options[i].toString()
            }
        }
        viewModel.progressAnswers.observe(viewLifecycleOwner){
            binding.tvAnswersProgress.text = it
        }

        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner){
            binding.progressBar.setProgress(it,true)
        }
        viewModel.enoughCountOfRightAnswers.observe(viewLifecycleOwner){
            binding.tvAnswersProgress.setTextColor(getColorByState(it))
        }
        viewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner){
            val color = getColorByState(it)
            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }
        viewModel.minPercent.observe(viewLifecycleOwner){
            binding.progressBar.secondaryProgress = it
        }
    }

    private fun getColorByState(goodState: Boolean): Int {
        val colorResId = if (goodState) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }
        return  ContextCompat.getColor(requireContext(), colorResId)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchGameFinishedFragment(result:GameResult){
        findNavController().navigate(
            GameFragmentDirections.actionGameFragment2ToGameFinishedFragment(result)
        )
    }

}