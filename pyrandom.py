import random
inp=input('please input the value')
data=['house','car','bench','laptop']
if inp in data:
	print('This is valid generate the code')
	a=random.randint(100000,999999)
else:
	print('Reset the Code')
print ('Please Enter the OTP =',a)
b=int(input('Please Enter the OTP shown above'))
if a==b:
	print('You are authorized')
else:
	print('Please Try Again')