package com.aditya.tictactoe

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_vs_computer.*
import kotlinx.android.synthetic.main.activity_vs_friend.*
import kotlinx.android.synthetic.main.activity_vs_friend.text_view_p1
import kotlinx.android.synthetic.main.activity_vs_friend.text_view_p2
import kotlin.random.Random

class VsComputer : AppCompatActivity(), View.OnClickListener {

    private val buttons = Array(3) { arrayOfNulls<Button>(3) }
    private var player1Turn = true
    private var roundCount = 0
    private var player1Points = 0
    private var computerPoints = 0
    private var textViewPlayer1: TextView? = null
    private var textViewComputer: TextView? = null
    var p1=""
    var p2=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_computer)

//Dialog Box Starts
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


        for (i in 0..2) {
            for (j in 0..2) {
                val buttonID = "button_$i$j"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById(resID)
                buttons[i][j]?.setOnClickListener(this)
            }
        }
        val buttonReset = findViewById<Button>(R.id.button_reset)
        buttonReset.setOnClickListener { resetGame() }
    }


    fun btnClick(view: View){
        val btnSelected = view as Button
        var cellID = 0
        when(btnSelected.id){
            R.id.btn1 -> cellID = 1
            R.id.btn2 -> cellID = 2
            R.id.btn3 -> cellID = 3
            R.id.btn4 -> cellID = 4
            R.id.btn5 -> cellID = 5
            R.id.btn6 -> cellID = 6
            R.id.btn7 -> cellID = 7
            R.id.btn8 -> cellID = 8
            R.id.btn9 -> cellID = 9
        }
        //Toast.makeText(this,"Cell ID:"+cellID,Toast.LENGTH_LONG).show()
        playGame(cellID,btnSelected)
    }
    var player1 = ArrayList<Int>()
    var computer = ArrayList<Int>()

    var activePlayer = 1
    var btnUsed = 0
    private fun playGame(cellID: Int, btnSelected: Button){
        if(activePlayer == 1){
            btnSelected.text = "X"
            btnSelected.setBackgroundColor(Color.parseColor("#6f9fed"))
            player1.add(cellID)
            activePlayer =2
            autoPlay()
        }else{

            btnSelected.text = "O"
            btnSelected.setBackgroundColor(Color.parseColor("#edbb50"))
            computer.add(cellID)
            activePlayer =1

        }
        btnSelected.isEnabled = false


        checkWinner()
    }

    private fun checkWinner(){
        var winner = -1

        //row1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        if(computer.contains(1) && computer.contains(2) && computer.contains(3)){
            winner = 2
        }
        //row2
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if(computer.contains(4) && computer.contains(5) && computer.contains(6)){
            winner = 2
        }
        //row3
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        if(computer.contains(7) && computer.contains(8) && computer.contains(9)){
            winner = 2
        }

        //col1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if(computer.contains(1) && computer.contains(4) && computer.contains(7)){
            winner = 2
        }
        //col2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        if(computer.contains(2) && computer.contains(5) && computer.contains(8)){
            winner = 2
        }
        //col3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        if(computer.contains(3) && computer.contains(6) && computer.contains(9)){
            winner = 2
        }

        // "\"
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner = 1
        }
        if(computer.contains(1) && computer.contains(5) && computer.contains(9)){
            winner = 2
        }
        // "/"
        if(player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner = 1
        }
        if(computer.contains(3) && computer.contains(5) && computer.contains(7)){
            winner = 2
        }


        if(winner != -1){
            if(winner == 1){
                player1Points++
                Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show()
                updatePointsText()
                resetBoard()


            }else{
                computerPoints++
                Toast.makeText(this, "Computer wins!", Toast.LENGTH_SHORT).show()
                updatePointsText()
                resetBoard()

            }
            if(winner == 1 || winner == 2){
                resetBoard()
            }
        }
        btnUsed = 0
        for(cellID in 0..9){
            if(player1.contains(cellID) || computer.contains(cellID)){
                btnUsed++
            }
        }
        if(btnUsed >= 8 && winner == -1){
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
            resetBoard()
        }
    }
    private fun autoPlay(){
        var emptyCells = ArrayList<Int>()
        for(cellID in 1..9){
            if(!((player1.contains(cellID)) || (computer.contains(cellID)))){
                emptyCells.add(cellID)
            }
        }
        val random = Random
        val randIndex = random.nextInt(emptyCells.size-0)+0
        val cellID = emptyCells[randIndex]

        if(!(emptyCells.isEmpty())){
            val btnSelected: Button
            when(cellID){
                1->btnSelected = btn1
                2->btnSelected = btn2
                3->btnSelected = btn3
                4->btnSelected = btn4
                5->btnSelected = btn5
                6->btnSelected = btn6
                7->btnSelected = btn7
                8->btnSelected = btn8
                9->btnSelected = btn9
                else -> btnSelected = btn1
            }
            playGame(cellID,btnSelected)
        }
    }


    private fun updatePointsText() {
        textViewPlayer1!!.text = "$p1: $player1Points"
        textViewComputer!!.text = "$p2: $computerPoints"
    }

    private fun resetBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j]!!.text = ""
            }
        }
        roundCount = 0
        player1Turn = true

    }

    private fun resetGame() {
        player1Points = 0
        computerPoints = 0
        text_view_p1.text = "$p1: 0"
        text_view_p2.text = "$p2: 0"
        updatePointsText()
        resetBoard()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("roundCount", roundCount)
        outState.putInt("player1Points", player1Points)
        outState.putInt("computerPoints", computerPoints)
        outState.putBoolean("player1Turn", player1Turn)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        roundCount = savedInstanceState.getInt("roundCount")
        player1Points = savedInstanceState.getInt("player1Points")
        computerPoints = savedInstanceState.getInt("computerPoints")
        player1Turn = savedInstanceState.getBoolean("player1Turn")
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}
