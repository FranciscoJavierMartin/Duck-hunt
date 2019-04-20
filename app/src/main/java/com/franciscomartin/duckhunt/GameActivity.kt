package com.franciscomartin.duckhunt

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.franciscomartin.duckhunt.commons.Constants
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val nick:String? = intent.getStringExtra(Constants.EXTRA_NICK)
        textViewNick.text = nick?.let { nick }

    }
}
