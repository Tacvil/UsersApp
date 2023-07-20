package com.example.myapplication.fragments.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.database.models.UserModel

class MainAdapter(private val usersList: List<UserModel>) :
    RecyclerView.Adapter<MainAdapter.MainHolder>() {

    class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.text_view_user_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.account_item, parent, false)
        return MainHolder(view)
    }

    override fun getItemCount(): Int = usersList.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.tvTitle.text = usersList[position].nameUser
    }
}