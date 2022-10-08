package com.ei.android.numbergame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ei.android.numbergame.R
import com.ei.android.numbergame.databinding.FragmentGameBinding
import com.ei.android.numbergame.domain.entity.GameResult
import com.ei.android.numbergame.domain.entity.GameSettings
import com.ei.android.numbergame.domain.entity.Level

class GameFragment : Fragment() {

    private lateinit var level: Level

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
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
        binding.tvOption1.setOnClickListener{
            val gameResult = GameResult(
                false,
                20,
                20,
                GameSettings(0, 0, 0, 0)
            )
            launchGameFinishedFragment(gameResult)
        }
        binding.tvOption2.setOnClickListener{
            val gameResult = GameResult(
                true,
                20,
                20,
                GameSettings(0, 0, 0, 0)
            )
            launchGameFinishedFragment(gameResult)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchGameFinishedFragment(result:GameResult){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,GameFinishedFragment.newInstance(result))
            .addToBackStack(null)
            .commit()
    }

    private fun parseArgs(){
        level = requireArguments().getSerializable(KEY_LEVEL) as Level
    }

    companion object {
        private const val KEY_LEVEL = "level"
        const val NAME = "game fragment"
        fun newInstance(level: Level):GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_LEVEL,level)
                }
            }
        }

    }
}