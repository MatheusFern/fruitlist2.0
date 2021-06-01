package com.example.listfruit20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listfruit20.databinding.ActivityMainBinding
import com.example.listfruit20.model.Fruit
import java.text.FieldPosition

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



    private fun preList() {
        fruits = mutableListOf()
        for (i in 1..5 ) {
            var imagepreview = when (i % 3) {
                0 -> R.drawable.apple
                1 -> R.drawable.banana
                else -> R.drawable.carot
            }
            val fruit = Fruit("Fruit$i", "lorem ipsum", imagepreview)
            fruits.add(fruit)
        }
    }

    private fun setupRecyclerView() {
        binding.FruitList.adapter = mListAdapter
        val layoutManager = LinearLayoutManager(this)
        binding.FruitList.layoutManager = layoutManager
    }

    fun setupButton(view: View) {
        binding.floatingActionButton.setOnClickListener{
            val intent = Intent(this, AddFruitActivity::class.java)
            startActivityForResult(intent, MAIN_ACTIVITY_REQUEST_CODE)
        }
    }
}