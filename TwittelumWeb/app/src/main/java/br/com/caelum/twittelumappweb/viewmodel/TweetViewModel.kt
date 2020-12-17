package br.com.caelum.twittelumappweb.viewmodel

import androidx.lifecycle.ViewModel
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.modelo.toTweetComUsuario

class TweetViewModel(
        private val repository: TweetRepository,
        private val usuarioRepository: UsuarioRepository
        ) : ViewModel() {


    fun salva(tweet: Tweet)  {
        val usuario = usuarioRepository.getUsuario().value!!
        repository.salva(tweet.toTweetComUsuario(usuario))
    }

    fun falha() = repository.excecao
    fun novoTweet() = repository.tweetCriado

    fun tweets(): List<Tweet> = listOf(
            Tweet("bla", null),
            Tweet("ble", null),
            Tweet("bli", null),
            Tweet("blo", null),
            Tweet("blu", null),
            Tweet("blao", null))

    fun filtraTweetsPelo(texto: String): List<Tweet> {
        val tweets = tweets()
        return tweets.filter { tweet -> tweet.mensagem.contains(texto, true) }
    }

}