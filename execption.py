a=int(input('Enter Number'))
b=int(input('Enter Number'))

try:
	print(a/b)

except ZeroDivisionError:
	print('Can not divided by Zero')
	print('Error Handled')

except ValueError:
	print('Can not integer by Zero')
	print('Error Handled')

except TypeError:
	print('Type Error 33 Handled')

except:
	print('Type Error Handled')

print('end of Program')
