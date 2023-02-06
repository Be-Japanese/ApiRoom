package com.example.apiroom.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apiroom.R
import com.example.apiroom.data.entity.postEntity
import com.example.apiroom.resource.Resource

class RvAdapter(val rlist: Resource<List<postEntity>>):RecyclerView.Adapter<RvAdapter.viewholder>() {


    class viewholder(view: View):RecyclerView.ViewHolder(view)
    {
        var sttitle : TextView = view.findViewById(R.id.sttitle)
        var stbody : TextView = view.findViewById(R.id.stbody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        var x = LayoutInflater.from(parent.context).inflate(R.layout.post_list,parent,false)
        return viewholder(x)
    }

    override fun getItemCount(): Int {
       var count = 0
        if(rlist is Resource.Successful)
            count = rlist.data.size
        return count
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        if(rlist is Resource.Successful)
        {
            holder.sttitle.text = rlist.data[position].title
            holder.stbody.text = rlist.data[position].body
        }




    }
}