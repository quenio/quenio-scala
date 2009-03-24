import java.io._;
import scala.io._;

class TextFile(fileName: String) {

	def append(update: BufferedWriter => Unit) {
		write(update, true)
	}

	def create(update: BufferedWriter => Unit) {
		write(update, false)
	}

	def write(update: BufferedWriter => Unit, append: Boolean) {
		val writer = new BufferedWriter(new FileWriter(fileName, append))
		update(writer)
		writer.close
	}

	def readLines =	Source.fromFile(fileName).getLines.toList 

}
