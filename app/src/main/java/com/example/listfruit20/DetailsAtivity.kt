package com.example.listfruit20

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.core.view.drawToBitmap
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
            binding.imageView2.setImageBitmap(fruit.Img)

        }
        val positionData = intent?.getIntExtra(MainActivity.MAIN_ACTIVITY_FRUIT_POSITION, -1)
        positionData?.let {
            position = positionData
        }
    }

    fun deleteFruit(view: View) {
     binding.btnRemove.setOnClickListener{


        val builder = AlertDialog.Builder(this)

        builder.setTitle("Deseja mesmo remover essa fruta")

        builder.setPositiveButton(getString(R.string.yes)) { dialog, id ->

              val retornar = Intent()
              retornar.putExtra(MainActivity.MAIN_ACTIVITY_FRUIT_POSITION,position)
              setResult(Activity.RESULT_OK, retornar)
              finish()
        }
        builder.setNegativeButton(getString(R.string.no)) { dialog, id ->

        }

        val alertDialog = builder.create()

        alertDialog.show()


     }
    }
}