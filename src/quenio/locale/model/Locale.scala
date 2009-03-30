package quenio.locale.model

class Locale(localeCode: String, allLines: List[Line]) {
  
  require(localeCode != null, "Locale requires 'localeCode' parameter.")
  require(allLines != null, "Locale requires 'allLines' parameter.")
  
  val code = localeCode
  
	val lines: List[Line] = allLines

}




