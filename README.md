##Hummingbird 
An open source, lightweight and standards-compliant mission control system for small spacecraft.

You are at the root of the Hummingbird project.

In order to build Hummingbird, please install Apache Maven first (refer to http://maven.apache.org/).
Run: "mvn install" in the src sub-directory of this repository. All Hummingbird artifacts will be installed in your local Maven repository.

If you're only interested in deploying and running Hummingbird, please follow the INSTALL.txt guide.


For further information, please refer to one of the following pages: 

* [Github repository](https://github.com/JohannesKlug/hbird)
* [Wiki](https://github.com/JohannesKlug/hbird/wiki)
* [Facebook](http://www.facebook.com/pages/Hummingbird-Mission-Control-System/156087881111212)

### Installation provisos

####Karaf and Maven
Maven 3.1.0 is incompatible with the karaf-maven-plugin:3.0.0-SNAPSHOT so you must use Maven 3.0.5 or lower for now.

[See here for details in Karaf JIRA](https://issues.apache.org/jira/browse/KARAF-2395)

####M2E in Eclipse

There are no m2e connectors for a few plugins we use...

	+ Castor
	+ Karaf
	+ Enunciate

They don't affect the Eclipse project so add them to Lifecycle mapping ignore in eclipse.

If you are some issues resolving the connectors with the m2e catalog try installing JDK 7u45 [See here for details](https://bugs.eclipse.org/bugs/show_bug.cgi?id=417241)
This worked perfectly for me by the way. Check which java your eclipse is starting with (Help > About eclipse > Installation details) to make sure it's using 7u45
