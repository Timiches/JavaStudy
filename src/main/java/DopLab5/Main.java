package DopLab5;
/**
 * Задача № 5. Вариант 2
 *
 *
 *
 * Написать программу, реализующую иерархию классов банковских счетов (BankAccont).
 * Должны быть смоделировано поведение следующих видов счетов:
 *
 * Простой вклад  Каждый месяц на остаток начисляется постоянный процент, определяемый банком  (по умолчанию – 6%).
 *
 * Шестимесячный вклад В конце каждого шестого месяца на остаток начисляется процент для шестимесячного вклада (по умолчанию – 32%). В случае досрочного снятия любой суммы срок отсчитывается от момента снятия.
 *
 * Анонимный случайный вклад Каждый месяц на остаток начисляется процент в указанных пределах, (по умолчанию – от 2% до 8%). В качестве имени вкладчика используется порядковый его номер.
 *
 * Вклад с премией Каждый месяц на остаток начисляется постоянный процент, определяемый банком (по умолчанию – 5%).
 * При этом, если в течении 4 месяцев не было снятия денег, начисляется премия определённого размера (по умолчанию – 3%).
 *
 * Любой вклад должен позволять довложение средств в произвольный момент времени, снятие средств в пределах остатка, получения справки о состоянии счета.
 * Любой тип вклада должен позволять узнать и изменить свои условия. Любой вклад должен хранить имя вкладчика и номер счета.
 * Считать (и реализовать в программе), что для единообразного доступа к вкладам существует массив базового класса,
 * в котором хранятся реальные объекты-вклады. Для  подсчета  процентов для каждого указателя в конце месяца вызывается функция calc без аргументов.
 * Вклады могут создаваться в разные месяцы, причем каждый сам хранит всю  необходимую информацию о  временных  интервалах.
 * Предусмотреть возможность вывода количества вкладов как  каждого типа в отдельности, так и всех вкладов.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<BankAccount> accounts = new ArrayList<>();

    static int variable;
    static int percent;
    static int money;
    static String name;
    static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    static Scanner in = new Scanner(System.in);

    public static void main(String args[]) throws InterruptedException {

        int days[] = {31,29,31,30,31,30,31,30,31,31,30,31};
        String month[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

        accounts.add(new SimpleContribution(1000, "aaaa"));
        accounts.add(new SixMontContribution(1000, "bbbb"));
        accounts.add(new AnonymousContribution(1000, 3));
        accounts.add(new PremiumContribution(1000, "dddd", 4));

        int d = 0, m = 0;

        while (true) {
            try {
                if(console.ready()){
                    System.out.println("What do you want?");
                    System.out.println("1. Sign in");
                    System.out.println("2. Create account");
                    System.out.println("3. See all contributions");
                    variable = in.nextInt();


                    switch (variable){
                        case 1:

                            System.out.println("Enter name");
                            name = console.readLine();
                            if(checkContributor(name) == true){
                                int i = checkContributorInt(name);
                                boolean check = true;

                                while(check == true) {
                                    System.out.println("Chose an option");
                                    System.out.println("1. Add money");
                                    System.out.println("2. Remove money");
                                    System.out.println("3. Info");
                                    System.out.println("4. Contribution info");
                                    System.out.println("5. Change contribution type");
                                    System.out.println("6. Exit");

                                    variable = in.nextInt();
                                    switch (variable) {
                                        case 1:
                                            System.out.println("How much money");
                                            variable = in.nextInt();
                                            accounts.get(i).addCash(variable);
                                            break;
                                        case 2:
                                            System.out.println("How much money");
                                            variable = in.nextInt();
                                            accounts.get(i).removeCash(variable);
                                            break;
                                        case 3:
                                            accounts.get(i).infoCash();
                                            break;
                                        case 4:
                                            accounts.get(i).ContributionInfo();
                                            break;
                                        case 5:
                                            money = accounts.get(i).money;
                                            accounts.remove(i);

                                            System.out.println("Chose contribution type");
                                            System.out.println("1. Simple contribution");
                                            System.out.println("2. Six-Mont Contribution");
                                            System.out.println("3. Anonymous Contribution");
                                            System.out.println("4. Premium Contribution");
                                            variable = in.nextInt();


                                            switch (variable){
                                                case 1:
                                                    accounts.add(new SimpleContribution(money, name));
                                                    break;
                                                case 2:
                                                    accounts.add(new SixMontContribution(money, name));
                                                    break;
                                                case 3:
                                                    System.out.println("Enter your percent");
                                                    percent = in.nextInt();
                                                    accounts.add(new AnonymousContribution(money, percent));
                                                    break;
                                                case 4:
                                                    System.out.println("Enter your percent");
                                                    percent = in.nextInt();
                                                    accounts.add(new PremiumContribution(money, name, percent));
                                                    break;
                                            }
                                            break;
                                        case 6:
                                            check = false;
                                        default:
                                            break;
                                    }

                                }
                            } else{
                                System.out.println("Wrong name");
                            }
                            break;
                        case 2:
                            System.out.println("Chose contribution type");
                            System.out.println("1. Simple contribution");
                            System.out.println("2. Six-Mont Contribution");
                            System.out.println("3. Anonymous Contribution");
                            System.out.println("4. Premium Contribution");
                            variable = in.nextInt();


                            switch (variable){
                                case 1:
                                    System.out.println("Enter name");
                                    name = console.readLine();
                                    System.out.println("Enter amount of money");
                                    money = in.nextInt();
                                    accounts.add(new SimpleContribution(money, name));
                                    System.out.println("Success");
                                    break;
                                case 2:
                                    System.out.println("Enter name");
                                    name = console.readLine();
                                    System.out.println("Enter amount of money");
                                    money = in.nextInt();
                                    accounts.add(new SixMontContribution(money, name));
                                    System.out.println("Success");
                                    break;
                                case 3:
                                    System.out.println("Enter amount of money");
                                    money = in.nextInt();
                                    System.out.println("Enter your percent");
                                    percent = in.nextInt();
                                    accounts.add(new AnonymousContribution(money, percent));
                                    System.out.println("Success");
                                    break;
                                case 4:
                                    System.out.println("Enter name");
                                    name = console.readLine();
                                    System.out.println("Enter amount of money");
                                    money = in.nextInt();
                                    System.out.println("Enter your percent");
                                    percent = in.nextInt();
                                    accounts.add(new PremiumContribution(money, name, percent));
                                    System.out.println("Success");
                                    break;
                            }
                            break;
                        case 3:
                            for (BankAccount account : accounts) {
                                account.infoCash();
                            }
                            break;
                        default:
                            break;
                    }


                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            d++;
            System.out.println("Today: " + month[m] + " " + d);
            if (d >= days[m]) {

                for(int i = 0; i<accounts.size(); i++){
                    accounts.get(i).infoCashOfMontEnd();
                    accounts.get(i).check();
                }

                d = 0;
                m++;
                if (m >= 12)
                    m = 0;
            }

            for (BankAccount account : accounts) {
                account.check();
            }

            Thread.sleep(1000);
        }

    }

    static boolean checkContributor(String name){
        for (BankAccount account : accounts) {
            if (name.equals(account.contributor)) {
                return true;
            }
        }
        return false;
    }

    static int checkContributorInt(String name){
        for(int i = 0; i<accounts.size(); i++){
            if(name.equals(accounts.get(i).contributor)){
                return i;
            }
        }
        return 0;
    }


}
