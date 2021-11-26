package DopLab5;

public class SixMontContribution extends BankAccount {
    SixMontContribution(int money, String name){
        this.money = money;
        this.contributor = name;
        this.bankAccountID = rand.nextInt(100)+1;
        this.type = 2;
        this.percent = 36;
    }

    void calc() {
        System.out.println("At this half of year user "+ this.bankAccountID + " has earned " + this.money/100*percent + " and now has "+ (this.money/100*percent+this.money));
        this.money = this.money/100*percent + this.money;
    }

    void ContributionInfo() {
        System.out.println("2");
    }

    @Override
    void check() {
        day++;
        if(day >= 120){
            calc();
            day = 0;
        }
    }
}
