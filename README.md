# Site Automation Test Framework

Test Automation Framework for [the-internet.herokuapp.com](https://the-internet.herokuapp.com) using Java + Selenium WebDriver.

## Technology Stack

- **Language**: Java 11+
- **Test Framework**: Selenium WebDriver 4.x
- **Test Runner**: TestNG 7.x
- **Build Tool**: Apache Maven 3.8+
- **Driver Management**: WebDriverManager 5.x
- **Logging**: SLF4J Simple

## Project Structure

```
SiteAutomation/
├── pom.xml                          # Maven configuration
├── testng.xml                       # TestNG suite configuration
├── src/
│   └── test/
│       └── java/
│           └── com/
│               └── siteautomation/
│                   ├── base/
│                   │   └── BaseTest.java          # Base test class with setup/teardown
│                   ├── pages/                      # Page Object Model classes
│                   │   ├── ABTestingPage.java
│                   │   ├── AddRemoveElementsPage.java
│                   │   ├── BasicAuthPage.java
│                   │   ├── BrokenImagesPage.java
│                   │   ├── ChallengingDOMPage.java
│                   │   ├── CheckboxesPage.java
│                   │   ├── ContextMenuPage.java
│                   │   ├── DigestAuthPage.java
│                   │   ├── DisappearingElementsPage.java
│                   │   ├── DragAndDropPage.java
│                   │   ├── DropdownPage.java
│                   │   ├── DynamicContentPage.java
│                   │   ├── DynamicControlsPage.java
│                   │   ├── FileDownloadPage.java
│                   │   ├── FileUploadPage.java
│                   │   ├── FloatingMenuPage.java
│                   │   ├── ForgotPasswordPage.java
│                   │   ├── FramesPage.java
│                   │   ├── GeolocationPage.java
│                   │   ├── InputsPage.java
│                   │   ├── JavaScriptAlertsPage.java
│                   │   ├── LoginPage.java
│                   │   ├── MultipleWindowsPage.java
│                   │   ├── NotificationMessagesPage.java
│                   │   ├── RedirectLinkPage.java
│                   │   └── StatusCodesPage.java
│                   └── tests/                      # TestNG test classes
│                       ├── ABTestingTest.java
│                       ├── AddRemoveElementsTest.java
│                       ├── BasicAuthTest.java
│                       ├── BrokenImagesTest.java
│                       ├── ChallengingDOMTest.java
│                       ├── CheckboxesTest.java
│                       ├── ContextMenuTest.java
│                       ├── DigestAuthTest.java
│                       ├── DisappearingElementsTest.java
│                       ├── DragAndDropTest.java
│                       ├── DropdownTest.java
│                       ├── DynamicContentTest.java
│                       ├── DynamicControlsTest.java
│                       ├── FileDownloadTest.java
│                       ├── FileUploadTest.java
│                       ├── FloatingMenuTest.java
│                       ├── ForgotPasswordTest.java
│                       ├── FormAuthenticationTest.java
│                       ├── FramesTest.java
│                       ├── GeolocationTest.java
│                       ├── InputsTest.java
│                       ├── JavaScriptAlertsTest.java
│                       ├── MultipleWindowsTest.java
│                       ├── NestedFramesTest.java
│                       ├── NotificationMessagesTest.java
│                       ├── RedirectLinkTest.java
│                       └── StatusCodesTest.java
└── README.md
```

## Test Cases Implemented

The framework implements test cases for the following features:

1. ✅ A/B Testing
2. ✅ Add/Remove Elements
3. ✅ Basic Auth (admin/admin)
4. ✅ Broken Images
5. ✅ Challenging DOM
6. ✅ Checkboxes
7. ✅ Context Menu
8. ✅ Digest Authentication (admin/admin)
9. ✅ Disappearing Elements
10. ✅ Drag and Drop
11. ✅ Dropdown
12. ✅ Dynamic Content
13. ✅ Dynamic Controls
14. ✅ File Download
15. ✅ File Upload
16. ✅ Floating Menu
17. ✅ Forgot Password
18. ✅ Form Authentication
19. ✅ Frames
20. ✅ Geolocation
21. ✅ Inputs
22. ✅ JavaScript Alerts
23. ✅ Multiple Windows
24. ✅ Nested Frames
25. ✅ Notification Messages
26. ✅ Redirect Link
27. ✅ Status Codes

## Prerequisites

- Java JDK 11 or higher
- Apache Maven 3.8+
- Chrome browser installed (ChromeDriver is managed automatically by WebDriverManager)

## Setup

1. Clone the repository:
```bash
git clone <repository-url>
cd SiteAutomation
```

2. Build the project:
```bash
mvn clean compile
```

## Running Tests

### Run all tests:
```bash
mvn test
```

### Run specific test class:
```bash
mvn test -Dtest=CheckboxesTest
```

### Run tests matching a pattern:
```bash
mvn test -Dtest=*AuthTest
```

### Run tests using TestNG suite:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

## Configuration

### Chrome Options

The framework configures Chrome with the following options (per PRD):
- `--disable-notifications`: Disable browser notifications
- `--start-maximized`: Start browser maximized
- `--disable-infobars`: Disable info bars
- `--no-sandbox`: Disable sandbox (for CI/CD environments)
- `--disable-dev-shm-usage`: Disable /dev/shm usage

### Base URL

All tests target: `https://the-internet.herokuapp.com`

### Wait Times

- **Implicit Wait**: 5 seconds
- **Explicit Wait**: 10 seconds (WebDriverWait)

## Test Design Patterns

- **Page Object Model (POM)**: All page interactions are encapsulated in Page Object classes
- **Arrange-Act-Assert (AAA)**: Tests follow the AAA pattern
- **Explicit Waits**: WebDriverWait is used instead of Thread.sleep
- **WebDriverManager**: Automatic driver binary management

## Credentials

- **Basic Auth**: username=`admin`, password=`admin`
- **Digest Auth**: username=`admin`, password=`admin`
- **Form Auth**: username=`tomsmith`, password=`SuperSecretPassword!`

## Troubleshooting

### ChromeDriver Issues
WebDriverManager automatically downloads and manages ChromeDriver. If you encounter issues:
1. Ensure Chrome browser is installed
2. Check internet connection (for driver download)
3. Verify Java version is 11+

### Test Failures
- Check browser console logs for JavaScript errors
- Verify network connectivity to the-internet.herokuapp.com
- Ensure Chrome browser is up to date

## Contributing

When adding new test cases:
1. Create a Page Object class in `com.siteautomation.pages`
2. Create a TestNG test class in `com.siteautomation.tests`
3. Extend `BaseTest` for setup/teardown
4. Follow the AAA pattern
5. Use explicit waits instead of Thread.sleep

## License

This project is for educational/testing purposes.
