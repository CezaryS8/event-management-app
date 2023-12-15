MWO LAB 5
Przykładowy test selenium

public class CreateUserTest {

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testCreateUser() {
        driver.get("http://localhost:3000/users/create-user");

        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameInput.sendKeys("testuser");
        emailInput.sendKeys("testuser@example.com");
        submitButton.click();

        WebElement successMessage = driver.findElement(By.id("successMessage"));
        Assert.assertNotNull(successMessage, "Success message is not displayed");
        Assert.assertEquals(successMessage.getText(), "User created successfully", "The success message is not as expected.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
