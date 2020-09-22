package com.pratik.demoapp.utils

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.pratik.demoapp.R


fun showProgressDialog(progressDialog: ProgressDialog) {
    progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    progressDialog.isIndeterminate = true
    progressDialog.setCancelable(false)
    progressDialog.show()
    progressDialog.setContentView(R.layout.item_progress)
}

fun hideProgressDialog(progressDialog: ProgressDialog) {
    if (progressDialog.isShowing) {
        progressDialog.hide()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

interface AlertDialogClickListener {
    fun onAlertDialogPositiveClickListener(requestCode: Int)
}