package com.franciscomartin.duckhunt.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.franciscomartin.duckhunt.R
import com.franciscomartin.duckhunt.adapters.UserRankingRecyclerViewAdapter
import com.franciscomartin.duckhunt.commons.Constants
import com.franciscomartin.duckhunt.models.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


class UserRankingFragment : Fragment() {

    private lateinit var userList : List<User>
    private lateinit var adapter : UserRankingRecyclerViewAdapter
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)

        if (view is RecyclerView) {

            userList = ArrayList<User>()
            adapter =
                UserRankingRecyclerViewAdapter(
                    userList
                )
            view.adapter = adapter

            db.collection(Constants.USER_COLLECTION)
                .orderBy(Constants.DUCKS_FIELD, Query.Direction.DESCENDING)
                .limit(Constants.RANKING_COUNT)
                .get()
                .addOnCompleteListener {

                    if(it!= null){
                        if(it.result != null){
                            userList = it.result!!.map{ document ->
                                User(
                                    document[Constants.NICKNAME_FIELD] as String,
                                    (document[Constants.DUCKS_FIELD]).toString().toLong()
                                )
                            }

                            adapter.putUsers(userList)
                        }
                    }

                }

            // TODO: Test this block
            /*db.collection(Constants.USER_COLLECTION)
                .orderBy(Constants.DUCKS_FIELD, Query.Direction.DESCENDING)
                .limit(Constants.RANKING_COUNT)
                .addSnapshotListener(EventListener<QuerySnapshot>{ snapshots, e ->

                    if(e!= null){
                        if (snapshots != null) {
                            userList = snapshots.documents.map { document ->
//                                document.toObject(User::class.java)!!
//                                User(document[Constants.NICKNAME_FIELD] as String, (document[Constants.DUCKS_FIELD] as String).toInt())

                                val username = document[Constants.NICKNAME_FIELD] as String
                                val score = (document[Constants.DUCKS_FIELD] as Long)
                                User( username, score)
                            }

                            adapter.putUsers(userList)
                        }

                    }

                })*/


        }
        return view
    }

}
