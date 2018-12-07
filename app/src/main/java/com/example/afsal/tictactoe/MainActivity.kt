package com.example.afsal.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() , View.OnClickListener{
    var grid = Array<Array<Button?>>(3) { arrayOfNulls(3) }
    var isPlayer1Turn = true
    var round = 0
    var player1Point = 0
    var player2Point = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        if (isPlayer1Turn){

            if(a.text== ""){

                a.setText("X")
                isPlayer1Turn = !isPlayer1Turn
                round++
            }
        }else{
            if(a.text == ""){

                a.setText("O")
                isPlayer1Turn = !isPlayer1Turn
                round++
            }
        }

        if (round ==9){
            Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show()
            resetBoard(false)

        }


    }
}
