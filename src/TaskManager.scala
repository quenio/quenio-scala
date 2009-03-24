
class TaskManager {
	
	private val file = new TextFile("tasks.txt")

	def count = file.readLines.length
	
	def add(description: String) {
		file.append(writer => writer.write(description +"\n"))
	}
	
	def remove(index: Int): String = {
		val lines = file.readLines
		assert(
			index >= 0 && index <= lines.length, 
			"Index must be in bounds: " + (1,lines.length))
		file.create(writer => {
			lines.slice(0, index - 1).foreach(writer.write)
			lines.drop(index).foreach(writer.write)
		})
		return lines(index - 1)
	}
	
}
