package quenio.locale.repository

import java.io.File
import scala.io.Source
import quenio.locale.model._

class LocaleRepository(dirPath: String) {
  
  private var map = Map.empty[String, Locale]
  private var dir = new File(dirPath)
  private var files = dir.listFiles
  
  require(files != null, "The specified path should point to directory: " + dirPath)
  
  for (file <- files) {
    val NameFormat = """custom_([a-z][a-z])""".r
    val NameFormat(localeCode) = file.getName
    val lines = Source.fromFile(file).getLines.toList.map(convert(_))
    map += (localeCode -> new Locale(localeCode, lines))
  }
  
  private def convert(raw: String): Line = {
    raw.trim match {
      case "" => Break
      case Comment(text) => new Comment(raw)
      case Property(name, value) => new Property(raw)
      case _ => throw new Exception("Unable to convert line: " + raw)
    }
  }
	
	def apply(code: String): Locale = map(code)
	
}
