package DopLab5;

public class SimpleContribution extends BankAccount {
    SimpleContribution(int money, String name){
        this.money = money;
        this.contributor = name;
        this.bankAccountID = rand.nextInt(100)+1;
        this.type = 1;
        this.day = 0;
        this.percent = 6;
    }

    @Override
    void calc() {
        System.out.println("At this month user "+ this.bankAccountID + " has earned " + this.money/100*percent + " and now has "+ (this.money/100*percent+this.money));
        this.money = this.money/100*percent + this.money;
    }

    @Override
    void ContributionInfo() {
        System.out.println("1");
    }

}
