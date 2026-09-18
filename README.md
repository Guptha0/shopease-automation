# ShopEase Automation Framework

This project is a robust, modular Test Automation Framework built for the e-commerce application "ShopEase". It utilizes Java, Selenium WebDriver, TestNG, Maven, and follows the Page Object Model (POM) design pattern.

## Architecture & Tech Stack

*   **Language:** Java 17
*   **Core Engine:** Selenium WebDriver (v4.x)
*   **Test Runner:** TestNG
*   **Build Tool:** Maven
*   **Design Pattern:** Page Object Model (POM)
*   **Reporting:** ExtentReports (Configured via dependencies)
*   **Logging:** Log4j2
*   **Data Handling:** Apache POI (for Excel data-driven testing)

### Directory Structure Layering

*   `src/main/java/com/shopease/automation/base/`: Contains `BaseTest.java` for WebDriver initialization (ThreadLocal for parallel execution), configurations, and teardown logic (e.g., screenshot capture on failure). Note: TestNG dependency is compiled so that annotations can be used here.
*   `src/main/java/com/shopease/automation/pages/`: Contains Page Object classes where each web page is represented by a class containing WebElements (`@FindBy`) and action methods.
*   `src/main/java/com/shopease/automation/utils/`: Contains utility classes like `WaitUtils.java` to handle explicit waits efficiently.
*   `src/test/java/com/shopease/automation/tests/`: Contains the actual TestNG test classes (e.g., `LoginTests.java`).
*   `src/test/resources/`: Contains configuration files (`config.properties`, `testng.xml`).

## Prerequisites

1.  **Java Development Kit (JDK) 17** installed and `JAVA_HOME` configured.
2.  **Apache Maven** installed and added to your system `PATH`.
3.  An IDE like IntelliJ IDEA or Eclipse.
4.  Browsers (Chrome, Firefox, or Edge) installed on the testing machine.

## Setup Instructions

1.  **Clone or create the project directory:** Navigate to the project root containing `pom.xml`.
2.  **Download Dependencies:** Run the following Maven command to download all necessary libraries:
    ```bash
    mvn clean install -DskipTests
    ```
3.  **Configure Environment:** Update `src/test/resources/config.properties` with your test URL, preferred browser (`chrome`, `firefox`, or `edge`), and timeouts.

## Execution Instructions

You can execute the test suite using either Maven or TestNG directly.

### 1. Execute via Maven (Recommended)

Run the entire suite defined in `testng.xml` using the Maven Surefire plugin:

```bash
mvn clean test
```

### 2. Execute via IDE / TestNG

1.  Right-click on `src/test/resources/testng.xml` in your IDE.
2.  Select **Run 'testng.xml'**.

## Parallel Execution

The framework is configured for parallel test execution at the `<test>` level. You can modify this behavior in `src/test/resources/testng.xml`:

```xml
<suite name="ShopEase Automation Suite" parallel="tests" thread-count="4">
```
*   `parallel="tests"`: Runs different `<test>` tags concurrently. Change to `classes` or `methods` as needed.
*   `thread-count="4"`: Sets the maximum number of concurrent threads.

## Screenshot on Failure

The `BaseTest.java` class includes an `@AfterMethod` hook. If a TestNG test method fails, a screenshot is automatically captured and saved to the `screenshots/` directory at the project root.
