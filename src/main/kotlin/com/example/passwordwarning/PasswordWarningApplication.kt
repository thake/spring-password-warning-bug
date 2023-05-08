package com.example.passwordwarning

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.security.config.web.servlet.invoke

@SpringBootApplication
class PasswordWarningApplication

fun main(args: Array<String>) {
	runApplication<PasswordWarningApplication>(*args)
}


@EnableWebSecurity
class SecurityConfiguration {
	@Bean
	fun securityChain(http: HttpSecurity) : SecurityFilterChain {
		http {
			authorizeRequests {
				authorize(anyRequest, authenticated)
			}
			oauth2ResourceServer {
				jwt {
					jwkSetUri = "https://idp.example.com/.well-known/jwks.json"
				}
			}
		}
		return http.build()
	}
}

@Component
@RestController("/hello-world")
class MyController {
	@GetMapping
	fun sayHello() = "Hello World"
}
