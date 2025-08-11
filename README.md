README.md# AutomationHomeAssignment

[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-orange.svg)](https://maven.apache.org/)
[![Selenium](https://img.shields.io/badge/Selenium-4.x-green.svg)](https://www.selenium.dev/)
[![Allure Report](https://img.shields.io/badge/Allure-Reports-yellow.svg)](https://qameta.io/allure-report/)

Java + Selenium + TestNG automation framework with Allure reporting and reusable utilities.

---

## 📌 Overview
This project automates UI testing using:
- **Java 21**
- **Selenium WebDriver**
- **TestNG**
- **Allure Reports**
- **Page Object Model (POM)**
- Custom reusable utilities for safe element interaction and logging

All steps and key actions are tracked in **Allure reports** using `@Step` annotations and `Allure.step()` calls for detailed traceability.

---

## 📂 Project Structure
```
AutomationHomeAssignment/
│
├── pom.xml                  # Maven dependencies & configuration
├── README.md                # Project documentation
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/        # Page Object Model classes
│   │       ├── utilities/    # Helper methods (safeFindText, etc.)
│   │       └── config/       # Config reader
│   │
│   └── test/
│       ├── java/             # Test classes
│       └── resources/        # testng.xml and other resources
│
└── target/                   # Build output (ignored by Git)
    └── allure-results/       # Allure raw results (ignored by Git)
```

---

## ⚙️ Setup

### 1️⃣ Prerequisites
- **Java 21**
- **Maven 3.8+**
- **Google Chrome** & **ChromeDriver** (or other browser driver)
- **Allure CLI** (install from [Allure official site](https://docs.qameta.io/allure/))

### 2️⃣ Clone the repository
```bash
git clone https://github.com/<your-username>/AutomationHomeAssignment.git
cd AutomationHomeAssignment
```

### 3️⃣ Install dependencies
```bash
mvn  mvn clean test
allure serve target/allure-results
```

---

## ▶️ Running Tests

### Run all tests:
```bash
mvn clean test
```

### Run with TestNG XML:
```bash
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml
```

---

## 📊 Generating Allure Report
```bash
allure serve target/allure-results
```
This command will open the Allure report in your default browser.

---

## 📸 Allure Report Sample

![Allure Report Sample](docs/allure-sample.png)

---

## 🧩 Key Utility & Verification Methods

| Method | What it does | Notes (Allure) |
|---|---|---|
| `safeFindText(WebElement parent, By locator)` | Safely finds text of a child element. Scrolls into view, waits, trims, returns `""` if missing. | Logs locator/parent, visibility handling, extracted text, errors. |
| `safeFindAttribute(WebElement parent, By locator, String attribute)` | Reads a given attribute. Returns trimmed value or default message. | Logs attribute name/locator, parent state, value or error. |
| `centerWebElementWithJS(WebElement element)` | Centers the element in viewport via JS `scrollIntoView`. | Logs target element and result; warns on null; errors rethrown. |
| `DateUtils.isValidDate(String dateStr, String pattern, Locale locale)` | Validates date string strictly against pattern and locale. | Logs inputs, parsed date, equality check, or parse failure. |
| `DateUtils.safeExtractYear(String text)` | Extracts first year between 1900–2099; returns `***` if none. | Logs input, found year, or no match; catches unexpected errors. |
| `DateUtils.convertRelativeToAbsolute(String relative, String format, ZoneId zone)` | Converts “Today / Yesterday / X days ago” to absolute date. | Logs inputs, base date, matched rule, and final result. |
| `storeNewsInfo(List<WebElement> items, NewsPage page)` | Builds `NewsObject` list (WHO filter for “Joint News Release”/“Statement”). | Logs per-item processing, type filter decisions, fields added. |
| `exportNews(List<NewsObject> list)` | Sorts then writes news to `target/latest_news.txt`. | Logs count, sorting, file path, per-article write, success/errors. |
| `verifyNewsData(List<NewsObject> list)` | Soft-asserts title, URL, year-in-URL, and site-specific date format. | Logs each article’s checks and expected patterns (WHO/APPLE). |
| `positivityFrom11(List<WebElement> rows)` | Extracts region → positivity (column index 3) from table 11. | Logs row-by-row parsing, skips, extracted pairs, errors. |
| `positivityFrom13(List<WebElement> rows)` | Extracts region → positivity (column index 2) from table 13. | Same style of logging as above. |

---

## 📝 Notes
- Allure steps are added for **every key action** to improve debugging and reporting.
- Failures include screenshots and logged parameters for better traceability.
- The `target/` folder (including `allure-results/`) is **ignored** in `.gitignore` to keep the repo clean.

---

## 🚀 Author
**Stas Kolesnikov**  
Senior Automation Developer | Selenium | Java | TestNG | Allure | API Testing
