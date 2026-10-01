# ShopEase E-Commerce Test Automation Framework

This repository contains the Week 3 test automation framework for the ShopEase e-commerce platform. It transitions from basic procedural test scripts to a highly modular, maintainable, and extensible framework built with Java, Selenium WebDriver, TestNG, and Maven.

## Framework Architecture & Layers

The framework implements a multi-layered design prioritizing the separation of concerns:

- **Base Layer (`com.shopease.automation.base`)**: 
  Contains `BaseTest.java`, which abstracts WebDriver setup and teardown. It uses `ThreadLocal` to ensure thread-safety for parallel test execution.
- **Pages Layer (`com.shopease.automation.pages`)**: 
  Implements the Page Object Model (POM) design pattern. UI elements and interactions are isolated into respective page classes, ensuring tests are highly readable and easy to maintain when UI changes occur.
- **Tests Layer (`com.shopease.automation.tests`)**: 
  Contains the TestNG test classes (e.g., `LoginTests.java`). These classes purely orchestrate the business flows utilizing the Page objects, entirely free of low-level element finding logic.
- **Utils Layer (`com.shopease.automation.utils`)**: 
  Contains reusable helpers such as `ConfigReader.java` (reading environment properties) and `ScreenshotUtil.java` (for capturing evidence on failure).
- **Listeners Layer (`com.shopease.automation.listeners`)**: 
  Houses the TestNG `ExtentReportListener.java` to automatically generate rich HTML execution reports and attach screenshots without polluting the test classes.
- **Test Data & Config (`src/test/resources`)**: 
  Externalized configurations (`config.properties`) and TestNG suite execution rules (`testng.xml`).

## Key Design Decisions

1. **Page Object Model (POM):** Prevents code duplication and enhances maintainability.
2. **ThreadLocal WebDriver:** Facilitates robust parallel testing by assigning a separate WebDriver instance to each execution thread.
3. **Listener Pattern:** Decouples reporting and screenshot capture logic from the test flows, making the code cleaner and strictly adhering to the Single Responsibility Principle.

## Prerequisites

- **Java Development Kit (JDK):** 17 or higher
- **Maven:** 3.8+ for dependency management
- **Browser:** Google Chrome (managed seamlessly by Selenium 4.6+ Manager)

## Setup and Execution

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Guptha0/shopease-automation.git
   cd shopease-automation
   ```

2. **Execute Tests via Maven Command Line:**
   To run the entire suite as defined in the `testng.xml` file (parallel execution):
   ```bash
   mvn clean test
   ```

3. **View Reports:**
   After execution, the Extent HTML Report will be generated at:
   `reports/ExtentReport.html`

## Continuous Integration (CI/CD)

This project integrates with **GitHub Actions** to automate test execution on every `push` and `pull_request` to the `main` branch. This ensures that new changes do not break existing functionality.

### How the CI Pipeline Operates

1. **Trigger**: The pipeline is triggered automatically on pushes or pull requests to `main`.
2. **Environment Setup**: It runs on an `ubuntu-latest` runner and sets up JDK 17 (Temurin distribution). Maven dependencies are cached to speed up subsequent runs.
3. **Execution**: Tests are executed using the Maven command: `mvn clean test -Dbrowser=chrome -Dheadless=true`.
4. **Artifact Archiving**: Regardless of test success or failure, execution reports (like Extent HTML reports) and screenshots are uploaded as build artifacts.

### Headless Execution

To ensure tests run smoothly in a server environment without a GUI, the `DriverFactory` has been configured to check for the `headless=true` system property or the `CI=true` environment variable. 
When triggered, it injects the following ChromeOptions:
- `--headless=new`: Uses the modern headless Chrome architecture.
- `--disable-gpu`, `--no-sandbox`, `--disable-dev-shm-usage`: Standard stability arguments for Linux-based CI environments.
- `--window-size=1920,1080`: Ensures responsive elements load correctly as they would on a standard desktop monitor.

### Configuring GitHub Secrets (Optional)

If your application under test requires sensitive information (like API keys or staging passwords), you can configure them in GitHub Secrets:
1. Go to your repository settings on GitHub.
2. Navigate to **Secrets and variables > Actions**.
3. Add a **New repository secret** (e.g., `STAGING_PASSWORD`).
4. Update the `.github/workflows/regression.yml` to pass these secrets as environment variables:
   ```yaml
   env:
     STAGING_PASSWORD: ${{ secrets.STAGING_PASSWORD }}
   ```

### Downloading Test Reports

Post-build, you can download the detailed execution reports directly from GitHub Actions:
1. Navigate to the **Actions** tab in your GitHub repository.
2. Click on the latest workflow run.
3. Scroll down to the **Artifacts** section at the bottom of the summary page.
4. Download the `test-execution-reports` artifact to view the HTML reports and failure screenshots locally.
