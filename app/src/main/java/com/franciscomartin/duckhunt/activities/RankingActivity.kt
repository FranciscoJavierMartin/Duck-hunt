package com.franciscomartin.duckhunt.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.franciscomartin.duckhunt.R
import com.franciscomartin.duckhunt.fragments.UserRankingFragment


class RankingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, UserRankingFragment())
            .commit()
    }

}
