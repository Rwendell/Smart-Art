Edge cases have not been checked on for HTTP requests

Requests:
USE HTTP POST REQUESTS FOR ALL

	Users:

		Create a new user:	[server]:[port]/user/add?username=[username]&password=[password]
		Login:			[server]:[port]/user/login?username=[username]&password=[password]	(This request returns the userId)
		Change pass:		[server]:[port]/user/changepass?userId=[userId]&password=[password]

	Artboard: (only has add available right now)
	
		Create a new artboard:	[server]:[port]/artboard/add?userId=[userId]&artboardname=[artboardname]
		


