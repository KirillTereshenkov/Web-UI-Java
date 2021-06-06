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
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class Test2 {
    private static WebDriver driver;
    private static final String URL ="https://crm.geekbrains.space/user/login";
    private static final String LOGIN = "Applanatest1";
    private static final String PASS = "Student2020!";
    private static final String Menu = "//ul[contains(@class,'nav-multilevel')]/li[contains(.,'Контрагенты')]";
    private static final String Submenu = "//li[@data-route='crm_contact_index']/a";
    private static final String Button = "//a[@title='Создать контактное лицо']";
    private static final String organization = "//span[@class='select2-arrow']";
    private static final String Input = "//input[contains (@class,'select2-input')]";
    private static final String Result = "//div[@class='select2-result-label']";
    private static final String save = "//button[contains(.,'Сохранить и закрыть')]";

    public static void main( String[] args ) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);

        //Ожидание
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //Авторизация
        driver.get(URL);
        driver.manage().window().maximize();

        WebElement loginTextInput = driver.findElement(By.cssSelector("input[id='prependedInput']"));
        loginTextInput.sendKeys(LOGIN);

        WebElement passTextInput = driver.findElement(By.id("prependedInput2"));
        passTextInput.sendKeys(PASS);

        WebElement loginButton = driver.findElement(By.xpath("//button[@id='_submit']"));
        loginButton.click();

        //Выпадающее меню
        Actions actionContractor = new Actions(driver);
        WebElement contractorMenu = driver.findElement(By.xpath(Menu));
        actionContractor.moveToElement(contractorMenu).perform();

        driver.findElement(By.xpath(Submenu)).click();

        //Создание контакта
        driver.findElement(By.xpath(Button)).click();

        //Заполнение формы
        WebElement fieldLastName = driver.findElement(By.name("crm_contact[lastName]"));
        fieldLastName.sendKeys("Терещенков");
        System.out.println("Поле Фамилия заполнено: "+ !fieldLastName.getAttribute("value").isEmpty());
        System.out.println("+++++++++++++++++++++");

        WebElement fieldFirstName = driver.findElement(By.name("crm_contact[firstName]"));
        fieldFirstName.sendKeys("Кирилл");
        System.out.println("Поле Имя заполнено: "+ !fieldFirstName.getAttribute("value").isEmpty());
        System.out.println("+++++++++++++++++++++");

        WebElement fieldOrganization = driver.findElement(By.name("crm_contact[company]"));

        driver.findElement(By.xpath(organization)).click();
        driver.findElement(By.xpath(Input)).sendKeys("123test");
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(Result))));
        driver.findElement(By.xpath(Input)).sendKeys(Keys.ENTER);

        System.out.println("Поле Организация Заполнено: " + !fieldOrganization.getAttribute("value").isEmpty());
        System.out.println("+++++++++++++++++++++");

        WebElement fieldJobTitle = driver.findElement(By.name("crm_contact[jobTitle]"));
        fieldJobTitle.sendKeys("Тестировщик");
        System.out.println("Поле должность заполнено: " + !fieldJobTitle.getAttribute("value").isEmpty());
        System.out.println("+++++++++++++++++++++");

        driver.findElement(By.xpath(save)).click();

        System.out.println("Test is completed");
        driver.quit();

    }
}
