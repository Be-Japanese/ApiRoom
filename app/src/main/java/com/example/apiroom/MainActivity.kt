package com.example.apiroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apiroom.RecyclerView.RvAdapter
import com.example.apiroom.data.entity.postEntity
import com.example.apiroom.resource.Resource
import com.example.apiroom.room.PostDb
import com.example.apiroom.viewmodel.postViewModel

class MainActivity : AppCompatActivity() {

    lateinit var progressBar: ProgressBar
    // lateinit var textView: TextView
    lateinit var rv:RecyclerView

    lateinit var rvAdapter : RvAdapter

    val mvm : postViewModel by viewModels()

    fun initui()
    {
        progressBar = findViewById(R.id.progressBar)

        rv = findViewById(R.id.rv)
        //  textView = findViewById(R.id.insert)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initui()
        PostDb.initDB(applicationContext);


        val observer:Observer<Resource<List<postEntity>>> = Observer {
            when(it) {
                is Resource.Loading -> progressBar.visibility = ProgressBar.VISIBLE

                is  Resource.Successful->{
                    progressBar.visibility = ProgressBar.GONE
                    rvAdapter = RvAdapter(it)
                    rv.layoutManager = LinearLayoutManager(this)
                    rv.adapter = rvAdapter


                }
                is Resource.Error ->
                {
                    progressBar.visibility = ProgressBar.GONE
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
                }



                is Resource.Inserted ->
                {
                    progressBar.visibility = ProgressBar.GONE
                    Toast.makeText(this, "Inserted To Database Successfully", Toast.LENGTH_SHORT).show()


                }

            }

        }

        mvm.postlivedata.observe(this,observer)

        mvm.getallposts()




    }
}