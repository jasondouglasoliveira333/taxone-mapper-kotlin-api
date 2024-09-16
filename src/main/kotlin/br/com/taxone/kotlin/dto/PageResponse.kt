package br.com.taxone.kotlin.dto

import  kotlin.collections.MutableList

public class PageResponse<T> {
	var totalPages : Int = 0 
	var content : MutableList<T>? = null
	
}
