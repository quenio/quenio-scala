package quenio.locale.model

class Line(rawText: String) {
  
  require(rawText != null, "The 'rawText' parameter is required.")
  
  def raw = rawText
  
}

object Line {
  
  def unapply(line: Line): Option[String] = Some(line.raw)
  
}