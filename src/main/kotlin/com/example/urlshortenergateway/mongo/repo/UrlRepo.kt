package com.example.urlshortenergateway.mongo.repo

import com.example.urlshortenergateway.mongo.document.Url
import com.example.urlshortenergateway.mongo.repo.extra.UrlRepoExtra
import org.springframework.data.mongodb.repository.MongoRepository


interface UrlRepo: MongoRepository<Url,String> , UrlRepoExtra {
    fun findByShortUrl(shortUrl:String):Url?
    fun findByOriginalUrl(originalUrl:String):Url?
}