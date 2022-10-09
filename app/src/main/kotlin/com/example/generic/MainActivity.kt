package com.example.generic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.generic.databinding.MainActivityBinding

class MainActivity: AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}