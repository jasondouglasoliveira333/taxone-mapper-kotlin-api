package br.com.taxone.kotlin

import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

import br.com.taxone.kotlin.dto.Customer
import br.com.taxone.kotlin.dto.ErrorResponse
import java.nio.file.Files
import kotlin.io.NoSuchFileException
import java.io.File
import br.com.taxone.kotlin.dto.CustomerJava


@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
open public class TaxoneApplication {
}


fun main(args: Array<String>){
	println("OUR other test")
	runApplication<TaxoneApplication>(*args)
}	
