#!/user/bin/python3

#>>> import sys
#>>> dir(sys)

from sys import argv
print(argv)
a=int(argv[1])
b=int(argv[2])
print(a+b)


fh=open("code.jpg",'rb')
data=fh.read()
fh.close()
fh1=open("jag.jpg",'wb')
fh1.write(data)
fh1.close()


### Read file and save file to location
### Read multiple files and save on one Location