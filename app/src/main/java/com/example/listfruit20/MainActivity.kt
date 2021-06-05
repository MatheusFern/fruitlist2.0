package com.example.listfruit20

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listfruit20.databinding.ActivityMainBinding
import com.example.listfruit20.helper.FruitItemTouchHelperCallback
import com.example.listfruit20.model.Fruit

class MainActivity : AppCompatActivity() {

    private lateinit var fruits:MutableList<Fruit>

    private val mListAdapter by lazy { ListAdapter(fruits, this::onNoteClickListener) }


    private lateinit var binding: ActivityMainBinding

    companion object {
        const val  MAIN_ACTIVITY_REQUEST_CODE = 1
        const val  MAIN_ACTIVITY_FRUIT_EXTRA = "fruit_extra"
        const val  MAIN_ACTIVITY_FRUIT_POSITION = "fruit_position_extra"
        const val  MAIN_ACTIVITY_REQUEST_CODE_EDIT = 2

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        preList()
        setupRecyclerView()
    }

    private fun onNoteClickListener(fruit: Fruit,position: Int) {
        val intent = Intent(this, DetailsAtivity::class.java)
        intent.putExtra(MAIN_ACTIVITY_FRUIT_EXTRA, fruit)
        intent.putExtra(MAIN_ACTIVITY_FRUIT_POSITION, position)
        startActivityForResult(intent, MAIN_ACTIVITY_REQUEST_CODE_EDIT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if ( requestCode == MAIN_ACTIVITY_REQUEST_CODE) {
                val fruit = data?.getParcelableExtra<Fruit>(MAIN_ACTIVITY_FRUIT_EXTRA)
                fruit?.let { fruits.add(it)  }
                mListAdapter.notifyDataSetChanged()
            }
            if (requestCode == MAIN_ACTIVITY_REQUEST_CODE_EDIT) {
               val fruit = data?.getParcelableExtra<Fruit>(MAIN_ACTIVITY_FRUIT_POSITION)
               val position = data?.getIntExtra(MAIN_ACTIVITY_FRUIT_POSITION, -1)
                fruits.removeAt(position!!)

                mListAdapter.notifyDataSetChanged()

            }

        }
    }

    private fun preList() {
        fruits = mutableListOf()
        for (i in 1..5 ) {
            var icon: Bitmap = BitmapFactory.decodeResource(resources,R.drawable.apple)
            val fruit = Fruit("Fruit$i", "lorem ipsum", icon)
            fruits.add(fruit)
        }
    }

    private fun setupRecyclerView() {
        binding.FruitList.adapter = mListAdapter
        val layoutManager = LinearLayoutManager(this)
        binding.FruitList.layoutManager = layoutManager

        val itemTouchCallback = FruitItemTouchHelperCallback(mListAdapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.FruitList)
    }

    fun setupButton(view: View) {
        binding.floatingActionButton.setOnClickListener{
            val intent = Intent(this, AddFruitActivity::class.java)
            startActivityForResult(intent, MAIN_ACTIVITY_REQUEST_CODE)
        }
    }
}