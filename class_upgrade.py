class Maker:
	def __init__(self, Model, Color):
		self.Model = Model
		self.Color = Color

	def mycar(self):
		self.python = "Hello World"
		return self.Model, self.Color

	def newfnc(self):
		print(self.python)


class UpdateMakerClass(Maker):
	def __init__(self, Model, Color, Gear, Milage):
		Maker.__init__(self, Model, Color)
		self.Trans = Gear
		self.Average = Milage

	def mycar(self):
		return self.Model, self.Color, self.Trans, self.Average

abcd = UpdateMakerClass("AUDI", "BLUE", 5, 20)
print(abcd.mycar())