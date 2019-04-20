package com.franciscomartin.duckhunt.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.franciscomartin.duckhunt.R
import com.franciscomartin.duckhunt.commons.Constants
import com.franciscomartin.duckhunt.goToActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var nick: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonStart.setOnClickListener {
            nick = editTextNick.text.toString()

            if (nick.length < 3){
                editTextNick.error = getString(R.string.login_nick_error_empty)
            } else if(nick.length > 7) {
                editTextNick.error = getString(R.string.login_nick_error_longer)
            } else {
                editTextNick.text.clear()

                /*val intent = Intent(this, GameActivity::class.java)
                intent.putExtra(Constants.EXTRA_NICK, nick)
                startActivity(intent)*/

                goToActivity<GameActivity> {
                    putExtra(Constants.EXTRA_NICK, nick)
                }
            }

        }
    }
}
