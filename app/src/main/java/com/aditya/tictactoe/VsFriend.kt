package com.aditya.tictactoe

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.inflate
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class VsFriend : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_friend)

        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.vsfriend_dialog,null)
        val player1 = dialogView.findViewById<EditText>(R.id.player1_name)
        val player2 = dialogView.findViewById<EditText>(R.id.player2_name)
        dialog.setView(dialogView)
        dialog.setPositiveButton("Let's Play!!") { Dialog: DialogInterface, i: Int ->}
        dialog.setNegativeButton("Cancel"){Dialog: DialogInterface, i: Int ->}
        val customDialog = dialog.create()
        customDialog.show()
        customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            if(player1.text.isBlank())
                Toast.makeText(baseContext, "Please enter player 1 name", Toast.LENGTH_LONG).show()
            else if(player2.text.isBlank())
                Toast.makeText(baseContext, "Please enter player 2 name", Toast.LENGTH_LONG).show()
            else
                customDialog.dismiss()
        }
        customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener{
            val intent = Intent(this, Dashboard::class.java).apply {}
            startActivity(intent)

        }
    }
}
