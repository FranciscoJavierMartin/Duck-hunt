package com.franciscomartin.duckhunt.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.franciscomartin.duckhunt.R
import com.franciscomartin.duckhunt.commons.Constants
import com.franciscomartin.duckhunt.goToActivity
import com.franciscomartin.duckhunt.models.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var nickname: String
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonStart.setOnClickListener {
            nickname = editTextNickname.text.toString()

            if (nickname.length < 3){
                editTextNickname.error = getString(R.string.login_nick_error_empty)
            } else if(nickname.length > 7) {
                editTextNickname.error = getString(R.string.login_nick_error_longer)
            } else {

                addNicknameToDBAndStart()


            }

        }
    }

    private fun addNicknameToDBAndStart(){

        db.collection(Constants.USER_COLLECTION)
            .whereEqualTo(Constants.NICKNAME_FIELD, nickname).get()
            .addOnSuccessListener {
                if(it.size() > 0){
                    editTextNickname.error = getString(R.string.login_nick_is_not_available, nickname)
                } else {
                    addNickToFirestore()
                }
            }


    }

    private fun addNickToFirestore() {
        db.collection(Constants.USER_COLLECTION)
            .add(User(nickname, 0))
            .addOnSuccessListener {

                editTextNickname.text.clear()

                goToActivity<GameActivity> {
                    putExtra(Constants.EXTRA_NICKNAME, nickname)
                    putExtra(Constants.EXTRA_ID, it.id)
                }

            }

    }
}
