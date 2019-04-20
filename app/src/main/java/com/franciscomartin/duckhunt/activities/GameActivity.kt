package com.franciscomartin.duckhunt.activities

import android.content.DialogInterface
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.Display
import com.franciscomartin.duckhunt.R
import com.franciscomartin.duckhunt.commons.Constants
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class GameActivity : AppCompatActivity() {

    private var counter:Int = 0
    private var screenWidth: Int = 0
    private var screenHeight: Int = 0
    private val random: Random = Random()
    private var gameOver: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val nick:String? = intent.getStringExtra(Constants.EXTRA_NICK)
        textViewNick.text = nick?.let { nick }

        imageViewDuck.setOnClickListener {

            if(!gameOver){
                counter ++
                textViewCounter.text = counter.toString()
                imageViewDuck.setImageResource(R.drawable.duck_clicked)

                Handler().postDelayed({
                    imageViewDuck.setImageResource(R.drawable.duck)
                    moveDuck()
                }, 500)
            }

        }

        initScreen()
        moveDuck()
        initCountDownTimer()

    }

    private fun moveDuck() {
        val min = 0
        val maxX = screenWidth - imageViewDuck.width
        val maxY = screenHeight - imageViewDuck.height

        val randomX = random.nextInt(((maxX - min) + 1) + min)
        val randomY = random.nextInt(((maxY - min) + 1) + min)

        imageViewDuck.x = randomX.toFloat()
        imageViewDuck.y = randomY.toFloat()

    }

    private fun initScreen(){
        val display: Display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
    }

    private fun initCountDownTimer() {
        object:CountDownTimer(Constants.GAME_DURATION, Constants.GAME_INTERVAL){
            override fun onTick(millisUntilFinished: Long) {
                textViewTimer.text = "${millisUntilFinished/1000}s"
            }

            override fun onFinish() {
                textViewTimer.text = "0s"
                gameOver = true
                showDialogGameOver()
            }
        }.start()

    }

    private fun showDialogGameOver(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        val duckHuntedMessage:String =
            if (counter > 1) getString(R.string.game_ducks_hunted, counter)
            else getString(R.string.game_duck_hunted, counter)

        builder.setMessage(duckHuntedMessage)
            .setTitle(R.string.game_over)

        builder.setCancelable(false)

        builder.setPositiveButton(R.string.game_restart){dialog, which ->  
            counter = 0
            textViewCounter.text = "0"
            gameOver = false
            initCountDownTimer()
        }

        builder.setNegativeButton(R.string.game_exit){dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}
