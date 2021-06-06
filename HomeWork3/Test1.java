package HomeWork3;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class Test1 {
    private static WebDriver driver;
    private static  final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String STUDENT_LOGIN = "Applanatest1";
    private static final String STUDENT_PASS = "Student2020!";
    private static final String Menu = "//li[@class='dropdown']/a[contains(., 'Проекты')]";
    private static final String Submenu = "//li[@data-route='crm_project_my']/a";
    private static final String Button = "//a[@title='Создать проект']";
    private static final String organization = "//div[@class='company-container']/div/a/span[@class='select2-arrow']";
    private static final String Input = "//input[@class='select2-input select2-focused']";
    private static final String Result = "//div[@class='select2-result-label']";
    private static final String save = "//button[contains(.,'Сохранить и закрыть')]";

    public static void main( String[] args ) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //Авторизация
        driver.get(LOGIN_PAGE_URL);
        driver.manage().window().maximize();
        WebElement loginTextInput = driver.findElement(By.cssSelector("input[id='prependedInput']"));
        loginTextInput.sendKeys(STUDENT_LOGIN);
        WebElement passTextInput = driver.findElement(By.id("prependedInput2"));
        passTextInput.sendKeys(STUDENT_PASS);
        WebElement loginButton = driver.findElement(By.xpath("//button[@id='_submit']"));
        loginButton.click();

        //Выпадающее меню
        Actions actionProject = new Actions(driver);
        WebElement contractorMenu = driver.findElement(By.xpath(Menu));
        actionProject.moveToElement(contractorMenu).perform();

        driver.findElement(By.xpath(Submenu)).click();

        //Создание проекта
        driver.findElement(By.xpath(Button)).click();

        //Заполнение формы
        WebElement fieldProlectName = driver.findElement(By.name("crm_project[name]"));
        fieldProlectName.sendKeys("Test Project 868");
        System.out.println("Поле название заполнено: " + !fieldProlectName.getAttribute("value").isEmpty());
        System.out.println("+++++++++++++++++++++");

        WebElement fieldOrganization = driver.findElement(By.name("crm_project[company]"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(organization))));
        driver.findElement(By.xpath(organization)).click();

        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(Input))));
        driver.findElement(By.xpath(Input)).sendKeys("123test");

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(Result))));
        driver.findElement(By.xpath(Input)).sendKeys(Keys.ENTER);

        System.out.println("Поле организация заполнено: " + !fieldOrganization.getAttribute("value").isEmpty());
        System.out.println("+++++++++++++++++++++");

        Select fieldMainContact = new Select(driver.findElement(By.name("crm_project[contactMain]")));
        fieldMainContact.selectByVisibleText("Терещенков Кирилл");

        System.out.println("Поле контактное лицо заполнено: " + fieldMainContact.getFirstSelectedOption().getText().equals("Терещенков Кирилл"));
        System.out.println("+++++++++++++++++++++");

        Select fieldBusinessUnit = new Select(driver.findElement(By.name("crm_project[businessUnit]")));
        fieldBusinessUnit.selectByValue("1");

        System.out.println("Поле подразделение заполнено: " + fieldBusinessUnit.getFirstSelectedOption().getAttribute("value").equals("1"));
        System.out.println("+++++++++++++++++++++");

        Select fieldProjectCurator = new Select(driver.findElement(By.name("crm_project[curator]")));
        fieldProjectCurator.selectByVisibleText("Applanatest1 Applanatest1 Applanatest1");

        Select fieldProjectDirector = new Select(driver.findElement(By.name("crm_project[rp]")));
        fieldProjectDirector.selectByVisibleText("Applanatest1 Applanatest1 Applanatest1");

        Select fieldProjectManager = new Select(driver.findElement(By.name("crm_project[manager]")));
        fieldProjectManager.selectByVisibleText("Applanatest1 Applanatest1 Applanatest1");

        driver.findElement(By.xpath(save)).click();

        System.out.println("Test is completed");
        driver.quit();
    }
}
