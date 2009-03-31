package quenio.locale.repository

import java.io._
import scala.io._
import quenio.locale.model._

class LocaleRepository(dirPath: String) {
  
  private var map = Map.empty[String, Locale]
  private var dir = new File(dirPath)
  private var files = dir.listFiles
  
  require(files != null, "The specified path should point to directory: " + dirPath)
  
  for (file <- files) {
    val NameFormat = """custom_([a-z][a-z]_?[A-Z]?[A-Z]?)""".r
    val NameFormat(localeCode) = file.getName
    val lines = readLines(file.getPath).map(convert(_))
    map += (localeCode -> new Locale(localeCode, lines))
  }
  
  private def readLines(filePath: String): List[String] = {
    val reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"))
    try {
      readLines(reader, Nil)
    } finally {
      reader.close
    }
  }
  
  private def readLines(reader: BufferedReader, lines: List[String]): List[String] = {
    val line = reader.readLine
    if (line == null)
      lines
    else
      line :: readLines(reader, lines)
  }
  
  private def convert(raw: String): Line = {
    raw.trim match {
      case "" => Break
      case Comment(text) => new Comment(raw)
      case Property(name, value) => new Property(raw)
      case _ => new Comment("# " + raw)
    }
  }
	
	def apply(code: String): Locale = map(code)
	
}
