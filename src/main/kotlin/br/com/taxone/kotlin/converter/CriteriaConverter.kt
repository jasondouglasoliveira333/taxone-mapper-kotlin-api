package br.com.taxone.kotlin.converter

import br.com.taxone.kotlin.dto.CriteriaDTO
import br.com.taxone.kotlin.dto.SAFXColumnDTO
import br.com.taxone.kotlin.entity.Criteria
import br.com.taxone.kotlin.entity.SAFXColumn

public class CriteriaConverter {

	companion object {
		fun convert(c: Criteria): CriteriaDTO {
			var cDTO = CriteriaDTO()
			cDTO.id = c.id
			cDTO.safxColumn = SAFXColumnConverter.convertCriteria(c.safxColumn as SAFXColumn)
			cDTO.operator = c.operator
			cDTO.value = c.value
			cDTO.additionalValue = c.additionalValue
			return cDTO
		}
		
		fun convert(cDTO: CriteriaDTO): Criteria {
			var c = Criteria()
			c.id = cDTO.id
			c.safxColumn = SAFXColumnConverter.convert(cDTO.safxColumn as SAFXColumnDTO)
			c.operator = cDTO.operator
			c.value = cDTO.value
			c.additionalValue = cDTO.additionalValue
			return c
		}
	}
}