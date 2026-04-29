Selenium Automation Framework (Java + TestNG + CI/CD)

This project is a scalable end-to-end automation framework built using Selenium WebDriver with Java.  
It is designed to automate web application workflows with a focus on maintainability, reusability, and CI/CD integration.

---

🧰 Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- Jenkins (CI/CD)
- Git & GitHub
- ExtentReports

---

🏗️ Framework Design

- Page Object Model (POM) for clean separation of test logic and UI locators.
- Page Factory for efficient WebElement initialization.
- Data-driven testing using TestNG DataProviders (JSON-based input).
- Reusable utility classes for waits, actions, and browser handling.
- ThreadLocal WebDriver for thread-safe parallel execution.
- Retry mechanism for handling flaky test scenarios.
- Centralized configuration using a properties file.

---

⚙️ Key Features

- Automated end-to-end workflows (login, product selection, cart, checkout).
- CI/CD integration with Jenkins (triggered via GitHub webhooks).
- Parallel test execution support.
- Explicit wait handling for dynamic elements.
- Screenshot capture on test failure.
- Rich HTML reporting using ExtentReports.

---

🔄 CI/CD Integration

- Jenkins job configured to trigger builds on GitHub push events.
- Maven used for build lifecycle management.
- Supports profile-based execution (e.g., Regression suite).

Example:
bash
mvn test -PRegression

📁 Project Structure
src/
 ├── main/java/
 │    ├── PageObjects
 │    ├── AbstractComponents
 │    └── resources
 │
 ├── test/java/
 │    ├── tests
 │    ├── TestComponents
 │    └── data
 │
testSuite/
 ├── testng.xml
 ├── Regression.xml
 ├── Purchase.xml


▶️ How to Run
 
Run from CLI
mvn clean test

Run a specific profile
mvn test -PRegression

Run from TestNG XML
Use testng.xml or specific suite files under testSuite/

📊 Reporting
- ExtentReports generated after execution.
Includes:
  - ass/Fail status.
  - Screenshots on failure.
  - Execution logs.

🧠 Challenges Solved
- Handled flaky tests caused by dynamic UI and timing issues.
- Implemented ThreadLocal to resolve parallel execution conflicts.
- Stabilized CI execution using proper synchronization strategies.
- Resolved click interception issues using JS fallback for headless execution.

🔮 Future Enhancements
- Migration to Playwright (JavaScript/TypeScript).
- Docker-based test execution.
- Integration with cloud execution platforms (BrowserStack/Sauce Labs).
- Advanced reporting dashboards.

👨‍💻 Author
Raagaveandar Kumar S
Automation QA Engineer
