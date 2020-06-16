package com.identitye2etest.steps;

import com.identitye2etest.car.CarDetails;
import com.identitye2etest.car.CarDetailsBuilder;
import com.identitye2etest.pages.CarTaxCheckPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarTaxCheckSteps {

    private ArrayList<String> inputFileLines = new ArrayList<String>();
    private ArrayList<String> carRegistrationNumbers = new ArrayList<String>();

    private CarTaxCheckPage carTaxCheckPage = new CarTaxCheckPage();

    private ArrayList<CarDetails> carDetailsFromWebsite = new ArrayList<CarDetails>();
    private ArrayList<CarDetails> carDetailsFromOutput = new ArrayList<CarDetails>();

    private CarDetailsBuilder carDetailsBuilder = new CarDetailsBuilder();

    @Given("^I read the input file \"([^\"]*)\"$")
    public void iReadTheInputFile(String carInputFile) throws Throwable {

        String filePathName = "src/test/resources/data/" + carInputFile;

        BufferedReader br = new BufferedReader(new FileReader(filePathName));
        while (br.ready()) {
            inputFileLines.add(br.readLine());
        }
    }

    @When("^I extract vehicle registration numbers based on pattern$")
    public void iExtractVehicleRegistrationNumbersBasedOnPattern() throws Throwable {
        Pattern pattern = Pattern.compile("([A-Z]{2}[0-9]{2} [A-Z]{3})|([A-Z]{2}[0-9]{2}[A-Z]{3})");

        for(String line : inputFileLines)
        {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String carRegistration = matcher.group().replace(" ", "");
                System.out.println(carRegistration);
                carRegistrationNumbers.add(carRegistration);
            }
        }
    }

    @When("^I fed each vehicle registration numbers extract from input file to cartaxcheck website and perform free car check$")
    public void iFedEachVehicleRegistrationNumbersExtractFromInputFileToCartaxcheckWebsite() throws Throwable {
        for(String carRegistration : carRegistrationNumbers) {
            carTaxCheckPage.openUrl();
            carTaxCheckPage.enterRegistration(carRegistration);
            carTaxCheckPage.clickFreeCarCheck();

            Thread.sleep(1000);

            String carRegistrationObtained = carTaxCheckPage.getRegistration();
            if(carRegistrationObtained.equals("not found"))
                carRegistrationObtained = carRegistration;

            carDetailsFromWebsite.add(
                carDetailsBuilder
                    .withRegistration(carRegistrationObtained)
                    .withMake(carTaxCheckPage.getMake())
                    .withModel(carTaxCheckPage.getModel())
                    .withColour(carTaxCheckPage.getColour())
                    .withYear(carTaxCheckPage.getYear())
                    .build()
            );
        }
    }

    @Then("^I compare the output returned with output file \"([^\"]*)\"$")
    public void iCompareTheOutputReturnedWithOutputFile(String carOutputFile) throws Throwable {
        String filePathName = "src/test/resources/data/" + carOutputFile;
        BufferedReader br = new BufferedReader(new FileReader(filePathName));
        int count = 0;
        while (br.ready()) {
            String[] carDetailArray = br.readLine().split(",");
            if(count != 0) {
                carDetailsFromOutput.add(
                        carDetailsBuilder
                                .withRegistration(carDetailArray[0].trim())
                                .withMake(carDetailArray[1].trim())
                                .withModel(carDetailArray[2].trim())
                                .withColour(carDetailArray[3].trim())
                                .withYear(carDetailArray[4].trim())
                                .build()
                );
            }
            count++;

        }

        compareAndAssert();
    }

    private void compareAndAssert() {
        for(CarDetails carDetailsOutput : carDetailsFromOutput) {
            CarDetails carDetailsWebsite = findCarDetailWebsite(carDetailsOutput.getRegistration());

            Assert.assertNotNull("Vehicle registration " + carDetailsOutput.getRegistration() + " was missing in input file", carDetailsWebsite);

            Assert.assertFalse("Vehicle " + carDetailsOutput.getRegistration() + " not found", carDetailsWebsite.getMake().equals(""));

            Assert.assertTrue("Vehicle " + carDetailsOutput.getRegistration() + " mismatch from output file", carDetailsWebsite.detailsMatch(carDetailsOutput));

        }
    }

    private CarDetails findCarDetailWebsite(String registration) {
        CarDetails carDetailsFound = null;
        for(CarDetails carDetailsWebsite : carDetailsFromWebsite) {
            if(carDetailsWebsite.getRegistration().equals(registration))
                carDetailsFound = carDetailsWebsite;
        }
        return carDetailsFound;
    }

}
