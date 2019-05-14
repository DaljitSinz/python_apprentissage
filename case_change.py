a='BharAT'
new=[]
for b in a:
	if ord(b)>=65 and ord(b)<=85:
		result=ord(b)+32 ###65-90 and 97-122
		new.append(chr(result))
	elif ord(b)>=97 and ord(b)<=122:
		result=ord(b)-32
		new.append(chr(result))
print(''.join(new))
