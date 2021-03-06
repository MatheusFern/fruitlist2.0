package com.example.listfruit20

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.listfruit20.databinding.FruitItemBinding
import com.example.listfruit20.model.Fruit
import java.util.*

class ListAdapter (private val fruits:MutableList<Fruit>,
                   private val callback: (Fruit, Int) -> Unit)
    : RecyclerView.Adapter<ListAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent,false)
       val binding = FruitItemBinding.bind(view)

       val vh = VH(binding)

        vh.itemView.setOnClickListener {
            val fruit = fruits[vh.adapterPosition]
            callback( fruit, vh.adapterPosition)
        }
       return vh
    }



    override fun onBindViewHolder(holder: VH, position: Int) {
        val fruit = fruits[position]
        holder.setValues(fruit.title, fruit.description, fruit.Img)
    }

    override fun getItemCount() = fruits.size

    fun swap(initPosition: Int, targetPosition: Int) {
        Collections.swap(fruits, initPosition, targetPosition)
        notifyItemMoved(initPosition, targetPosition)
    }

    fun remove(position: Int) {
        fruits.removeAt(position)
        notifyDataSetChanged()

    }

    class VH(itemView: FruitItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val title = itemView.FruitItemTitle
        private val description = itemView.FruitItemDescription
        private val Image = itemView.imageView

        fun setValues(title:String, description: String, fruit: Bitmap){
            this.title.text = title
            this.description.text = description
            this.Image.setImageBitmap(fruit)
        }
    }
}