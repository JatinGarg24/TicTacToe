package com.aditya.tictactoe

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class VsComputer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_computer)

        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.vscomputer_dialog,null)
        val playerName=dialogView.findViewById<EditText>(R.id.player1_name)
        dialog.setView(dialogView)
        dialog.setPositiveButton("Let's Play!!") { dialogInterface: DialogInterface, i: Int ->  }
        dialog.setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int ->  }
        val customDialog = dialog.create()
        customDialog.show()
        customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            if(playerName.text.isBlank())
                Toast.makeText(baseContext, "Please enter player name", Toast.LENGTH_LONG).show()
            else
                customDialog.dismiss()
        }
        customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener{
            val intent = Intent(this, Dashboard::class.java).apply {}
            startActivity(intent)
        }
    }
}