package com.example.cs427_advanced_android

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cs427_advanced_android.network.QuotesApi
import com.example.cs427_advanced_android.network.RetrofitHelper
import com.example.task.MyAdapter
import kotlinx.coroutines.*

class HomeActivity : AppCompatActivity() {

    private lateinit var newRecycleview: RecyclerView
    private lateinit var newArrayList: ArrayList<CarParser>
    private lateinit var heading: Array<String>
    private lateinit var price: Array<Int>
    private lateinit var from: Array<String>
    private lateinit var to: Array<String>
    private lateinit var headingArr: ArrayList<String>
    private lateinit var priceArr: ArrayList<Int>
    private lateinit var fromArr: ArrayList<String>
    private lateinit var toArr: ArrayList<String>
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        heading = arrayOf()
        price = arrayOf()
        from = arrayOf()
        to = arrayOf()

        headingArr = ArrayList<String>()
        priceArr = ArrayList<Int>()
        fromArr = ArrayList<String>()
        toArr = ArrayList<String>()

        val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)
        // launching a new coroutine
        runBlocking<Unit> {
            launch(Dispatchers.IO){
                val result = quotesApi.getQuotes()
                for(x in result.body()?.results!!.indices) {
                    headingArr.add(result.body()?.results!![x].author)
                    priceArr.add(result.body()?.results!![x].length)
                    fromArr.add(result.body()?.results!![x].dateAdded)
                    toArr.add(result.body()?.results!![x].dateModified)
                }
                Log.d("before", toArr.toString())
            }
        }
        Log.d("after", toArr.toString())
        newRecycleview = findViewById(R.id.GG)
        newRecycleview.layoutManager = LinearLayoutManager(this@HomeActivity)
        newRecycleview.setHasFixedSize(true)
        newArrayList = arrayListOf<CarParser>()

        getUserData()

    }

    private fun getUserData() {
        for(i in headingArr.indices){

            val res = CarParser(headingArr[i], priceArr[i], fromArr[i], toArr[i])
            newArrayList.add(res)

            val adapter = MyAdapter(newArrayList)
            newRecycleview.adapter = adapter
        }
    }
}