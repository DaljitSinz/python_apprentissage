class Maker:
	def __init__(self, Model, Color):
		self.Model = Model
		self.Color = Color

	def mycar(self):
		self.python = "Hello World"
		return self.Model, self.Color

	def newfnc(self):
		print(self.python)

abc=Maker("BMW", "Black")
XYZ=Maker("Maruti", "Red")
print(XYZ.mycar())
XYZ.newfnc()