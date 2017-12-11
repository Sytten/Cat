# Cat

**We worked with SVN and moved the project here**

## Description
Third semester project (Computer Engineering Sherbrooke) by team CHAT.

The goal of the projet was to create a solution for cat home caring. 
The interface allow you to give food and water to your cat.
You can also see it and talk to it!

![Control Center Image](/ControlCenter.png "Control Center")

## Code
Contributors:
- [Sytten](https://github.com/Sytten)
- [Larochelle000](https://github.com/Larochelle000)
And others on the team.

The project is separated in 4 sub-projects.
- CatController : That one is to be packaged in a jar and run on a Raspberry Pi (on it python 2 must installed and the script to run motors must be in the same folder). You must specify the IP of the server.
- CatServer : That one is to run a computer with Apache Tomcat 8 installed and configured.
- CatStatistics : Usefull to parse csv from Taiga (Scrum manager) with custom fields.
- CatVideo : NodeJS projet that must run on the Raspberry Pi too. Uses the camera of the PI to stream video to webpage on the server.

To make it work you need the following dependencies (sorry no maven):
- commons-file-upload1.3.2, commons-io-2.5, commons-logging-1.2, gson2.3.1, json-20140107, junit-4.12, mockito-all-1.10.19, spring-core-3.2.5, spring-test-3.2.5, spring-web-3.2.5 in CatServer/WebContent/WEB-INF/lib
- json20140107, mockito-all-1.10.19 in CatController/Libs
- OpenCV3.8 in the root of CatStatistics

## Construction
![Construction Side](/Construction1.png "Construction Side")

![Construction Behind](/Construction2.png "Construction Behind")

## License
The code is released under MIT license.
