package br.com.taxone.kotlin.repository


import org.springframework.data.jpa.repository.JpaRepository

import br.com.taxone.kotlin.entity.Email

interface EmailRepository : JpaRepository<Email, Int>{

//	List<Email> findByTypeIn(List<EmailType> et)

}
