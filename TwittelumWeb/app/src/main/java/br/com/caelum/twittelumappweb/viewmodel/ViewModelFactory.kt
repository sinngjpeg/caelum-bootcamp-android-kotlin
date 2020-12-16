package br.com.caelum.twittelumappweb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.data.UsuarioRepository

object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    private val tweetRepository = TweetRepository()
    private val usuarioRepository = UsuarioRepository()
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        TweetViewModel::class.java -> {
            TweetViewModel(tweetRepository) as T
        }
        else -> {
            UsuarioViewModel(usuarioRepository) as T
        }
    }
}