package DopLab5;

import java.util.Random;

public abstract class BankAccount {

    Random rand = new Random();

    int bankAccountID;
    String contributor;
    protected int money; // скрыть
    int type;
    int day;
    int percent;

    public void addCash(int cash){
        money += cash;
        System.out.println("Now you have: "+ money);
    }

    public void removeCash(int cash){
        if (money < cash){
            System.out.println("To much money! You have only "+ money);
        } else {
            money -= cash;
            System.out.println("Now you have: "+ money);
        }
    }

    public void infoCash(){
        String name = contributor;
        if(name == null){
            name = "Anonymous";
        }
        System.out.println("Contributor "+ this.bankAccountID +" with name "+ name +" have " + money);
    }

    public void infoCashOfMontEnd(){
        System.out.println("For the end of calendar month contributor " + contributor + " have " + money);
    }


    abstract void calc();
    abstract void ContributionInfo();
    void check() {
        day++;
        if(day >= 30){
            calc();
            day = 0;
        }
    }


}
