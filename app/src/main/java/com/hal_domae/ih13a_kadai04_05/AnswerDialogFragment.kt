package com.hal_domae.ih13a_kadai04_05

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AnswerDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = requireArguments().getString("TITLE")
        val message = requireArguments().getString("MESSAGE")

        return activity?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK") { _, _ ->
                    (activity as MainActivity).checkQuizCount()
                }
                .create()
        } ?: throw IllegalStateException("アクティビティがありません")
    }

    companion object {
        fun newInstance(title: String, message: String): AnswerDialogFragment {
            val fragment = AnswerDialogFragment()
            val args = Bundle().apply {
                putString("TITLE", title)
                putString("MESSAGE", message)
            }
            fragment.arguments = args
            return fragment
        }
    }
}