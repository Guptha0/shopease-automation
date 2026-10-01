# ShopEase Automation Framework

This is a robust, production-grade Test Automation Framework built for an e-commerce platform using **Java, Selenium WebDriver, and TestNG**. It leverages the **Page Object Model (POM)** design pattern for maintainability and is fully integrated with a **Continuous Integration (CI/CD)** pipeline using GitHub Actions.

## 🚀 Key Features

*   **Page Object Model (POM):** Clean separation between test logic and UI locators.
*   **Parallel Execution:** Configured via `testng.xml` to execute test classes concurrently, heavily reducing total execution time.
*   **Continuous Integration (CI/CD):** Automatically triggers headless testing on Linux runners upon every push to the `main` branch via GitHub Actions.
*   **Cross-Browser & Headless Support:** Dynamically switches to `--headless=new` logic via a custom `DriverFactory` when executed in a CI environment.
*   **Target Application:** Currently configured to test against `https://demowebshop.tricentis.com`, a stable and public e-commerce demo environment.

## 📁 Project Architecture

```
src/
├── main/java/com/shopease/automation/
│   ├── base/           # BaseTest configuration & WebDriver initialization
│   ├── pages/          # Page Object classes (Login, Registration, Cart, etc.)
│   └── utils/          # DriverFactory, ConfigReader, Screenshots
└── test/
    ├── java/com/shopease/automation/tests/ # TestNG Test Cases
    └── resources/
        ├── config.properties # Global variables (URL, credentials, browser)
        └── testng.xml        # TestNG suite & parallel execution config
```

## 🛠️ How to Run Locally

You do not need Maven installed globally on your machine! This repository includes a Maven Wrapper (`mvnw`) for immediate cross-platform execution.

**1. Run tests normally (Browsers will open visibly):**
```bash
./mvnw clean test
```

**2. Run tests in Headless Mode (Browsers run invisibly in the background):**
```bash
./mvnw clean test -Dheadless=true
```

## ☁️ Continuous Integration (GitHub Actions)
This project contains a workflow file located at `.github/workflows/regression.yml`. 
Whenever new code is pushed to this repository, GitHub automatically provisions an `ubuntu-latest` server, installs JDK 17, and executes the entire test suite in headless mode. You can view the real-time test execution results in the **Actions** tab of this repository.

## 📝 Test Scenarios Covered
1.  **Authentication:** Validates secure login mechanisms and invalid credential handling.
2.  **Registration:** Validates the flow for creating a new user account.
3.  **Product Search & Cart:** Automates searching for an item, navigating to the Product Details Page (PDP), and adding the item to the shopping cart.
