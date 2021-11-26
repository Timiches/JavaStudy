package DopLab5;

public class PremiumContribution extends BankAccount{
    int cashFlag = 0, i = 0;

    PremiumContribution(int money, String name, int percent){
        this.money = money;
        this.contributor = name;

        this.percent = percent;
        this.type = 4;
    }

    public void calc() {
        System.out.println("At this month user "+ this.bankAccountID + " has earned " + this.money/100*percent + " and now has "+ (this.money/100*percent+this.money));
        this.money = this.money/100*percent + this.money;
    }

    public void ContributionInfo() {
        System.out.println("4");
    }

    @Override
    public void addCash(int cash) {
        super.addCash(cash);
        cashFlag = 1;
    }

    @Override
    void check() {
        super.check();
        i++;
        if(cashFlag == 1){
            i = 0;
            cashFlag = 0;
        } else if (cashFlag == 0 && i >= 120){
            System.out.println("User "+ this.bankAccountID + " has earned a prime " + this.money/100*3 + " and now has "+ (this.money/100*3+this.money));
            this.money = this.money/100*3 + this.money;
        }
    }
}
