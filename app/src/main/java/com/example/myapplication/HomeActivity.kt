package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myhelloworldandroid.ItemAdapter
import com.example.myhelloworldandroid.ListItem
import com.example.myhelloworldandroid.NetworkConfig
import com.example.myhelloworldandroid.ResultWeather
import kotlinx.android.synthetic.main.activity_home.*
import okhttp3.*
import java.io.IOException


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // run ( "https://api.openweathermap.org/data/2.5/forecast?q=purwakarta,ID&appid=2ce659b9c25fc6fe3a07de4ca71d1dac")

        NetworkConfig().getService().getWeathers("purwakarta,ID", "2ce659b9c25fc6fe3a07de4ca71d1dac").enqueue(object : retrofit2.Callback<ResultWeather> {

            override fun onResponse(
                    call: retrofit2.Call<ResultWeather>,
                    response: retrofit2.Response<ResultWeather>)

            {
                val item = response.body()
                Log.d("response", response.body().toString())

                txtKota.text = item?.city?.name
                txtTanggal.text = item?.list?.get(0)?.dt?.let { Util.getDayName(it.toLong())}
                text_temp_max.text = item?.list?.get(0)?.main?.temp?.let { Util.setFormatTemperature(it) }
                text_temp_min.text = item?.list?.get(0)?.main?.tempMin?.let { Util.setFormatTemperature(it) } + " - " + item?.list?.get(0)?.main?.tempMax?.let { Util.setFormatTemperature(it) }
                text_desc.text = item?.list?.get(0)?.weather?.get(0)?.description.toString()
                item?.list?.get(0)?.weather?.get(0)?.id?.let { Util.getArtResourceForWeatherCondition(it) }?.let { image_desc.setImageResource(it) }

                var list = item?.list
                var itemAdap = ItemAdapter(list as List<ListItem>)

                recycle_view_container.apply {
                    layoutManager = LinearLayoutManager(this@HomeActivity)
                    adapter = itemAdap }

                fun onFailure(call: retrofit2.Call<ResultWeather>, t: Throwable) {
                    Log.d("error response service", t.message.toString())

                    Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
                }

            }

            override fun onFailure(call: retrofit2.Call<ResultWeather>, t: Throwable) {
                Log.d("error response service", t.message.toString())

                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
            }
        })}
    fun run(url: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) =
                    println(response.body()?.string())
        })
    }
}