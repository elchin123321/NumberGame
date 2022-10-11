package com.ei.android.numbergame.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ei.android.numbergame.R
import com.ei.android.numbergame.databinding.FragmentGameFinishedBinding
import com.ei.android.numbergame.databinding.FragmentWelcomeBinding
import com.ei.android.numbergame.domain.entity.GameResult
import com.ei.android.numbergame.domain.entity.Level

class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private val gameResult by lazy {
        args.gameResult
    }

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding = null")



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(gameResult)
        {
            if (winner)
                binding.emojiResult.setImageResource(R.drawable.ic_smile)
            else
                binding.emojiResult.setImageResource(R.drawable.ic_sad)
            binding.tvRequiredAnswers.text = String.format(
                getString(R.string.required_score),
                gameSettings.minCountOfRightAnswers.toString()
            )
            binding.tvScoreAnswers.text = String.format(
                getString(R.string.score_answers), countOfRightAnswers.toString()
            )
            binding.tvRequiredPercentage.text = String.format(
                getString(R.string.required_percentage), gameSettings.minPercentOfRightAnswer.toString()
            )
            binding.tvScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                calculatePercentOfRightAnswers()
            )
        }

        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun calculatePercentOfRightAnswers(): Int {
        return if(gameResult.cuntOfQuestions == 0)
            0
        else
            ((gameResult.countOfRightAnswers / gameResult.cuntOfQuestions.toDouble()) * 100).toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    private fun retryGame() {
        findNavController().popBackStack()
    }


}