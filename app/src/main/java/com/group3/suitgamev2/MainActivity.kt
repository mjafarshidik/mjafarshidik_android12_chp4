package com.group3.suitgamev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.group3.suitgamev2.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private var player2Choice: Int? = null
    private var player1Choice: Int? = null
    private var playMode: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        onStartGame()
        onButtonResetClick()
    }

    private fun onStartGame() {
        if (playMode == 0) {
            onPlayerOneChooses()
            onComputerChooses()
        } else {
            onPlayerOneChooses()
        }
    }

    private fun onPlayerOneChooses() {
        var random: Int
        mainBinding.apply {
            ivOptionRockP1.setOnClickListener {
                Log.d("Player 1 is chooses", "Rock")
                player1Choice = 0
                setBgUserShape(0)
                if (playMode != 0) {
                    random = Random.nextInt(0, 3)
                    setBgCompShape(random)
                    startGame(player1Choice, random)
                }
            }
            ivOptionPaperP1.setOnClickListener {
                Log.d("Player 1 is chooses", "Paper")
                player1Choice = 1
                setBgUserShape(1)
                if (playMode != 0) {
                    random = Random.nextInt(0, 3)
                    setBgCompShape(random)
                    startGame(player1Choice, random)
                }
            }
            ivOptionScissorP1.setOnClickListener {
                Log.d("Player 1 is chooses", "Scissor")
                player1Choice = 2
                setBgUserShape(2)
                if (playMode != 0) {
                    random = Random.nextInt(0, 3)
                    setBgCompShape(random)
                    startGame(player1Choice, random)
                }
            }
        }
    }

    private fun onComputerChooses() {
        mainBinding.apply {
            ivRockCom.setOnClickListener {
                Log.d("Computer is choose", "Rock")
                player2Choice = 0
                startGame(player1Choice, player2Choice!!)
            }
            ivPaperCom.setOnClickListener {
                Log.d("Computer is choose", "Paper")
                player2Choice = 1
                startGame(player1Choice, player2Choice!!)
            }
            ivScissorCom.setOnClickListener {
                Log.d("Computer is choose", "Scissor")
                player2Choice = 2
                startGame(player1Choice, player2Choice!!)
            }
        }
    }

    private fun setBgUserShape(i: Int) {
        mainBinding.apply {
            when (PlayerShape.fromInt(i)) {
                PlayerShape.ROCK -> {
                    ivOptionRockP1.background = ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.ic_select
                    )

                    ivOptionPaperP1.setBackgroundColor(0)
                    ivOptionScissorP1.setBackgroundColor(0)
                }
                PlayerShape.PAPER -> {
                    ivOptionRockP1.setBackgroundColor(0)
                    ivOptionPaperP1.background = ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.ic_select
                    )
                    ivOptionScissorP1.setBackgroundColor(0)

                }
                PlayerShape.SCISSOR -> {
                    ivOptionRockP1.setBackgroundColor(0)
                    ivOptionPaperP1.setBackgroundColor(0)
                    ivOptionScissorP1.background = ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.ic_select
                    )
                }
                else -> {
                    ivOptionRockP1.setBackgroundColor(0)
                    ivOptionPaperP1.setBackgroundColor(0)
                    ivOptionScissorP1.setBackgroundColor(0)
                }
            }
        }
    }

    private fun setBgCompShape(random: Int) {
        mainBinding.apply {
            when (PlayerShape.fromInt(random)) {
                PlayerShape.ROCK -> {
                    ivRockCom.background = ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.ic_select
                    )
                    ivPaperCom.setBackgroundColor(0)
                    ivScissorCom.setBackgroundColor(0)
                }
                PlayerShape.PAPER -> {
                    ivRockCom.setBackgroundColor(0)
                    ivPaperCom.background = ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.ic_select
                    )
                    ivScissorCom.setBackgroundColor(0)
                }
                PlayerShape.SCISSOR -> {
                    ivRockCom.setBackgroundColor(0)
                    ivPaperCom.setBackgroundColor(0)
                    ivScissorCom.background = ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.ic_select
                    )
                }
                else -> {
                    ivRockCom.setBackgroundColor(0)
                    ivPaperCom.setBackgroundColor(0)
                    ivScissorCom.setBackgroundColor(0)
                }
            }
        }
    }

    private fun startGame(player1Choice: Int?, player2Choice: Int) {
        if (player1Choice != null) {
            when {
                (player1Choice.plus(1)).mod(3) == player2Choice -> {
                    Log.d("Result is", "Computer won")
                    mainBinding.ivResult.setImageResource(R.drawable.ic_p2win)

                }
                player1Choice == player2Choice -> {
                    Log.d("Result is", "Draw")
                    mainBinding.ivResult.setImageResource(R.drawable.ic_draw)

                }
                else -> {
                    Log.d("Result is", "Player 1 won")
                    mainBinding.ivResult.setImageResource(R.drawable.ic_p1win)
                }
            }
        }
    }

    private fun onButtonResetClick() {
        mainBinding.apply {
            setBgUserShape(-1)
            setBgCompShape(-1)
            player1Choice = null
            player2Choice = null
            ivRefresh.setImageResource(R.drawable.ic_refresh)
            ivRefresh.setOnClickListener {
                ivOptionRockP1.setBackgroundColor(0)
                ivOptionPaperP1.setBackgroundColor(0)
                ivOptionScissorP1.setBackgroundColor(0)
                ivRockCom.setBackgroundColor(0)
                ivPaperCom.setBackgroundColor(0)
                ivScissorCom.setBackgroundColor(0)
                ivResult.setImageResource(R.drawable.ic_versus)
            }
        }
    }
}