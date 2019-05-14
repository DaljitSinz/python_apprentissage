#!/usr/bin/python3

class Bank: 
    def __init__(ide,name,passwd,balance):
        ide.name=name
        ide.passwd=passwd
        ide.balance=balance
    def show(ide):
        print(ide.name)
        print(ide.passwd)
        print(ide.balance)

a100=Bank('yasvindra',123,2000)
a101=Bank('daljeet',111,3000)
a102=Bank('jagrati',222,4000)

a102.show()


