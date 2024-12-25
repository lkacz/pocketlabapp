package com.lkacz.pola

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class InstructionFragment : Fragment() {
    private var header: String? = null
    private var body: String? = null
    private var nextButtonText: String? = null
    private lateinit var logger: Logger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            header = it.getString("HEADER")
            body = it.getString("BODY")
            nextButtonText = it.getString("NEXT_BUTTON_TEXT")
        }
        logger = Logger.getInstance(requireContext())
        logger.logInstructionFragment(header ?: "Default Header", body ?: "Default Body")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_instruction, container, false)

        InstructionUiHelper.setupInstructionViews(
            view,
            header ?: "Default Header",
            body ?: "Default Body",
            nextButtonText
        ) {
            // Transition to the next fragment
            (activity as MainActivity).loadNextFragment()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(header: String?, body: String?, nextButtonText: String?) =
            InstructionFragment().apply {
                arguments = Bundle().apply {
                    putString("HEADER", header)
                    putString("BODY", body)
                    putString("NEXT_BUTTON_TEXT", nextButtonText)
                }
            }
    }
}
