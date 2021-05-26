package com.albertabdullin.twitchtopgames.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.albertabdullin.twitchtopgames.R

class AppReviewDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(requireContext())
                    .setView(R.layout.app_review_dialog)
                    .setPositiveButton(R.string.send) { dialog, which -> }
                    .setNegativeButton(R.string.cancel) { dialog, which -> }
                    .create()
    companion object {
        const val TAG = "AppReviewDialogFragment"
    }
}