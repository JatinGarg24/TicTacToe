package com.aditya.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        var vsComputer = findViewById<Button>(R.id.vs_computer)
        var vsFriend =  findViewById<Button>(R.id.vs_friend)

        vsComputer.setOnClickListener {
            var intent = Intent(this, VsComputer::class.java)
            startActivity(intent)
        }

        vsFriend.setOnClickListener {
            var intent = Intent(this, VsFriend::class.java)
            startActivity(intent)
        }
    }
}