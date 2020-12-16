package com.example.urlshortenergateway.mongo.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "Url")
data class Url(val originalUrl: String, val shortUrl: String, val creationDate: Date) {
    @Id
    private lateinit var id: String
}