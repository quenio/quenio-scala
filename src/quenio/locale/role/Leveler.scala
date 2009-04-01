package quenio.locale.role

import quenio.locale.model._
import quenio.locale.repository._

trait Leveler extends LocaleRepository {
  
  def level(baseLocaleCode: String) {
    val base = apply(baseLocaleCode)
    val leveled = Leveler.level(base, locales - base)
    replaceWith(leveled)
  }

}

object Leveler {
  
  def level(base: Locale, locales: List[Locale]): List[Locale] = 
    locales.map(locale => new Locale(locale.code, base.lines.map(convert(locale, _))))
  
  private def convert(locale: Locale, line: Line) = line match {
    
    case Property(name, value) => {
      var newVal = value
      try {
        newVal = locale(name)
      } catch {
        case e: NoSuchElementException => newVal = "(" + locale.code + ") " + newVal 
      }
      new Property(name + "=" + newVal)
    }
    
    case _ => line
    
  }
  
}