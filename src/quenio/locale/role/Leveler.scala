package quenio.locale.role

import quenio.locale.model._
import quenio.locale.repository._

trait Leveler extends LocaleRepository {
  
  override abstract def apply(code: String): Locale
	override abstract def locales: List[Locale]
	override abstract def replaceWith(locales: List[Locale])
  
  def level(baseLocaleCode: String) {
    val base = super.apply(baseLocaleCode)
    val leveled = Leveler.level(base, super.locales - base)
    super.replaceWith(leveled)
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