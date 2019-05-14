import smtplib

class MyEmailSend:
	def __init__(self):
		self.host = 'smtp.gmail.com'
		self.port = 587
		self.connection = smtplib.SMTP(self.host, self.port)
		self.connection.starttls()

	def authetication(self, username, password):
		self.username = username
		self.connection.login(username, password)

	def sendmailwithclass(self, fromto, message):
		self.connection.sendmail(self.username, fromto, message)

abcd = MyEmailSend()
abcd.authentication('robkingpython@gmail.com', 'python@123')
abcd.sendmailwithclass('daljit.in@gmail.com', "Hello World Test Email from Python Code")