package com.example.twittelumapp.activities

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.twittelumapp.R
import com.example.twittelumapp.databinding.ActivityListaTweetsBinding
import com.google.android.material.snackbar.Snackbar

class ListaTweetsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tweets)

        val binding = ActivityListaTweetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tweets: List<String> = listOf(
            "tweet 1", "tweet 2", "tweet 3", "tweet 4", "tweet 5", "tweet 6"
        )

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tweets)
        binding.listaTweet.adapter = adapter

        binding.fabNovo.setOnClickListener {
            Snackbar.make(it, "Clicou no botao", Snackbar.LENGTH_LONG)
                .setAction("Desfazer") {}.show()
        }
    }
}