package com.franciscomartin.duckhunt.activities

import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        initScreen()

        val nick:String? = intent.getStringExtra(Constants.EXTRA_NICK)
        textViewNick.text = nick?.let { nick }

        imageViewDuck.setOnClickListener {
            counter ++
            textViewCounter.text = counter.toString()
            imageViewDuck.setImageResource(R.drawable.duck_clicked)

            Handler().postDelayed({
                imageViewDuck.setImageResource(R.drawable.duck)
                moveDuck()
            }, 500)

        }

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
}
