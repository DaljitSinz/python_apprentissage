dic={123456:(1111,"JACK",20)}
print(dic)

key=int(input("Please enter the Key   :  "))
if key in dic.keys():
	print("Key is valid")
	PIN=int(input("Please enter Pin   :  "))
	if PIN == dic[key][0]:
		print("Its a valid PIN")
		NAME=input("Please Enter Name")
		if NAME == dic[key][1]:
			print("Its a Valid Name")
			AGE=int(input("Please enter your AGE   :  "))
			if AGE == dic[key][2]:
				print("You are a Valid user")
				print("You are Welcome")
			else:
				print("Invalid Age")
		else:
			print("Invalid Name")
	else:
		print("Invalid Pin")
else:
	print ("please try valid key")