package com.example.xuegaoshop

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Shouquan : AppCompatActivity() {
    private var button3: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shouquan)
        button3 = findViewById(R.id.button3)
        button3?.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                this@Shouquan,
                "验证码：123456",
                Toast.LENGTH_SHORT
            ).show()
        })
    }
}