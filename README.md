# Astro-API

Small Web Service to calculate planet position and some astrological calculations based on Thomas Mack port of of the Swiss Ephemeris library of AstroDienst Zürich to Java.

## Getting Started

The project is a web service build in on the Maven 3.3.9 endpoints-skeleton-archetype, is made to work on Google Appengine service an has no authentification. It was made as a reference/testing simple web service. it does provide a template to make astrological calculation based on two julian day dates of birth, the definition com.astro.astroAPI.definitinos.DefinitionTemaple.java file illustrate the basic concept of how the comparisons work.

### Prerequisites

You will need:

1. Appengine SDK v 1.9.46 or higher
2. Maven 3.3.9 or higher
3. Some kind of text processor to read/modify the code :P such as eclipse, notepad, visual studio, etc.

### Installing

To test locally this project you will need to clone the project locally, and after open a command prompt/console and navigate to the folder astroAPI and:

enter command: mvn appengine:devserver

This will run the service port 8080 and you can see it in http://localhost:8080/_ah/api/explorer

The testing values are:

1. category =        testcategory
2. julindayfirst =   2450029.958333
3. juliandaysecond = 2446374.105556

If all goes well you should recieve back an calculationValue answer with a meaningless Double (no valid astrological study is included, just a template).

## Deployment

To deploy this service on the cloud you would need to add some information:

On astroAPI\src\main\webapp\WEB-INF\appengine-web.xml, modify <application>your-app-id</application> to you app ID and <version>1</version> to the version you want to make.

After that, open a command prompt/console and navigate to the folder astroAPI and:

enter command: mvn appengine:update

The project should deploy.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [SDK for App Engine](https://cloud.google.com/appengine/downloads) - The SDK for App Engine includes a local development server as well as the tooling for deploying and managing your applications in App 

For more rich and clear/updated information please go to Google documentation:

[Backend API with Maven tutorial](https://cloud.google.com/appengine/docs/java/endpoints/helloendpoints-java-maven)

## Authors

* **Diego Pinto** - [Me](https://github.com/diegopintog)

## License

This project is licensed under the GLP-3.0 License.

## Acknowledgments

* [Thomas Mack](http://www.th-mack.de/international/download/) - for his awesome port of Swiss Ephemeris library of AstroDienst Zürich.
* [AstroDienst Zürich](http://www.astro.com) - for the Swiss Ephemeris library.
