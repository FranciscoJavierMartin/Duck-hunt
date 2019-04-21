package com.franciscomartin.duckhunt.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.franciscomartin.duckhunt.R

import com.franciscomartin.duckhunt.models.User
import kotlinx.android.synthetic.main.fragment_user_item.view.*

class UserRankingRecyclerViewAdapter(
        private var items: List<User>
    ) : RecyclerView.Adapter<UserRankingRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_user_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], position)

    override fun getItemCount(): Int = items.size

    fun putUsers(userList: List<User>){
        items = userList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User, position: Int) = with(itemView){
            textViewPosition.text = "${position+1}º"
            textViewNickname.text = user.nickname
            textViewDucks.text = user.ducks.toString()
        }
    }

}
