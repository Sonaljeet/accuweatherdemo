# Validation of Weather information from different sources and compare the values
## Cucumber-BDD-Framework

### Tools/Application required to run at your system:
* JDK-16 (recommended) - [Link to download](https://www.oracle.com/java/technologies/javase/jdk16-archive-downloads.html)
* IDE - Any Java IDE
* Apache-Maven-3.8.3 - [Link to download](https://maven.apache.org/download.cgi)
* GitBash - [Link to download](https://git-scm.com/downloads)
* Browser - Chrome/MS Edge

### Steps to run at your system:
* Clone the repository using "git clone".
* Open the project as maven project in your installed IDE.
* Create a JUnit configuration with environment variable as *-Dbrowser=chrome* or *-Dbrowser=edge* based on the browser of your choice.
* Run the configuration.
* Alternatively, you can run it using maven command as "mvn clean install -Dbrowser=chrome".

### Directory structure:
![ImageLink](https://github.com/Sonaljeet/image/blob/main/folderStructure.png)

### Snapshot of Extent Report:
![ImageLink](https://github.com/Sonaljeet/image/blob/main/extentReport1.png)

![ImageLink](https://github.com/Sonaljeet/image/blob/main/extentReport2.png)

### Handle blocking of unwanted Ads in your browser:
#### Steps to block unwanted ad during execution:
* Uncomment the commented line in *DriverBase.java* file.
  ![ImageLink](https://github.com/Sonaljeet/image/blob/main/driverBase.png)
