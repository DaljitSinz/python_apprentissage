a=raw_input('Try Your Luck')
data=[]
while True:
	if a=='Jack':
		print('You are Lucky')
		data.append(a)
		print data
		break
	else:
		data.append(a)
		a=raw_input('Try your Luck Again')
