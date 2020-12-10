package com.example.twittelumapp.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.twittelumapp.R
import com.example.twittelumapp.TweetActivity
import com.example.twittelumapp.databinding.ActivityListaTweetsBinding
import com.example.twittelumapp.db.TwittelumDatabase
import com.example.twittelumapp.modelo.Tweet
import com.example.twittelumapp.viewmodel.TweetViewModel
import com.example.twittelumapp.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class ListaTweetsActivity : AppCompatActivity() {

    private val viewModel: TweetViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory).get(TweetViewModel::class.java)
    }

    private lateinit var binding: ActivityListaTweetsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tweets)

        binding = ActivityListaTweetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.lista().observe(this, observer())

        binding.fabNovo.setOnClickListener {
            val intencao = Intent(this, TweetActivity::class.java)
            startActivity(intencao)
        }
    }


    private fun observer(): Observer<List<Tweet>> {
        return Observer {
            binding.listaTweet.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, it)
        }
    }


}