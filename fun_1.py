c=90
f=76
def inp():
	a=int(input("Please enter first value"))
	b=int(input("Please enter second value"))
	return a+b

def take():
	a=int(input("Please enter first value"))
	b=int(input("Please enter second value"))
	return a-b
inp()
if c!=take():
	print('Done')