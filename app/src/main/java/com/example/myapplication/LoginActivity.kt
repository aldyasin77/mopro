package com.example.myhelloworldandroid

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.ViewConfigurationCompat
import com.example.myapplication.HomeActivity
import com.example.myapplication.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val EmailAddress: EditText = findViewById (R.id.Email)
        val TextPassword: EditText = findViewById (R.id.Password)
        val main_button: Button = findViewById (R.id.btnLogin)

        main_button.setOnClickListener(View.OnClickListener {
            val i = Intent(this@LoginActivity, HomeActivity::class.java)
        })

    }
}
