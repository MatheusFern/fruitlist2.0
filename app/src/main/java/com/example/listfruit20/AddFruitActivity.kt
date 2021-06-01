package com.example.listfruit20

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.listfruit20.databinding.ActivityAddFruitBinding
import com.example.listfruit20.model.Fruit
import java.util.jar.Manifest

class AddFruitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddFruitBinding
    private var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddFruitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fruit = intent?.getParcelableExtra<Fruit>(MainActivity.MAIN_ACTIVITY_FRUIT_EXTRA)
        fruit?.let {
                binding.addFruitTitle.setText(fruit.title)
                binding.addFruitDescription.setText(fruit.description)
                binding.ImgPreviewFruit.setImageResource(fruit.Img)
        }
        val positionData = intent?.getIntExtra(MainActivity.MAIN_ACTIVITY_FRUIT_POSITION, -1)
        positionData?.let {
            position = positionData
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_fruit, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun onClickListener(view: View) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123 ) {
            var bitmap = data?.extras?.get("data") as Bitmap
            binding.ImgPreviewFruit.setImageURI(data.data)
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId){
//        R.id.menu_add_fruit -> {
//
//            val title = binding.addFruitTitle.text.toString()
//            val description = binding.addFruitDescription.text.toString()
//            val image = binding.ImgPreviewFruit.setImageResource()
//
//            val fruit = Fruit(title, description,)
//            val returnIntent = Intent (this, MainActivity::class.java)
//            returnIntent.putExtra(MainActivity.MAIN_ACTIVITY_FRUIT_EXTRA, fruit)
//            returnIntent.putExtra(MainActivity.MAIN_ACTIVITY_FRUIT_POSITION, position)
//            setResult(Activity.RESULT_OK,returnIntent)
//            finish()
//            true
//        } else ->{
//            super.onOptionsItemSelected(item)
//        }
//
//    }
}