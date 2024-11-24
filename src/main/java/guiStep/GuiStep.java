package guiStep;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import stepConstant.StepConstant;

import java.util.List;
import java.util.Objects;

public class GuiStep {

    static WebDriver driver;


    public static boolean lunchBrowser(){
        try{
            driver = new ChromeDriver();
            driver.get(StepConstant.GUI_URL);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();

            return  true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clickRevenueCalculatorPage(String menuItem) throws InterruptedException {
       try{
           String pathName = "//div[text()='" +menuItem + "']";
           driver.findElement(By.xpath(pathName)).click();
           Thread.sleep(2000);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
    public static void scrollDownToScrollSection() throws InterruptedException {
        try{

            WebElement slider = driver.findElement(By.xpath(StepConstant.REVENUE_CALCULATOR_SECTION));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "let element = arguments[0];" +
                            "let topPosition = element.getBoundingClientRect().top + window.scrollY;" +
                            "let offset = window.innerHeight / 2 - element.offsetHeight / 2;" +
                            "window.scrollTo({top: topPosition - offset});",
                    slider
            );
            Thread.sleep(2000);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static int adjustSlider(int desireValue) {
        try {
            WebElement slider = driver.findElement(By.cssSelector(StepConstant.REVENUE_CALCULATOR_SLIDER_RANGE));
            int currentValue = Integer.parseInt(Objects.requireNonNull(slider.getAttribute("value")));
            System.out.println("Current Slider value is : " + currentValue);
            if (currentValue < desireValue) {
                for (int i = currentValue; i < desireValue; i++) {
                    slider.sendKeys(Keys.ARROW_RIGHT); // Increment the slider
                }
            } else if (currentValue > desireValue) {
                for (int i = currentValue; i > desireValue; i--) {
                    slider.sendKeys(Keys.ARROW_LEFT); // Decrement the slider
                }
            }
            int finalValue = Integer.parseInt(Objects.requireNonNull(slider.getAttribute("value")));
            return finalValue;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void quitDriver() {
        driver.quit();
    }

    public static void sendKeyTextField(String keyValue) throws InterruptedException {
        try {
            Thread.sleep(2000);
            WebElement el = driver.findElement(By.xpath(StepConstant.REVENUE_CALCULATOR_INPUT_TEXT));
            String currentValue = el.getAttribute("value");
            for (int i = 0; i < currentValue.length(); i++)
            {
                el.sendKeys(Keys.BACK_SPACE);
            }
            el.sendKeys(keyValue);
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean validateInputValueWithSliderValue() throws InterruptedException {

        try{
            WebElement slider = driver.findElement(By.cssSelector(StepConstant.REVENUE_CALCULATOR_SLIDER_RANGE));
            String currentSliderValue = slider.getAttribute("value");
            System.out.println("Current Slider value is : " + currentSliderValue);

            WebElement inputBox = driver.findElement(By.xpath(StepConstant.REVENUE_CALCULATOR_INPUT_TEXT));
            String inputBoxValue = inputBox.getAttribute("value");
            System.out.println("Current Input Box Value is : " + inputBoxValue);
            Thread.sleep(2000);
            if(currentSliderValue.equalsIgnoreCase(inputBoxValue)){
                return true;
            }
            else {
                return false;
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendCheckBoxValues(String checkBoxValueHeader)  throws InterruptedException{
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,250)", "");
            WebElement parentDiv = driver.findElement(By.xpath(StepConstant.REVENUE_CALCULATOR_INPUT_CHECKBOX));
            List<WebElement> cards = parentDiv.findElements(By.xpath("./div"));
            String targetValue = checkBoxValueHeader;
            for (WebElement card : cards) {
                WebElement pTag = card.findElement(By.xpath(".//p"));
                if (pTag.getText().equals(targetValue)) {
                    WebElement checkbox = card.findElement(By.xpath(".//span/input[@type='checkbox']"));
                    checkbox.click();
                    Thread.sleep(2000);
                    break;

                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validateValuesFlexBox(String text, String value) throws InterruptedException {
        try {
            WebElement parentDiv = driver.findElement(By.xpath(StepConstant.REVENUE_CALCULATOR_INPUT_VALIDATION)); // Update class or locator
            System.out.println(parentDiv);
            List<WebElement> elements = parentDiv.findElements(By.xpath("./p")); // Assuming each card is a direct child of the parent div
            System.out.println(elements);
            String targetValue = text;
            for (WebElement element : elements) {
                String textLabel = element.getText();
                System.out.println(textLabel);
                if (textLabel.contains(targetValue)) {
                    WebElement labelValueItem = element.findElement(By.xpath("./p"));
                    System.out.println(labelValueItem);
                    String labelValue = labelValueItem.getText();
                    System.out.println(labelValue);
                    Thread.sleep(2000);
                    if (labelValue.equalsIgnoreCase(value)) {
                        return true;
                    } else {
                        return false;
                    }

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    return false;
    }
}
