package quenio.locale.repository

import quenio.locale.model._

abstract class LocaleRepository {
  
  def apply(code: String): Locale
	def locales: List[Locale]
	def replaceWith(locales: List[Locale])
	
}