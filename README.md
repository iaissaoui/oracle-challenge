# oracle-challenge
Oracle Challenge

Java / Spring Boot Notebook Server

Java / Spring Boot Notebook Server
The task of this assignment is to set up a simple notebook server that can execute pieces of code in
an interpreter using Spring Boot technology.
Background:
Interactive notebooks are experiencing a rise in popularity. Notebooks offer an environment for Data
scientists to comfortably share research, collaborate with others and explore and visualize data. The
data usually comes from executable code that can be written in the client (e.g. Python, SQL) and is sent
to the server for execution. Popular notebook technologies which this approach are Apache Zeppelin
(https://zeppelin.apache.org/) and Jupyter Notebooks (http://jupyter.org/).
Tasks:
1. Set up a Spring Boot environment with Spring Boot 1.5.x. See:
https://spring.io/guides/gs/spring-boot/. Feel free to add dependencies to the project as needed.
2. Task: Create an `/execute` endpoint that accepts a JSON object such as:
{
“code”: “%python print 1+1”
}
The endpoint should parse this input and compute what the output of the python program is.
The code is formatted like this:
%<interpreter-name><whitespace><code>
To do this, execute python code by starting a python subprocess and capturing the output.
The returned output should be:
{
“result”: “2”
}
Make sure that basic python programs can be executed in this way.
3. Challenge 1: Variables and state. If a user uses a variable in a piece of code, it needs to be
accessible on subsequent executions. For example. The following requests are send:
{
“code”: “%python a = 1”
}
This should return:
{
“result”: “”}
Then a second piece of code is sent, which uses a result from the previous request. This means
the state of the Python interpreter has to be preserved:
{
“code”: “%python print a+1”
}
This should return:
{
“result”: “2”
}
4. Challenge 2: Sessions. The application needs to be used by multiple users at the same time, so
we need to be able to differentiate them from information in the request. To do this, an extra
field can be added to the request: sessionId. Extend the functionality of the /execute method
such that requests with the same sessionId can access the same variables, but requests with a
different sessionId don’t have this access.
Notes:
Consider the following:
What should happen if the piece of code cannot be parsed?
What should happen if the type of interpreter is not known?
What should happen if the interpreter takes a long time to finish?
The user’s code can have side effects, so make sure that it is not executed multiple times.
What should happen if the python process encounters some kind of error?
How to reliably test an application like this? Think about unit and integration testing.
The following aspects will contribute to your solution:
Correct implementation
Solving of the challenges
Testability
Clean code
Separation of concerns
"Please do not send any source code via e-mail. You should create a Git repository and
provide the necessary information for us to access it."
Good luck!
