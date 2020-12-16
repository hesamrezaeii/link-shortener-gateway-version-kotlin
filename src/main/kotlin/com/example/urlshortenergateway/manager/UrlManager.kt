package com.example.urlshortenergateway.manager

import com.example.urlshortenergateway.mongo.document.Url
import com.example.urlshortenergateway.mongo.repo.UrlRepo
import com.example.urlshortenergateway.request.MakeShortLinkRequest
import org.springframework.stereotype.Component
import java.util.*

@Component
class UrlManager(val urlRepo: UrlRepo) {
    fun getOrMakeUrl(request: MakeShortLinkRequest):Url{
        var toReturn = urlRepo.findByOriginalUrl(request.url)
        if(toReturn == null) {
            val random = (Math.random() * 89999 + 10000).toInt()
            val currentDate = Calendar.getInstance().time
            toReturn = Url(request.url,""+random,currentDate)
        }
        return urlRepo.save(toReturn)
    }
}