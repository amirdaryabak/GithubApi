package com.amirdaryabak.githubapi.ui

import android.app.Dialog
import android.os.Bundle
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.amirdaryabak.githubapi.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    fun showSnackbar(
        text: String = "خطا در برقراری ارتباط",
        actionText: String = "تلاش مجدد",
        duration: Int = Snackbar.LENGTH_LONG,
        callFunction: () -> Unit = {}
    ) {
        Snackbar.make(
            requireView(), text,
            duration
        ).setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
            .setAction(actionText) {
                callFunction.invoke()
            }.apply {
                setActionTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            }
            .show()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener {
            val bottomSheet = dialog.findViewById<FrameLayout>(
                com.google.android.material.R.id.design_bottom_sheet
            )
            val behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        return dialog

    }

}