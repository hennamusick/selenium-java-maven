# Multiple Base URL Management - Usage Examples

## Configuration Setup

Your `config.properties` now supports indexed base URLs:

```properties
baseUrl.0=https://testautomationpractice.blogspot.com
baseUrl.1=https://rahulshettyacademy.com/AutomationPractice
baseUrl.2=https://www.saucedemo.com
baseUrl.3=https://the-internet.herokuapp.com
```

## Usage Methods

### Method 1: Access by Index (Recommended)
```java
// Get specific URL by index
driver.get(ConfigReader.getBaseUrl(0));  // Gets first URL
driver.get(ConfigReader.getBaseUrl(1));  // Gets second URL
driver.get(ConfigReader.getBaseUrl(2));  // Gets third URL
```

### Method 2: Get All URLs as Array
```java
// Get all URLs and select one
String[] allUrls = ConfigReader.getAllBaseUrls();
driver.get(allUrls[0]);  // First URL
driver.get(allUrls[1]);  // Second URL

// Loop through all URLs
for (String url : allUrls) {
    driver.get(url);
    // Run tests on each URL
}
```

### Method 3: Traditional Property Key
```java
// Direct property access
driver.get(ConfigReader.getProperty("baseUrl.0"));
driver.get(ConfigReader.getProperty("baseUrl.1"));
```

## Example Test Class

```java
public class MultiUrlTest extends BaseTest {
    
    @Test
    public void testOnMultipleUrls() {
        String[] urls = ConfigReader.getAllBaseUrls();
        
        for (int i = 0; i < urls.length; i++) {
            System.out.println("Testing URL " + i + ": " + urls[i]);
            driver.get(urls[i]);
            // Perform tests
        }
    }
    
    @Test
    public void testSpecificUrl() {
        // Test only the second URL
        driver.get(ConfigReader.getBaseUrl(1));
        // Perform tests
    }
}
```

## Benefits

✅ Easy array-like access with index  
✅ No need to remember property names  
✅ Simple to add more URLs  
✅ Can iterate through all URLs easily  
✅ Backwards compatible with traditional property access

## Adding More URLs

Simply add new entries to `config.properties`:

```properties
baseUrl.4=https://example.com
baseUrl.5=https://another-site.com
```

The `getAllBaseUrls()` method automatically detects all configured URLs!
