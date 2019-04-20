package com.franciscomartin.duckhunt

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.franciscomartin.duckhunt.commons.Constants
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

                goToActivity<GameActivity> {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.putExtra(Constants.EXTRA_NICK, nick)
                }

                /*val intent = Intent(this,GameActivity::class.java)
                intent.putExtra(Constants.EXTRA_NICK, nick)
                startActivity(intent)*/
            }

        }
    }
}
