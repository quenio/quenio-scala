package quenio.locale.model

class Line(rawText: String) {
  
  require(rawText != null, "The 'rawText' parameter is required.")
  
  val raw = rawText
 
  override def toString = raw
}

object Line {
  
  def unapply(line: Line): Option[String] = Some(line.raw)
  
}