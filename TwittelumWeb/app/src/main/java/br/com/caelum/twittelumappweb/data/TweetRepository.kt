package br.com.caelum.twittelumappweb.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.caelum.twittelumappweb.modelo.TweetComUsuario
import br.com.caelum.twittelumappweb.webservices.TweetWebClient

class TweetRepository(private val client: TweetWebClient) {

    private val lista: MutableLiveData<List<TweetComUsuario>> = MutableLiveData()

    val excecao: MutableLiveData<Throwable> = MutableLiveData()
    val tweetCriado: MutableLiveData<TweetComUsuario> = MutableLiveData()

    fun salva(tweet: TweetComUsuario) = client.insere(tweet, sucesso(), erro())
    private fun erro() = { erro: Throwable ->
        excecao.value = erro

    }

    fun buscaLista() = client.buscaTweets(sucessoParaLista(), erro())
    private fun sucessoParaLista() = { tweets: List<TweetComUsuario> ->
        lista.postValue(tweets)
    }

    private fun sucesso() = { tweet: TweetComUsuario ->
        tweetCriado.value = tweet

    }

    fun pegaLista(): LiveData<List<TweetComUsuario>> = lista
}