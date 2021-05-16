package com.emreergun.dependencyinjectiondagger2.ui.main.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emreergun.dependencyinjectiondagger2.R
import com.emreergun.dependencyinjectiondagger2.models.post.Post


class PostRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.titlePostItem)
}

class PostRecyclerViewAdapter:RecyclerView.Adapter<PostRecyclerViewHolder>() {

    private var postList= emptyList<Post>()
    fun setPostList(list:List<Post>){
        postList=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostRecyclerViewHolder {
        val root=LayoutInflater.from(parent.context).inflate(R.layout.layout_post_lit_item,parent,false)
        return PostRecyclerViewHolder(root)
    }

    override fun onBindViewHolder(holder: PostRecyclerViewHolder, position: Int) {
        val currentPost=postList[position]
        holder.title.text=currentPost.title
    }

    override fun getItemCount(): Int {
        return postList.size
    }


}