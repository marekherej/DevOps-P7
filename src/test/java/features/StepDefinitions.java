package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.Account;
import revolut.PaymentService;
import revolut.Person;

public class StepDefinitions {

    private double topUpAmount;
    //private String topUpMethod;
    PaymentService topUpMethod;
    Person danny;
    Person sonny;

    @Before//Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
        sonny = new Person("Sonny");
    }
    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {
        //System.out.println("Danny has starting account balance of: " + String.valueOf(startingBalance));
        danny.setAccountBalance(startingBalance);
        //double newAccountBalance = danny.getAccountBalance("EUR");
        //System.out.println("Danny's new account balance if: " + String.valueOf(newAccountBalance));
    }

    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topUpAmount) {
        // Write code here that turns the phrase above into concrete actions
        this.topUpAmount = topUpAmount;
        //throw new io.cucumber.java.PendingException();
    }

    //@Given("Danny selects his {word} as his topUp method")
    @Given("Danny selects his {paymentService} as his topUp method")
    //public void danny_selects_his_debit_card_as_his_top_up_method(String topUpSource) {
    public void danny_selects_his_debit_card_as_his_top_up_method(PaymentService topUpSource) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("The selected payment service type was " + topUpSource.getType());
        topUpMethod = topUpSource;
    }

    @When("Danny tops up")
    public void danny_tops_up() {
        // Write code here that turns the phrase above into concrete actions
        danny.getAccount("EUR").addFunds(topUpAmount);
        //throw new io.cucumber.java.PendingException();
    }

    @Then("The new balance of his euro account should now be {double}")
    public void the_new_balance_of_his_euro_account_should_now_be(double newBalance) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    // additional features
    // 1. used with solution outline with provided examples.
    @Given("Danny has a starting balance of {double}")
    public void danny_has_a_starting_balance_of(double startingBalance) {
        // Write code here that turns the phrase above into concrete actions
        danny.setAccountBalance(startingBalance);
       // throw new io.cucumber.java.PendingException();
    }
    @When("Danny now tops up by {double}")
    public void danny_now_tops_up_by(double topUpAmount) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        danny.getAccount("EUR").addFunds(topUpAmount);

    }
    @Then("The balance in his euro account should be {double}")
    public void the_balance_in_his_euro_account_should_be(double newBalance) {
        // Write code here that turns the phrase above into concrete actions
       // throw new io.cucumber.java.PendingException();
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    @And("Danny selects his {paymentService} as his topUp method with {double} as the amount")
    public void danny_selects_his_bank_account_as_his_top_up_method_with_as_the_amount(PaymentService topUpSource, double topUpBy) {
        // Write code here that turns the phrase above into concrete actions
        // set the bank account to zero, set method and the amount requested
        topUpMethod = topUpSource;
        topUpAmount = topUpBy;
        topUpMethod.setCurrentBalance(0);
    }

    @Then("TopUp amount is rejected if the {paymentService} has no founds")
    public void top_up_amount_is_rejected_if_the_bank_account_has_no_founds(PaymentService topUpSource ) {
        // Write code here that turns the phrase above into concrete actions
       // throw new io.cucumber.java.PendingException();
        // Act
        double expected =  danny.getAccount("EUR").getBalance();
        topUpSource.sendMoney(danny.getAccount("EUR"),topUpAmount);
        double actual =  danny.getAccount("EUR").getBalance();
        // Assert
        Assert.assertEquals(expected,actual,0);

    }

    @Then("TopUp amount is accepted if the {paymentService} has founds")
    public void top_up_amount_is_accepted_if_the_bank_account_has_founds(PaymentService topUpSource) {
        // Arrange
        topUpSource.setCurrentBalance(100);
        // Act
        double expected =  30;
        topUpSource.sendMoney(danny.getAccount("EUR"),topUpAmount);
        double actual =  danny.getAccount("EUR").getBalance();
        // Assert
        Assert.assertEquals(expected,actual,0);

    }

    // Danny sends money to Sonny
    @When("Danny wants to send {double} euro to {account}")
    public void danny_wants_to_send_euro_to_sonnys_account(double amountToSend, Account targetAccount) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        topUpAmount = amountToSend;

    }
    @Then("The {int} is taken out form Danny account and added to {account}")
    public void the_is_taken_out_form_danny_account_and_added_to_sonnys_account(double amountToSend, Account target) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        // Arrange

        // Act
        double expected = danny.getAccount("EUR").getBalance()-amountToSend;
        danny.getAccount("EUR").takeFunds(amountToSend);
        sonny.getAccount("EUR").addFunds(amountToSend);
        double actual = danny.getAccount("EUR").getBalance();
        // Assert
        Assert.assertEquals(expected,actual,0);
    }

}
