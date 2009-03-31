package quenio.locale.model

class Locale(localeCode: String, allLines: List[Line]) {
  
  require(localeCode != null, "Locale requires 'localeCode' parameter.")
  require(allLines != null, "Locale requires 'allLines' parameter.")
  
  val code = localeCode
  
	val lines: List[Line] = allLines

  private val props = convertToMap(allLines.filter(isProperty))
  
  private def isProperty(line: Line) = line match {
    case Property(name, value) => true
    case _ => false
  }
  
  private def convertToMap(lines: List[Line]) = {
    var props = Map.empty[String, String]
    lines.foreach {
      _ match {
        case Property(name, value) => props += (name -> value)
      }
    }
    props
  } 
  
  def apply(propertyName: String): String = props(propertyName)

}




