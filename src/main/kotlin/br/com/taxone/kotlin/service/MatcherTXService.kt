package br.com.taxone.kotlin.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired
import br.com.taxone.kotlin.dto.SAFXColumnUpdateDTO
import br.com.taxone.kotlin.repository.SAFXTableRepository
import br.com.taxone.kotlin.repository.SAFXColumnRepository
import org.springframework.transaction.annotation.Transactional

@Service
open class MatcherTXService {

	@Autowired
	lateinit private var safxTableRepository: SAFXTableRepository
	
	@Autowired
	lateinit private var safxColumnRepository: SAFXColumnRepository
	

	@Transactional
	open fun updateSAFXColumns(safxColumns: MutableList<SAFXColumnUpdateDTO>) {
		for (safxColumn in safxColumns){
			safxColumnRepository.updateSAFXColumn(safxColumn.id as Int, safxColumn.dsColumnId)
		}
	}

	@Transactional
	open fun updateSAFXTable(id: Int, dsTableId: Int) {
		safxTableRepository.updateDSTable(id, dsTableId)
	}


}
