package br.com.taxone.kotlin.dto

class ErrorResponse {
	var code : Int? = 3
	var message : String? = null
//	constructor (){}
	constructor (code : Int = 10, message : String = "Error in upload"){ 
		this.code = code
		this.message = message
	}

	constructor (code : Int = 5){
		this.code = code
		println("Third")
	}
	
}

