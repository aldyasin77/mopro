package com.example.myhelloworldandroid

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Util
import kotlinx.android.synthetic.main.activity_home.view.*
import kotlinx.android.synthetic.main.item_view.view.*

class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val dateTime = itemView.textView
    private val description = itemView.textView2
    private val tempNow = itemView.textView3
    private val tempMinMax = itemView.textView4
    private val imageIcon = itemView.imageView

    fun bindItem(item: ListItem) {
        dateTime.text = item.dt?.let { Util.getDayName(it.toLong())}
        description.text = item.weather?.get(0)?.description
        tempNow.text = item.main?.temp?.let { Util.setFormatTemperature(it) }
        tempMinMax.text = item?.main?.tempMin?.let { Util.setFormatTemperature(it) } + " - " + item.main?.tempMax?.let { Util.setFormatTemperature(it) }
        item?.weather?.get(0)?.id?.let { Util.getArtResourceForWeatherCondition(it) }?.let { imageIcon.setImageResource(it) }
        val weatherId = item.weather?.get(0)?.id
        weatherId?.let { Util.getArtResourceForWeatherCondition(it) }?.let { imageIcon.setImageResource(it) }
    }
}