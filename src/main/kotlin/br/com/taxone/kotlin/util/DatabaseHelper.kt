package br.com.taxone.kotlin.util

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.Statement
import java.util.ArrayList
import java.util.Arrays

import br.com.taxone.kotlin.dto.DSColumnDTO
import br.com.taxone.kotlin.dto.DSTableDTO
import br.com.taxone.kotlin.dto.DataSourceDTO
import br.com.taxone.kotlin.enums.ColumnType

public class DatabaseHelper {

	companion object {
		var STRING_COLUMN_NAMES = mutableListOf("varchar", "char", "clob")
		var NUMBER_COLUMN_NAMES = mutableListOf("int", "serial", "number", "long")
		var DATE_COLUMN_NAMES = mutableListOf("datetime", "date", "time", "timestamp")
	
		fun getTableMetadata(dsDTO: DataSourceDTO): List<DSColumnDTO>{
			var dsList = mutableListOf<DSColumnDTO>()
			var c: Connection? = null
			var s: Statement? = null
			var rs: ResultSet? = null
			var rsmd: ResultSetMetaData? = null
			try {
				c = DriverManager.getConnection(dsDTO.url, dsDTO.username, dsDTO.password)
				var tables = dsDTO.resourceNames?.split(",")?.toMutableList()
				if (tables != null){
					for (table in tables) {
						s = c.createStatement()
						println(">>>table" + table)
						rs = s.executeQuery("select * from " + table)
						rsmd = rs.getMetaData()
						for (x in 1..rsmd.getColumnCount()) {
							var dsCDTO = DSColumnDTO()
							dsCDTO.name = rsmd.getColumnName(x).toUpperCase()
							dsCDTO.columnType = getColumnType(rsmd.getColumnTypeName(x))
							dsCDTO.size = rsmd.getColumnDisplaySize(x)
							var dsTable = DSTableDTO()
							dsTable.name = table as String
							dsCDTO.dsTable = dsTable
							dsList.add(dsCDTO)
						}
						rs.close()
						s.close()
					}
				}
			} finally {
				if (rs != null) { try {rs.close()} catch (e: Exception) {}}
				if (s != null) { try {s.close()} catch (e: Exception) {}}
				if (c != null) { try {c.close()} catch (e: Exception) {}}
			}
			
			return dsList
		}
	
		fun getColumnType(columnTypeName: String): ColumnType  {
			if (STRING_COLUMN_NAMES.contains(columnTypeName.toLowerCase())) {
				return ColumnType.VARCHAR
			}else if (NUMBER_COLUMN_NAMES.contains(columnTypeName.toLowerCase())) {
				return ColumnType.NUMERIC
			}else if (DATE_COLUMN_NAMES.contains(columnTypeName.toLowerCase())) {
				return ColumnType.DATETIME
			}else {
				return ColumnType.VARCHAR
			}
		}
	
	}
}