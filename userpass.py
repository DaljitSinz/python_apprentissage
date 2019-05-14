data={'aman':'singh','raman':'jot'}
print(data)
print(data.keys())
print(data['aman'])

user=input('Please enter the Username')
if user in data:
	passwd=input('Please Enter Password')
	if passwd == data[user]:
		print('login done')
	else:
		print('Login failed')
else:
	print('Please enter valid user name')
print(vars())