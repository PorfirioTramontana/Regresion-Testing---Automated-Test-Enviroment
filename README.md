# Regresion Testing - Automated Test Enviroment
[![Actions Status](https://github.com/PabloGarciaFernandez/Regresion-Testing---Automated-Test-Enviroment/actions/workflows/ci.yml/badge.svg)](https://github.com/PabloGarciaFernandez/Regresion-Testing---Automated-Test-Enviroment/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=PabloGarciaFernandez_Regresion-Testing---Automated-Test-Enviroment&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=PabloGarciaFernandez_Regresion-Testing---Automated-Test-Enviroment)
[![codecov](https://codecov.io/gh/PabloGarciaFernandez/Regresion-Testing---Automated-Test-Enviroment/branch/master/graph/badge.svg?token=O1JH67E97U)](https://codecov.io/gh/PabloGarciaFernandez/Regresion-Testing---Automated-Test-Enviroment)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FUO276824%2FRegresion-Testing---Automated-Test-Enviroment.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2FUO276824%2FRegresion-Testing---Automated-Test-Enviroment?ref=badge_shield)


## Description

Testing Augmentation technique which, starting from tests recorded with Selenium, generates sets of possible input values which, applied to those tests, generate new test cases. It is a technique that transforms simple test cases into data-driven tests for which the test data is stored in JSON files.


## Technologies used

<p float="left">
<a href="https://www.oracle.com/java/"><img src="https://img.icons8.com/external-tal-revivo-shadow-tal-revivo/512/external-java-is-a-general-purpose-programming-language-that-is-class-based-logo-shadow-tal-revivo.png" height="50"></a>
<a href="https://www.selenium.dev/"><img src="https://img.icons8.com/fluency/512/selenium-test-automation.png" height="50"></a>
<a href="https://maven.apache.org/"><img src="https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/apache-maven-50.png" height="50"></a>
</p>


## Requirements

1º Need to install Firefox Browser in your own computer to execute the tests in local.

2º In the JSON test files use always fullXpath for the element you want to select.


## How to run the tests locally

After executing the following command a detailed test report in csv format is generated.

```bash
mvn -B test
```

If you want to see the GeckDriver window in action you need to comment this lines in DynamicGenerator.java

```bash
options.addArguments("--no-sandbox");
options.addArguments("--disable-dev-shm-usage");
options.addArguments("--headless");
```

