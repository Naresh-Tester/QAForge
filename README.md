# QAForge - Selenium Automation Framework

## Overview

QAForge is a Selenium WebDriver automation framework developed using Java.

The framework is designed to automate web application testing with focus on:

- Maintainability
- Reusability
- Scalability
- Easy test execution
- Detailed reporting


## Technology Stack

| Technology | Purpose |
|------------|---------|
| Java | Programming Language |
| Selenium WebDriver | Web Automation |
| TestNG | Test Execution Framework |
| Maven | Dependency Management |
| Allure Report | Test Reporting |
| Log4j2 | Logging Framework |
| IntelliJ IDEA | Development Environment |


## Framework Architecture

QAFORGE follows the Page Object Model (POM) design pattern.

```
Test Classes
      |
      |
Page Objects
      |
      |
Action Driver
      |
      |
Selenium WebDriver
      |
      |
Web Browser
```


## Project Structure

```
QAFORGE

src/main/java/com.orangehrm

├── actiondriver
│      └── ActionDriver.java
│          # Reusable Selenium actions
│
├── base
│      └── BaseClass.java
│          # Browser initialization and teardown
│
├── listeners
│      └── TestListener.java
│          # Test execution monitoring
│
├── pages
│      ├── LoginPage.java
│      └── DashboardPage.java
│          # Page object classes
│
└── utilities
       ├── ConfigReader.java
       ├── LoggerUtil.java
       └── ScreenshotUtil.java
           # Common reusable utilities


src/test/java/com.orangehrm.test

├── LoginPageTest.java
└── DashboardPageTest.java


src/main/resources

├── config.properties
└── log4j2.xml


src/test/resources

└── testng.xml
```


## Framework Features

### Page Object Model (POM)

Each application page is maintained as a separate Java class containing:

- Web locators
- Page actions
- Business methods


Benefits:

- Easy maintenance
- Reusable methods
- Reduced code duplication


### Action Driver

ActionDriver contains reusable Selenium operations.

Implemented actions:

- Click element
- Enter text
- Get text
- Wait handling
- Dropdown handling
- Mouse actions


Benefits:

- Centralized Selenium operations
- Improved readability
- Better maintainability


### Configuration Management

Application configuration is maintained in:

```
src/main/resources/config.properties
```

Example:

```
browser=chrome
url=https://opensource-demo.orangehrmlive.com
username=Admin
password=admin123
```


### Logging

QAForge uses Log4j2 for execution logging.

Log file location:

```
logs/automation.log
```

Logs provide:

- Execution details
- Debug information
- Error tracking
- Test execution flow


### Test Execution

Test cases are developed using TestNG.

Test location:

```
src/test/java/com.orangehrm.test
```

Example:

```
LoginPageTest.java
DashboardPageTest.java
```


## Running Tests

### Using Maven

Execute all test cases:

```
mvn clean test
```


### Using TestNG XML

Run:

```
src/test/resources/testng.xml
```


## Allure Reporting

QAFORGE uses Allure Report for generating test execution reports.

After test execution:

```
allure serve allure-results
```

Allure report provides:

- Test execution status
- Passed and failed test details
- Screenshots
- Execution history
- Failure information


## Screenshot Capture

Screenshots are captured automatically during test failures.

Screenshots are attached to Allure reports for easier debugging.


## Browser Support

Currently supported:

- Google Chrome


Future support:

- Microsoft Edge
- Firefox


## Future Enhancements

Planned improvements:

- Parallel test execution
- Retry mechanism
- Data driven testing
- API automation
- Database testing
- Jenkins CI/CD integration
- Docker execution


## Author

Naresh K

QA Automation Engineer