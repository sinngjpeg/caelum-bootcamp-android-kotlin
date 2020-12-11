package com.example.twittelumapp.activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.twittelumapp.R
import com.example.twittelumapp.TweetActivity
import com.example.twittelumapp.databinding.ActivityListaTweetsBinding
import com.example.twittelumapp.modelo.Tweet
import com.example.twittelumapp.viewmodel.TweetViewModel
import com.example.twittelumapp.viewmodel.ViewModelFactory

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

        binding.listaTweet.setOnItemClickListener { adapter, item, posicao, id ->
            val tweet = binding.listaTweet.getItemAtPosition(posicao) as Tweet
           // viewModel.deleta(tweet)
            perguntaSePrecisaDeletar(tweet)
        }

        viewModel.lista().observe(this, observer())

        binding.fabNovo.setOnClickListener {
            val intencao = Intent(this, TweetActivity::class.java)
            startActivity(intencao)

        }
    }

    private fun perguntaSePrecisaDeletar(tweet: Tweet) {
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setIcon(R.drawable.ic_alert)
        dialogBuilder.setTitle("Atenção")
        dialogBuilder.setMessage("Deseja mesmo apagar esse tweet?")
        dialogBuilder.setNegativeButton("Não", null)
        DialogInterface.OnClickListener() { _: DialogInterface, _: Int ->
            viewModel.deleta(tweet)
        }
        dialogBuilder.setPositiveButton("Sim") { _: DialogInterface, _: Int ->
            viewModel.deleta(
                tweet
            )
        }
        dialogBuilder.show()
    }

    private fun observer(): Observer<List<Tweet>> {
        return Observer {
            binding.listaTweet.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, it)
        }
    }

}


