# ShopEase Test Automation Framework 🚀

A robust, modular, and highly scalable Test Automation Framework built for the **ShopEase** e-commerce application. 

This framework is engineered using industry best practices to ensure maintainability, reliability, and fast feedback loops for continuous integration.

## 🏗️ Architecture & Tech Stack

This project strictly adheres to the **Page Object Model (POM)** design pattern. By separating the web element locators and action methods from the test scripts, we achieve high reusability and significantly reduce maintenance overhead when UI changes occur.

*   **Language:** Java 17
*   **Core Engine:** Selenium WebDriver (v4.x)
*   **Test Runner:** TestNG
*   **Build Tool:** Apache Maven
*   **Design Pattern:** Page Object Model (POM) & Fluent API
*   **Reporting:** ExtentReports
*   **Logging:** Log4j2
*   **Data Handling:** Apache POI (Excel Data-Driven)

### Directory Structure Layering

*   📂 `src/main/java/.../base/`: Contains `BaseTest.java` for ThreadLocal WebDriver initialization, global configurations, and teardown logic (e.g., screenshot capture on failure).
*   📂 `src/main/java/.../pages/`: Contains Page Object classes (`RegistrationPage.java`, etc.). Each class encapsulates the WebElements (`@FindBy`) and actions for a specific UI page. Methods return `this` where applicable to support method chaining (Fluent interface).
*   📂 `src/main/java/.../utils/`: Contains utility classes like `WaitUtils.java` to handle explicit waits efficiently and prevent flaky tests.
*   📂 `src/test/java/.../tests/`: Contains the actual TestNG test classes (e.g., `RegistrationTests.java`).
*   📂 `src/test/resources/`: Contains configuration properties and `testng.xml` for suite execution.

---

## 🛠️ Prerequisites

Before you begin, ensure you have the following installed on your local machine:

1.  **Java Development Kit (JDK) 17**: Ensure `JAVA_HOME` is configured in your system environment variables.
2.  **Apache Maven**: Installed and added to your system `PATH`.
3.  **IDE**: IntelliJ IDEA, Eclipse, or VS Code (with Java Extension Pack).
4.  **Browsers**: Google Chrome, Mozilla Firefox, or Microsoft Edge installed on the testing machine. *(Note: Selenium v4.6+ automatically manages browser drivers using Selenium Manager).*

---

## 🚀 Setup Instructions

1.  **Navigate to the project root:**
    Ensure your terminal is inside the directory containing the `pom.xml` file.
    ```bash
    cd shopease
    ```

2.  **Download Dependencies:**
    Run the following Maven command to download all required libraries defined in the `pom.xml`:
    ```bash
    mvn clean install -DskipTests
    ```

3.  **Configure Environment (Optional):**
    If your framework uses a configuration file, verify `src/test/resources/config.properties` has the correct test URL and preferred browser configuration.

---

## 💻 Execution Instructions

You can execute the test suite using either Maven from the command line or directly through your IDE.

### 1. Execute via Maven (Recommended for CI/CD)

To run the entire suite defined in `testng.xml`, use the Maven Surefire plugin:

```bash
mvn clean test
```

### 2. Execute via IDE / TestNG

1.  Open `src/test/resources/testng.xml` in your IDE.
2.  Right-click anywhere inside the file and select **Run 'testng.xml'**.
3.  Alternatively, you can run individual test classes (like `RegistrationTests.java`) by clicking the **Run** button next to the class or method signature in your IDE.

---

## ⚡ Parallel Execution & Reliability

*   **Parallelism:** The framework is configured for parallel test execution. You can modify this behavior in `src/test/resources/testng.xml` by adjusting the `parallel="tests"` and `thread-count="4"` attributes.
*   **Explicit Waits:** Hardcoded `Thread.sleep()` calls are strictly avoided. The framework utilizes `WaitUtils` (WebDriverWait) to dynamically wait for element visibility and clickability, ensuring fast and flake-free execution.
*   **Screenshot on Failure:** The `BaseTest.java` class includes an `@AfterMethod` hook. If a TestNG test fails, a screenshot is automatically captured and saved to the `screenshots/` directory for easier debugging.
