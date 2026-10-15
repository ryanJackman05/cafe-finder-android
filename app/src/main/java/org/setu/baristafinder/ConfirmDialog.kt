package org.setu.baristafinder

import android.content.Context
import android.app.Dialog
import android.widget.Button
import android.widget.TextView

// General-purpose and configurable confirm-cancel Dialog handler for use in this project
class ConfirmDialog (private val context: Context) {
    fun show(
        headerText: String,
        dialogText: String,
        confirmText: String = "Confirm",
        cancelText: String = "Cancel",
        onConfirm: () -> Unit // configurable lambda for the confirm action
    ) {
        val dialog = Dialog(context) //
        dialog.setContentView(R.layout.confirm_dialog)

        val header = dialog.findViewById<TextView>(R.id.headerText)
        header.text = headerText
        val message = dialog.findViewById<TextView>(R.id.dialogText)
        message.text = dialogText

        val confirmButton = dialog.findViewById<Button>(R.id.dialogConfirmButton)
        confirmButton.text = confirmText
        val cancelButton = dialog.findViewById<Button>(R.id.dialogCancelButton)
        cancelButton.text = cancelText

        confirmButton.setOnClickListener {
            dialog.dismiss()
            onConfirm() // the passed lambda
        }
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}