package br.com.taxone.kotlin

import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

import br.com.taxone.kotlin.dto.Customer
import br.com.taxone.kotlin.dto.ErrorResponse


@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
open public class TaxoneApplication {
}


fun main(args: Array<String>){
	println("OUR other test")
//	var c = ErrorResponse(1, "WE")
//	println(c.code.toString() + " - " + c.message)
//	c = ErrorResponse(33)//, "WE"
//	println(c.code.toString() + " - " + c.message)
//	c = ErrorResponse()//, "WE" //33
//	println(c.code.toString() + " - " + c.message)

	runApplication<TaxoneApplication>(*args)
}