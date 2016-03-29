------------	DAR HTTP Server --------------

--Authors :

	- CALDERON Juan Manuel
	- LARBI YOUCEF Mohamed Reda

--Prerequisites :

	- Java 1.8
	- Maven

--Features :

	Our software is a simple HTTP server that handles :
		- Requests parsing/handling and sending responses
		- Routing
		- Cookie-based sessions
		- HTML Templates
		
--Applications :

	We offer some applications to test the server. So we implemented :
		- EchoServer : A small application that returns any request received to the client in text/html/json according to the parameter "type" given by the client.
		- PointApp : Application that creates/stores/modifies some Points, to test the Routing feature
		- SignUp : A simple application which let you register and log in/log out, to test the sessions
		- HelloServer : A simple application that returns a text/json/html template with the values replaced, to test the templates
		- Sports-Men : A small application on which you can register with your favorite sport, and see other sportsmen (their username, sport and presence). If somebody log out, you will see it.
		
--How to use it :

	To use our server, you need to compile it with Maven. Once you are done, you can launch any application that contains an xml file, and a class with the same name (<Path to class>). To launch it, enter :
		java -jar target/DarHTTPServer-1.0-SNAPSHOT-jar-with-dependencies.jar <Port> <Path to class>

		<Path to class> must the whole path in it's package, i.e in the form com.upmc.dar.apps...

		Here are the path of our applications : 

			- EchoServer : com.upmc.dar.apps.EchoServer
			- PointApp : com.upmc.dar.apps.pointapp.PointApp
			- SignUp : com.upmc.dar.apps.signup.SignUp
			- HelloServer : com.upmc.dar.apps.HelloServer
			- Sports-Men : com.upmc.dar.apps.sports.Sports
			
		Also, we give some shell scripts for Linux users to launch one of these application with only one command.

Enjoy :)
