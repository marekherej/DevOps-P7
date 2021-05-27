package revolut;

import javax.lang.model.element.NestingKind;
import java.util.Currency;

public class Account {
    private Currency accCurrency;
    private double balance;
    private String accountOwner;

    public Account(Currency currency, double balance){
        this.balance = balance;
        this.accCurrency = currency;
    }
    public Account(String holder){
        this.accountOwner = holder;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void addFunds(double topUpAmount) {
        this.balance += topUpAmount;
    }
    public void takeFunds(double amount) {
        this.balance -=  amount;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public Currency getAccCurrency() {
        return accCurrency;
    }
}
