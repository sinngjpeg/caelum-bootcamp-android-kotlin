package br.com.caelum.twittelumappweb.data

import androidx.lifecycle.MutableLiveData
import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.modelo.TweetComUsuario
import br.com.caelum.twittelumappweb.webservices.TweetWebClient

class TweetRepository(private val client: TweetWebClient) {

    val excecao: MutableLiveData<Throwable> = MutableLiveData()
    val tweetCriado: MutableLiveData<TweetComUsuario> = MutableLiveData()

    fun salva(tweet: TweetComUsuario) = client.insere(tweet, sucesso(), erro())
    private fun erro() = { erro: Throwable ->
        excecao.value = erro

    }

    private fun sucesso() = { tweet: TweetComUsuario ->
        tweetCriado.value = tweet

    }
}