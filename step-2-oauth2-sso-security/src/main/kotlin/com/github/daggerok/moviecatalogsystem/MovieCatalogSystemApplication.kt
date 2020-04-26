package com.github.daggerok.moviecatalogsystem

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MyRestResource {
  @GetMapping fun index() = mapOf("result" to "Hello, World")
}

@EnableOAuth2Sso
@SpringBootApplication
class MovieCatalogSystemApplication

fun main(args: Array<String>) {
  runApplication<MovieCatalogSystemApplication>(*args)
}
