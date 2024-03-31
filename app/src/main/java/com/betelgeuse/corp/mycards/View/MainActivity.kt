package com.betelgeuse.corp.mycards.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.betelgeuse.corp.mycards.Model.ApiClient
import com.betelgeuse.corp.mycards.Model.ApiService
import com.betelgeuse.corp.mycards.Model.Card
import com.betelgeuse.corp.mycards.R
import com.betelgeuse.corp.mycards.ViewModel.MyViewModel

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var cardAdapter: CardAdapter? = null
    private var cardList = mutableListOf<Card>()
    private lateinit var viewModel: MyViewModel
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiService = ApiClient.apiService

        cardList = ArrayList()

        recyclerView = findViewById<View>(R.id.cardListItem) as RecyclerView
        cardAdapter = CardAdapter(cardList, this@MainActivity)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 1)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = cardAdapter

        prepareCardListData()

        cardAdapter?.setOnEyeButtonClickListener(object : CardAdapter.OnEyeButtonClickListener {
            override fun onEyeButtonClick(position: Int) {
                Toast.makeText(this@MainActivity, "Вы нажали на кнопку 'eyeBTN'", Toast.LENGTH_SHORT).show()
            }
        })

        cardAdapter?.setOnTrashButtonClickListener(object : CardAdapter.OnTrashButtonClickListener {
            override fun onTrashButtonClick(position: Int) {
                Toast.makeText(this@MainActivity, "Вы нажали на кнопку 'trashBTN'", Toast.LENGTH_SHORT).show()
            }
        })

        cardAdapter?.setInfoButtonClickListener(object : CardAdapter.InfoClickListener {
            override fun onInfoButtonClick(position: Int) {
                Toast.makeText(this@MainActivity, "Вы нажали на кнопку 'Информация'", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        // Вызовите fetchData для использования
        viewModel.fetchData(context = this)
        fetchData()
    }

    private fun fetchData() {
        viewModel.fetchData(applicationContext)
    }

    private fun prepareCardListData(){
        var card = Card("64", "2" + "%","Базовый")
        cardList.add(card)

        card = Card("324", "34" + "%", "Элит")
        cardList.add(card)

        card = Card("324", "16" + "%", "Премиум")
        cardList.add(card)

        card = Card("3234", "17" + "%", "Базовый")
        cardList.add(card)

        card = Card("3244", "76" + "%", "Премиум")
        cardList.add(card)

        card = Card("12", "25" + "%", "Премиум")
        cardList.add(card)

        cardAdapter!!.notifyDataSetChanged()

        Handler(Looper.getMainLooper()).postDelayed({
            val loadItem = findViewById<RelativeLayout>(R.id.loadItem)
            loadItem.visibility = View.GONE
        }, 2000)
    }
}
