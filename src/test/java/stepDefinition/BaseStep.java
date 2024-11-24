package stepDefinition;

import guiStep.GuiStep;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class BaseStep {


    @Given("^Open the Fitpeo Official Website$")
    public void OpenUrl(){
        Assert.assertTrue(GuiStep.lunchBrowser());
    }
    @When("^Navigate to (.*) Tab$")
    public void navigateToRevenueCalculatorTab(String menuItem) throws InterruptedException {
        GuiStep.clickRevenueCalculatorPage(menuItem);
        System.out.println(menuItem + " Navigation Menu Clicked!");
    }

    @And("^Scroll Down to the Slider section$")
    public void scrollDownToTheSliderSection() throws InterruptedException {
        GuiStep.scrollDownToScrollSection();
        System.out.println("Scroll Down to the slider section");
    }

    @And("^Adjust the Slider and set value to (.*)$")
    public void adjustTheSliderAndSetValueTo(int desireValue) {

        int currentValue = GuiStep.adjustSlider(desireValue);
        if (currentValue == desireValue){
            System.out.println("Slider successfully set to " + currentValue + "!");
        }
        else {
            System.out.println("Slider value did not update correctly. Expected: " + desireValue + " Current Value is: " + currentValue);
        }

    }

    @And("^quit driver$")
    public void quitDriver() {
        GuiStep.quitDriver();
    }

    @And("^Enter the value (.*) in the text field$")
    public void sendKeyTextField(String keyValue) throws InterruptedException {
        GuiStep.sendKeyTextField(keyValue);
        System.out.println("Text Field is updated with value : " + keyValue);


    }

    @Then("Validate input value should be match with slider value")
    public void validateInputValueWithSliderValue() throws InterruptedException{
        Assert.assertTrue("Slider and Input box Value does not match", GuiStep.validateInputValueWithSliderValue());
        System.out.println("Slider and Input box Value matched!");
    }


    @And("^Select the checkboxes for following values$")
    public void sendCheckBoxValues(DataTable dataTable) throws InterruptedException{
        for(int i = 1; i < dataTable.asList().size(); i++ ){
            String checkBoxValueHeader = dataTable.asList().get(i);
            GuiStep.sendCheckBoxValues(checkBoxValueHeader);
        }
    }

    @Then("^Validate values of (.*) is (.*)$")
    public void validateValuesFlexBox(String text, String value) throws InterruptedException{
        Assert.assertTrue("Value does not match", GuiStep.validateValuesFlexBox(text, value));
        System.out.println("Value matched!");
    }
}
