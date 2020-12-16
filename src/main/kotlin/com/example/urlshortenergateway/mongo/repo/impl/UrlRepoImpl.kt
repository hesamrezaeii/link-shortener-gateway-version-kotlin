package com.example.urlshortenergateway.mongo.repo.impl

import com.example.urlshortenergateway.mongo.document.Url
import com.example.urlshortenergateway.mongo.repo.extra.UrlRepoExtra
import jdk.nashorn.internal.objects.annotations.Property
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

class UrlRepoImpl(private val mongoOperations: MongoOperations) : UrlRepoExtra{

    override fun findLimitedUrls(limit: Int): List<Url> {
        val query = Query().limit(limit)
                .with(Sort.by(Sort.Order
                        .desc(Url::creationDate.toString())));
        return mongoOperations.find(query, Url::class.java)
    }


}


