package com.example.twittelumapp.db

import com.example.twittelumapp.modelo.Tweet

class TweetRepository(private val fonteDeDados: TweetDao) {
    fun lista() = fonteDeDados.lista()
    fun salva(tweet: Tweet) = fonteDeDados.salva(tweet)
}