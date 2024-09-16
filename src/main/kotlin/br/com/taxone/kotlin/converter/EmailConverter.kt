package br.com.taxone.kotlin.converter;

import br.com.taxone.kotlin.dto.EmailDTO;
import br.com.taxone.kotlin.entity.Email;

class EmailConverter {

	companion object {
		public fun convert(e : Email) : EmailDTO  {
			var eDTO = EmailDTO()
			eDTO.id = e.id
			eDTO.email = e.email
			eDTO.type = e.type
			return eDTO;
		}
		public fun convert(eDTO : EmailDTO ) : Email  {
			var e = Email()
			e.id = eDTO.id
			e.email = eDTO.email
			e.type = eDTO.type
			return e;
		}
	}
}
