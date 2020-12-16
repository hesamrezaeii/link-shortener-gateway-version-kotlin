package com.example.urlshortenergateway.controller.api

import com.example.urlshortenergateway.mongo.document.Url
import com.example.urlshortenergateway.mongo.repo.UrlRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors

@RestController
@RequestMapping("/api")
class ApiController(val urlRepo: UrlRepo) {
    @GetMapping("/ten-short-urls")
    fun shortUrl(): List<String> =
         urlRepo.findLimitedUrls(10).stream()
                .map(Url::shortUrl)
                .collect(Collectors.toList())
}
