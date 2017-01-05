# Multi-Threaded File Server
###Object-Oriented Programming (2016)

the project implements a multi-threaded file server and logging application that allows a client application to download files using a set of options presented in a terminal user interface. 
###Overview
a. client-side provides 4 options to users:

1. Connect to Server 

2. Print File Listing 

3. Download File

4. Quit

b. before user choose 2&3, the client should be connected the server.

c. once a client download a file, server should record.

###How to run 

1. before run it, make sure the jdk/jre environment installed correctly.

2. you have two way to run this project:

a. use two different version Eclipses run two Runner.java files in client & server folder respectivily.In order to connect, the server's ip should be known in advance.
Pay attention, you need input two parameters before execute the server-side's Runner.(One is the port, another is the relative or absolute path to the directory
containing the set of files to download.

b. download the folder[oop_Project], and access in that folder.open two cmd editor. Client-side input: 
```bash
java -cp oop.jar ie.gmit.sw.client.Runner
```
Server-side input:
```bash
java -cp oop.jar ie.gmit.sw.server.Runner 7777 ./files/
```
here "7777" is the default port, "./files/" is the path of files you wnat to get from the server.
