package com.lfx.shopuna.utils

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.lfx.shopuna.R

class NetworkDialogUtils(val context: Context, val inflater: LayoutInflater) {
    var activeDialog: AlertDialog? = null
    fun showErrorDialog(error: String?) {
        baseShowDialog(R.layout.dialog_error, error)
    }

    fun showSuccessDialog(message: String?) {
        baseShowDialog(R.layout.dialog_success, message)
    }

    fun showLoadingDialog() {
        baseShowDialog(R.layout.dialog_load, null)
    }

    private fun baseShowDialog(dialogView: Int, message: String?) {
        if ((activeDialog == null) or (activeDialog?.isShowing == false)) {
            Log.d("TAG", "baseShowDialog:$dialogView ")
            activeDialog?.dismiss()
            val builder = AlertDialog.Builder(context)
            val content = inflater.inflate(dialogView, null)
            builder.setView(content)
            val messageField = content.findViewById<TextView>(R.id.message)
            messageField.text = message
            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            activeDialog = dialog
        }
    }
}
