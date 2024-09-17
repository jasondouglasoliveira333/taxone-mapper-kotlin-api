package br.com.taxone.kotlin.util

class StringUtil {

  companion object{
    fun putPercent(name: String?): String? {
      if (name != null) {
        return "%" + name + "%";
      }
      return null as? String
    }
  }

}
