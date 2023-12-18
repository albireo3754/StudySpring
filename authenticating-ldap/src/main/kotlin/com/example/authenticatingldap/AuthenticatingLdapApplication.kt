package com.example.authenticatingldap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthenticatingLdapApplication

fun main(args: Array<String>) {
	runApplication<AuthenticatingLdapApplication>(*args)
}
