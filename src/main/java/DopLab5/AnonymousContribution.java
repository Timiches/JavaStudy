package DopLab5;

public class AnonymousContribution extends BankAccount{

    AnonymousContribution(int money, int percent){
        this.money = money;
        this.bankAccountID = rand.nextInt(100)+1;
        this.percent = percent;
        this.type = 3;
    }

    public void calc() {
        System.out.println("At this month user "+ this.bankAccountID + " has earned " + this.money/100*percent + " and now has "+ (this.money/100*percent+this.money));
        this.money = this.money/100*percent + this.money;
    }

    public void ContributionInfo() {
        System.out.println("3");
    }


    @Override
    public void infoCashOfMontEnd() {
        System.out.println("For the end of calendar mont anonymous contributor with id" + this.bankAccountID + " have " + this.money);
    }
}
