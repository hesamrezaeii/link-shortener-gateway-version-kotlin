package com.example.urlshortenergateway.mongo.repo.extra

import com.example.urlshortenergateway.mongo.document.Url
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

interface UrlRepoExtra{
    fun findLimitedUrls(limit: Int):List<Url>
}