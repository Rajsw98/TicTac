package com.example.gametictac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buClick(view: View){
        val buSelected= view as Button
        var cellId=0
        when(buSelected.id){
            R.id.bu1-> cellId=1
            R.id.bu2-> cellId=2
            R.id.bu3-> cellId=3
            R.id.bu4-> cellId=4
            R.id.bu5-> cellId=5
            R.id.bu6-> cellId=6
            R.id.bu7-> cellId=7
            R.id.bu8-> cellId=8
            R.id.bu9-> cellId=9
        }
        playGame(cellId,buSelected)
    }

    var activePlayer=1
    var player1=ArrayList<Int>()
    var player2=ArrayList<Int>()
    fun playGame(cellId: Int, buSelected:Button){
        if (activePlayer==1){
            buSelected.text="x"
            buSelected.setBackgroundResource(R.color.Blue)
            player1.add(cellId)
            activePlayer=2
            autoplay()

        }else{
            buSelected.text="o"
            buSelected.setBackgroundResource(R.color.yellow)
            player2.add(cellId)
            activePlayer=1
        }
        buSelected.isEnabled=false
        checkWinner()
    }
    fun checkWinner(){
        var winner=0

        //row1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }

        //row2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }

        //row3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        //colom1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }

        //colom2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }

        //colom3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        //triangle1
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }

        //triangle2
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        if (winner == 1){
            player1winscounts+=1
            Toast.makeText(this,"Player1 is win the game ",Toast.LENGTH_LONG).show()
            restartGame()

        }else if(winner == 2){
            player2winscounts+=1
            Toast.makeText(this,"Player2 is win the game ",Toast.LENGTH_LONG).show()
            restartGame()
        }
    }

    //AutoplayLogic

    fun  autoplay(){
        var emptycells=ArrayList<Int>()
        for(cellId in 1..9){
            if (!(player1.contains(cellId) || (player2.contains(cellId)))){
                emptycells.add(cellId)
            }
        }

        val r= Random
        val randomIndex=r.nextInt(emptycells.size)
        val cellId=emptycells[randomIndex]


        var buSelected:Button?
        buSelected=when(cellId){
            1->bu1
            2->bu2
            3->bu3
            4->bu4
            5->bu5
            6->bu6
            7->bu7
            8->bu8
            9->bu9
            else -> {
                bu1
            }
        }
        playGame(cellId,buSelected)



    }
    var player1winscounts=0
    var player2winscounts=0
    fun restartGame(){
        activePlayer=1
        player1.clear()
        player2.clear()

        for (cellId in 1..9){
            var buselected:Button?=when(cellId){
                1->bu1
                2->bu2
                3->bu3
                4->bu4
                5->bu5
                6->bu6
                7->bu7
                8->bu8
                9->bu9
                else -> {
                    bu1
                }


            }
            buselected!!.text=""
            buselected.setBackgroundResource(R.color.white)
            buselected!!.isEnabled=true
        }
        Toast.makeText(this,"player1: $player1winscounts , player2: $player2winscounts",Toast.LENGTH_LONG).show()
    }
}