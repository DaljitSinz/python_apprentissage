data={'aman':['singh','2345'],'raman':'jot'}
print(data)
print(data.keys())
print(data['aman'])
print(data['aman'][1])

user=input('Please enter the Username')
if user in data:
	print (data[user])
	passwd=input('Please Enter Password')
	if passwd in data[user]:
		print('login done')
		print (data[user][1])
	else:
		print('Login failed')
else:
	print('Please enter valid user name')