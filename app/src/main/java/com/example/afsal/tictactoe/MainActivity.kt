package com.example.afsal.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() , View.OnClickListener{
    var grid = Array<Array<Button?>>(3) { arrayOfNulls(3) }
    var isPlayer1Turn = true
    var round = 0
    var player1Point = 0
    var player2Point = 0
    var player1TextView: TextView? = null
    var player2TextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        player1TextView = findViewById<TextView>(R.id.player_1_text)
        player2TextView = findViewById<TextView>(R.id.player_2_text)
        resetBoard(true)
//        resetBoard(false)

    }
    fun resetBoard(isInitial: Boolean){
        for(i in 0..2){
            for(j in 0..2){
                if(isInitial){
                    var btnStr = "button_$i$j"
                    var resId = resources.getIdentifier(btnStr, "id", this.packageName)
                    var a= findViewById<Button>(resId)
                    a.setText("")
                    a.setOnClickListener(this)
                    grid[i][j] = findViewById<Button>(resId)
                }else{
                    var a = grid[i][j]
                    a?.setText("")
                }
            }
        }
        isPlayer1Turn = true
        round = 0

    }

    override fun onClick(v: View?) {
        var a = v as Button
        if(a.text == ""){
            if (isPlayer1Turn){
                a.setText("X")
                }
            else{
                a.setText("O")
            }
            var win = checkForWin()
            if (win){
                if (isPlayer1Turn){
                    player1Point++
                    Toast.makeText(this, "Player 1 win", Toast.LENGTH_SHORT).show()
                    resetBoard(false)
                }
                else
                {
                    player2Point++
                    Toast.makeText(this, "Player 2 win", Toast.LENGTH_SHORT).show()
                    resetBoard(false)
                }
            }

            else{
                round++
                isPlayer1Turn = !isPlayer1Turn

            }

            if (round ==9){
                Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show()
                resetBoard(false)

            }

        }



    }

    fun checkForWin(): Boolean{
        // check for all rows
        for (i in 0..2){
            if(grid[i][0]?.text == grid[i][1]?.text && grid[i][0]?.text == grid[i][2]?.text && grid[i][0]?.text != ""){
                return true
            }
        }
        //check for all columns
        for (i in 0..2){
            if(grid[0][i]?.text == grid[1][i]?.text && grid[0][i]?.text == grid[2][i]?.text && grid[0][i]?.text != ""){
                return true
            }
        }
        // check the diagonals
        if (grid[0][0]?.text == grid[1][1]?.text && grid[0][0]?.text == grid[2][2]?.text && grid[0][0]?.text != ""){
            return true
        }
        if (grid[2][0]?.text == grid[1][1]?.text && grid[0][0]?.text == grid[0][2]?.text && grid[0][2]?.text != ""){
            return true
        }

        return false


    }
}
