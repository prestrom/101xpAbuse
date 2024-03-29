import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main (String args[]) throws InterruptedException, IOException {
        String firstPage = "https://yandex.ru/promo/browser/101xp/2?lang=ru&clinst=2359302&partner_id=13609&banerid=&old_browser_version=0.0.0.0&install_type=0&installID=163990671506422313_1575315687700";
        String url = "https://101xp.com/?utm_source=ad101xp_special_ya_plus_browser_CIS&utm_medium=Special_Portal&utm_campaign=special_ya_plus_browser_CIS";
        String cabinet = "https://101xp.com/profile/balance";
        WebElement xpHeader = null;
        String email = null;
        String password = "Timetofade1";
        int firstIteration = 1;
        int lastIteration = 1;
        String postfix = "@mail.com";
        String shop = "https://market.101xp.com/ru/shop/icarus";
        String market = "https://market.101xp.com/ru";
        int amount = 100;

//for (int i = firstIteration; i>=lastIteration; i++) {
        email = 30+"trickster"+30+postfix;
//}
        System.setProperty("webdriver.chrome.driver", "C:\\yandexdriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        // JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.manage().window().maximize();
        // driver.get(firstPage);

        WebElement button = driver.findElement(By.xpath("//span[@class='y-button__text']"));
        button.click();
        Thread.sleep(5000);
        ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(2));
        String originalHandle = driver.getWindowHandle();

        //Do something to open new tabs

        for(String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }

        driver.switchTo().window(originalHandle);
        // driver.switchTo().window(tabs.get(1));
        //driver.get(url);
        //  WebElement loginButton =  driver.findElement(By.xpath("//button[@class='btn-enter']"));
        //   loginButton.click();
        WebElement emailField = driver.findElement(By.xpath("//input[@type='email']"));
        emailField.sendKeys(email);
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.sendKeys(password);
        WebElement checkbox = driver.findElement(By.xpath("//div[@class='check']"));
        checkbox.click();
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();
        String money = StringUtils.chop(driver.findElement(By.xpath("//span[@class='money']")).getText());
        System.out.println("1st iteration: "+money);
        //    driver.get(cabinet);

        while (Integer.parseInt((money)) != amount)
        {
            driver.navigate().refresh();
            Thread.sleep(5000);
            money = StringUtils.chop(driver.findElement(By.xpath("//span[@class='money']")).getText());
            System.out.println("money ="+ money + ", waiting for :" +amount+", refreshing...");
        }


        try {
            driver.get(market);
            if (driver.findElement(By.xpath("//div[@id='mCSB_1']")) != null) {
                driver.findElement(By.xpath("//div[@class='tutorial-close']")).click();
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@id='product-hint_80']")).click();
        driver.findElement(By.xpath("//button[@class='buy-btn'][@data-store_item_id=80]")).click();
        driver.findElement(By.xpath("//button[@class='open-btn']")).click();
        Thread.sleep(5000);
        driver.get(shop);

        Thread.sleep(5000);
        WebElement element = driver.findElement(By.xpath("//a[@data-item_id=36298]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
        element.click();
        Thread.sleep(12000);
        String image = driver.findElement(By.xpath("//div[@class='shop__card-drop']/img")).getAttribute("src");
        System.out.println(image);
        // Thread.sleep(5000);



        //driver.quit();
    }

}

