package com.example.listfruit20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listfruit20.databinding.ActivityDetailsAtivityBinding
import com.example.listfruit20.databinding.ActivityMainBinding
import com.example.listfruit20.model.Fruit

class DetailsAtivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsAtivityBinding
    private var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsAtivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fruit = intent?.getParcelableExtra<Fruit>(MainActivity.MAIN_ACTIVITY_FRUIT_EXTRA)
        fruit?.let {
            binding.addNoteTitle.setText(fruit.title)
            binding.addNoteDescription.setText(fruit.description)
            binding.imageView2.setImageResource(fruit.Img)

        }
        val positionData = intent?.getIntExtra(MainActivity.MAIN_ACTIVITY_FRUIT_POSITION, -1)
        positionData?.let {
            position = positionData
        }
    }
}