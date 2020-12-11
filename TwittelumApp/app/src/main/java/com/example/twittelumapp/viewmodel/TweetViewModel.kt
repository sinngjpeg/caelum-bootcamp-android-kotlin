package com.example.twittelumapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.twittelumapp.db.TweetDao
import com.example.twittelumapp.db.TweetRepository
import com.example.twittelumapp.modelo.Tweet

class TweetViewModel(private val repository: TweetRepository) : ViewModel() {
    fun lista() = repository.lista()
    fun salva(tweet: Tweet) = repository.salva(tweet)
    fun deleta(tweet: Tweet) = repository.deleta(tweet)
}