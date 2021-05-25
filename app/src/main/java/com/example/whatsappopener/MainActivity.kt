package com.example.whatsappopener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         var Number: String = "0"
        if(intent.action == Intent.ACTION_PROCESS_TEXT){
            Number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }
        if(Number.isDigitsOnly()){
            whatsAppOpen(Number)
        }else{
            Toast.makeText(this, "Please input Valid Number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun whatsAppOpen(number: String) {
           val intent = Intent(Intent.ACTION_VIEW)
           intent.setPackage("com.whatsapp")
           val data: String = if(number[0] == '+'){
               number.substring(1)
           }else if(number.length == 10){
               "91" + number
           }else{
               number
           }
           intent.data = Uri.parse("https://wa.me/$data")
          if(packageManager.resolveActivity(intent, 0) == null){
              startActivity(intent)
          }else{
              Toast.makeText(this, "Please install Whatsapp", Toast.LENGTH_SHORT).show()

          }

    }
}