package com.example.sampleproject.core.utils

import android.os.Build
import android.view.MotionEvent
import android.view.View
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInteropFilter

fun Modifier.clearFocusOnTap(view: View, focusManager: FocusManager): Modifier =
    this.pointerInteropFilter { event ->
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val focusedView = view.findFocus()
                val outRect = android.graphics.Rect()
                focusedView?.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    focusManager.clearFocus()
                }
            } else {
                focusManager.clearFocus()
            }
        }
        false
    }
