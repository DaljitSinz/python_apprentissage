import random
ACC={}
DATA={}
ZACK_BANK=input('Please enter the Choice of ZACK BANK :\n1. Account opening.\n2. Balance Query.\n3. Withdrawal.\n4. Deposit.\n5. Pin Change.\n6. Exit')
while ZACK_BANK=='1':                    											###Account Opening
	A_NAME=input('Please Enter your Full name  :  ')
	A_DEP=int(input('Please Start your Account with Minimum Balance of 10,000  : ')) ##Validation
	A_BAL=A_DEP
	if A_DEP >= 10000:
		print('Your Money has been deposited, THANKS FOR YOUR INTEREST !!!')
		A_PIN=int(input('Please create 4 Digit Pin Code  :  ')) 					##Validation
		if A_PIN<=9999:
			print('Your password is saved, Keep it SECRET for further log-In')
			A_ACC=random.randint(1000000000,9999999999)
			DATA[str(A_ACC)]=[A_NAME,A_DEP,A_PIN]
			print (DATA)
			print (A_ACC)
		else:
			print('Please enter the valid 4 Digit Pin')	
	else :
		('Please Deposit more than 10,000')
	ZACK_BANK=input('Please enter the Choice of ZACK BANK :\n1. New Account opening.\n2. For Main Menu\n3. Exit\n' )
	if ZACK_BANK=='2':
		ZACK_BANK=input('Please enter the Choice of ZACK BANK :\n1. Account opening.\n2. Balance Query.\n3. Withdrawal.\n4. Deposit.\n5. Pin Change.\n6. Exit\n')

ZACK_BANK=input('Please enter the Choice of ZACK BANK :\n1. Account opening.\n2. Balance Query.\n3. Withdrawal.\n4. Deposit.\n5. Pin Change.\n6. Exit\n') 
while ZACK_BANK=='2':               ###Balance Check
	B_ACC=input('Please enter the Account Number  :  ') 
	if B_ACC in DATA.keys():
		B_PIN=input('For Verification, Please enter the PIN Code  : ')
		if B_ACC==DATA[B_ACC][2]:
			print('The Account Balance is : )
		else:
			print('PleaseEnter the valid Pin ') 
	else:
		print('Please enter the Correct Account Number')
	ZACK_BANK=input('Please enter the Choice of ZACK BANK :\n1. New Account opening.\n2. For Main Menu\n' )

ZACK_BANK=input('Please enter the Choice of ZACK BANK :\n1. Account opening.\n2. Balance Query.\n3. Withdrawal.\n4. Deposit.\n5. Pin Change.') 
while ZACK_BANK=='3':               ###Balance Check
	B_ACC=input('Please enter the accont number  : ')
	B_PIN=input('Enter the 4 digit Pin code for AuthenticatV_PINion  :  ')
	if V_ACC in ACC:
	print('kk')
	BAL=DEP
	print('BAL')
				else:
					print('Please enter the Valid Account Number and Password.')

ZACK_BANK=input('Please enter the Choice of ZACK BANK :\n1. Account opening.\n2. Balance Query.\n3. Withdrawal.\n4. Deposit.\n5. Pin Change.') 
while ZACK_BANK=='4':               ###Deposit
	D_ACC=input('Please enter the accont number  : ')
	D_PIN=input('Enter the 4 digit Pin code for Authentication  :  ')
					if D_ACC in ACC:
						print('kk')
					else:
						print('Please enter the Valid Account Number and Password.')

ZACK_BANK=input('Please enter the Choice of ZACK BANK :\n1. Account opening.\n2. Balance Query.\n3. Withdrawal.\n4. Deposit.\n5. Pin Change.') 
while ZACK_BANK=='5':               ###Pin Change
	P_ACC=input('Please enter the accont number  : ')
	if VPACC in ACC:
		P_PIN=input('Enter the OLD Pin code for Authentication  : ')
		N_PIN=input('Enter the New 4 digit Pin code  :  ')
		C_PIN=input('Confirm 4 digit Pin code  :  ')
		if N_PIN==C_PIN:
			DATA.append()
			print('PIN has been updated') 
		else:
			print('Confirm Pin Do not match, please try again')

	else :
		print('')

ZACK_BANK=input('Please enter the Choice of ZACK BANK :\n1. Account opening.\n2. Balance Query.\n3. Withdrawal.\n4. Deposit.\n5. Pin Change.\n6. Exit.') 
while ZACK_BANK=='6':               ###Pin Change
	exit

