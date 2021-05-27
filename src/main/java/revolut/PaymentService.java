package revolut;

public class PaymentService {
    private String serviceName;
    private double currentBalance;

    public PaymentService(String name){
        this.serviceName = name;
    }

    public String getType() {
        return serviceName;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
    public boolean isTransferAllowed(double amount){
        if(getCurrentBalance()>amount){
            return  true;
        }else
             return false;
    }

    public void sendMoney(Account accountToSendTo,double amount){
        if(isTransferAllowed(amount)){
            System.out.println("Payment transfer "+amount+" accepted");
            accountToSendTo.addFunds(amount);
        }
        else {
            System.out.println("Payment transfer "+amount+" rejected");
        }
    }
}
