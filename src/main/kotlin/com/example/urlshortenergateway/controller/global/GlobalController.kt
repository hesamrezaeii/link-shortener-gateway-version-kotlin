package com.example.urlshortenergateway.controller.global

import com.example.urlshortenergateway.kafka.messageType.UrlClickMessage
import com.example.urlshortenergateway.kafka.producer.Producer
import com.example.urlshortenergateway.manager.UrlManager
import com.example.urlshortenergateway.mongo.document.Url
import com.example.urlshortenergateway.mongo.repo.UrlRepo
import com.example.urlshortenergateway.request.MakeShortLinkRequest
import com.example.urlshortenergateway.response.MakeShortUrlResponse
import org.springframework.web.bind.annotation.*
import java.io.IOException
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/public")
class GlobalController(val urlRepo: UrlRepo, val producer: Producer, val urlManager: UrlManager) {
    @GetMapping("/all")
    fun all(): List<Url> = urlRepo.findAll()

    @GetMapping("/click/{url}")
    fun click(httpRequest: HttpServletRequest, httpResponse: HttpServletResponse, @PathVariable url: String) {
        var exist = urlRepo.findByShortUrl(url)
        if (exist == null) {
            // return 404
            return
        }
        val message = UrlClickMessage(
                exist.shortUrl, exist.originalUrl,
                Calendar.getInstance().time,
                httpRequest.remoteAddr,
                httpRequest.getHeader("User-Agent")
        )
        producer.sendMessage(message)
        try {
            httpResponse.sendRedirect(exist.originalUrl)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    @PostMapping("/make")
    fun makeShortUrl(@RequestBody request: MakeShortLinkRequest): MakeShortUrlResponse {
        val url = urlManager.getOrMakeUrl(request)
        return MakeShortUrlResponse(url.shortUrl)
    }

    @DeleteMapping("/all")
    fun deleteAll():String {
        urlRepo.deleteAll()
        return "cleared"
    }

}