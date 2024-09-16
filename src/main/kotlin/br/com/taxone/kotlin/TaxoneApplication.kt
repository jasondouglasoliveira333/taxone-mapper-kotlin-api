package br.com.taxone.kotlin

import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

import br.com.taxone.kotlin.dto.Customer


@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
open public class TaxoneApplication {
}


fun main(args: Array<String>){
	println("OUR other test")
	var c = Customer(1, "WE")
	println(c.id.toString() + " - " + c.name)

	runApplication<TaxoneApplication>(*args)
}