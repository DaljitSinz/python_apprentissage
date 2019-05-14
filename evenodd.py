a=int(input('Enter the Start Number'))
b=int(input('Enther the Last Number'))

even=[]
odd=[]

while a<b:
	if a%4==0:
		even.append(a)
		a+=1

	elif a%3==0:
		odd.append(a)
		a+=1
	else:
		pass

print("the range is completed")
print('Even numbers are :',even)
print('ODD numbers are :',odd)




