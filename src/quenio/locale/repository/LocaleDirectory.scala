package quenio.locale.repository

import java.io._
import scala.collection.mutable._
import scala.io._
import quenio.locale.model._

class LocaleDirectory(path: String) extends LocaleRepository {
  
  private val map = Map.empty[String, Locale]
  private val dir = new File(path)
  private val files = dir.listFiles
  
  require(files != null, "The specified path should point to directory: " + path)
  
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
	
	def locales = map.values.toList
	
	def replaceWith(newLocales: List[Locale]) {
	  newLocales.foreach(locale => map(locale.code) = locale)
	  locales.foreach(writeLines(dir.getPath, _))
	}
	
	private def writeLines(dirPath: String, locale: Locale) {
	  val filePath = dirPath + "/custom_" + locale.code 
    val writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF8"))
    try {
      writeLines(writer, locale.lines)
    } finally {
      writer.close
    }
	}

  private def writeLines(writer: BufferedWriter, lines: List[Line]) {
    lines match {
      case Nil => return
      case Line(raw) :: remaining => {
        writer.write(raw, 0, raw.length)
        writer.newLine
        writeLines(writer, remaining)
      }
    }
  }

}

