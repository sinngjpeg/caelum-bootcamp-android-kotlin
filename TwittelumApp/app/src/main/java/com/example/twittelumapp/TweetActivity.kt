package com.example.twittelumapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.example.twittelumapp.activities.ListaTweetsActivity
import com.example.twittelumapp.databinding.ActivityTweetBinding
import com.example.twittelumapp.db.TwittelumDatabase
import com.example.twittelumapp.modelo.Tweet
import com.example.twittelumapp.viewmodel.TweetViewModel
import com.example.twittelumapp.viewmodel.ViewModelFactory
import java.io.File

class TweetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTweetBinding

    private var localFoto: String? = null

    private val viewModel: TweetViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTweetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_tweet, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item?.itemId) {
            R.id.tweet_menu_tweetar -> {
                publicaTweet()
                finish()
            }

            R.id.menu_camera -> {

                tiraFoto()
                true
            }
            android.R.id.home -> finish()
        }

        return false
    }

    fun publicaTweet() {
        val campoDeMensagemDoTweet = findViewById<EditText>(R.id.tweet_mensagem)
        val mensagemDoTweet: String = campoDeMensagemDoTweet.text.toString()
        val tweet = Tweet(mensagemDoTweet)
        val tweetDao = TwittelumDatabase.getInstance(this).tweetDao()
        viewModel.salva(tweet)
        Toast.makeText(this, "$tweet foi salvo com sucesso", Toast.LENGTH_LONG).show()
        //finish()

    }


    private fun tiraFoto() {
        val abrirCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val caminhoFoto = defineLocalDaFoto()
        abrirCamera.putExtra(MediaStore.EXTRA_OUTPUT, caminhoFoto)
        startActivityForResult(abrirCamera, 123)
    }

    fun defineLocalDaFoto(): Uri? {
        localFoto =
            "${getExternalFilesDir(Environment.DIRECTORY_PICTURES)}/${System.currentTimeMillis()}.jpg"
        val arquivo = File(localFoto)
        return FileProvider.getUriForFile(this, "br.com.twittelumapp.fileprovider", arquivo)

    }


    private fun carregaFoto() {
        val bitmap = BitmapFactory.decodeFile(localFoto)
        val bm = Bitmap.createScaledBitmap(bitmap, 300, 300, true)
        binding.tweetFoto.setImageBitmap(bm)
        binding.tweetFoto.scaleType = ImageView.ScaleType.FIT_XY
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                carregaFoto()
            }
        }
    }

}