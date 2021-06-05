package com.example.listfruit20.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fruit(val title:String, val description:String, val Img: Bitmap) : Parcelable