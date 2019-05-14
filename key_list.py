val={'ACC':'[NAME,PIN,BAL]'}
print val
print(val.keys())

user=input('input')
print user
if user in val:
	print('its true')
else:
	print('its false')