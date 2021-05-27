package features;

import io.cucumber.java.ParameterType;
import revolut.Account;
import revolut.PaymentService;

import java.util.Currency;

public class ParameterTypes {
    @ParameterType("BankAccount|DebitCard")
    public PaymentService paymentService(String type){
        return new PaymentService(type);
    }

    @ParameterType("SonnysAccount")
    public Account account(String accountHolder){
        return new Account(accountHolder);
    }
}
