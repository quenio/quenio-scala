package quenio.locale.model

class Locale(allLines: List[Line]) {
  
  require(allLines != null, "Locale requires 'allLines' parameter.")
  
	def lines: List[Line] = allLines

}




