HTTP REQUESTS

Edge cases have not been checked on for HTTP requests

Requests:
USE HTTP POST REQUESTS FOR ALL

	Users:

		Create a new user:	[server]:[port]/user/add?username=[username]&password=[password]
		Login:			[server]:[port]/user/login?username=[username]&password=[password]	(This request returns the userId)
		Change pass:		[server]:[port]/user/changepass?userId=[userId]&password=[password]

	Artboard: (only has add available right now)
	
		Create a new artboard:	[server]:[port]/artboard/add?userId=[userId]&artboardname=[artboardname]
		

WEBSOCKETS

the path will be /board/[boardID]	
the main board is mapped to /board/mainBoard

(you can get this boardID from the database, I can also modify the JSON object sent on login if nessisary to get all the boards each person is connected to)

I used a simple javascript function in order to test all of my websocket stuff, it is located at /server/src/main/resources/static/app.js 
