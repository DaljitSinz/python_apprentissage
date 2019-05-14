us_input=input('Enter 1 for Reg, 2 Login, Update Pass 3')

while us_input=='1':
	user=input('Please enter the username  :  ')
	passwd=input('Please enter the new password  :  ')
	data={}
	cond=input('confirm registration Y(yes)/N(no)/A(abort) :')

	if cond == 'Y':
		data[user]=passwd
		print('Registration Successfull')
	else:
		print('Hit Y to register or Press 1 to exit')
		print (data)
us_input=input('Enter 1 for Reg, 0 for exit')

us_input=input('Enter 1 for Reg, 2 Login, Update Pass 3')
if us_input=='2':
		login=input('Please enter the Username  :  ')
		if login in data:
			login_p=input('Please Enter Password  :  ')
			if login_p == data[user]:
				print('login done')
			else:
				print('Login failed')
			else:
				print('Please enter valid user name  :  ')

				print ('Please update Your password for security reasons')
if us_input=='3':
				up_name=input('Enter User name  :  ')
				if up_name in data:
					old_pass=input('please enter old password')
					if old_pass == data[up_name]:
						nu_pass=input('Enter new Password  : ')
					else:
						print('Please enter the correct password')
						nu_pass=input('Enter new Password  : ')
					else:
						print('enter the correct User Name')

						conf_pass=input('Please confirm password  :  ')
						if nu_pass == conf_pass:
							print('Your password has been updated')
						else:
							print('Your password Does not match')
