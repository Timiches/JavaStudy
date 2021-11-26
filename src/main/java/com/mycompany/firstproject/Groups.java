package com.mycompany.firstproject;
import java.util.regex.*;

import java.util.Arrays; 
import java.util.regex.Matcher; 
import java.util.regex.Pattern;

public class Groups {
    public static void main(String[] args) {
        System.out.println("======================================================"
                + "\nQuestion #1\nDoes the word 'abcdefghijklmnopqrstuv18340' matches this pattern?");
        question_1("abcdefghijklmnopqrstuv18340");
        System.out.println("And what about the word 'abcdefghijklmnoasdfasdpqrstuv18340'?");
        question_1("abcdefghijklmnoasdfasdpqrstuv18340");
        
        //---------------------------------------------------------------------------------
        
        System.out.println("======================================================"
                + "\nQuestion #2\nDoes the word 'e02fd0e4-00fd-090A-ca30-0d00a0038ba0' the GUID?");
        GUID_checker("e02fd0e4-00fd-090A-ca30-0d00a0038ba0");
        System.out.println("Does the word 'e02fd0e400fd090Aca300d00a0038ba0' the GUID?");
        GUID_checker("e02fd0e400fd090Aca300d00a0038ba0");
        System.out.println("Does the word 'e02fd0e400fd-090A-ca300d00a0038ba0' the GUID?");
        GUID_checker("e02fd0e400fd-090A-ca300d00a0038ba0");
        
        //---------------------------------------------------------------------------------
        
        System.out.println("======================================================"
                + "\nQuestion #3\nDoes the word 'ae-dc-ca-56-76-54' the MAC adress?");
        MAC_address_checker("ae-dc-ca-56-76-54");
        System.out.println("Does the word '01-23-45-67-89-Az' the MAC adress?");
        MAC_address_checker("01-23-45-67-89-Az");
        
        //---------------------------------------------------------------------------------
        
        System.out.println("======================================================"
                + "\nQuestion #4\nDoes the word 'http://www.zcontest.ru' URL adress?");
        URL_address("http://www.zcontest.ru");
        System.out.println("Does the word 'http://zcontest.ru' URL adress?");
        URL_address("http://zcontest.ru");
        System.out.println("Does the word 'Just Text' URL adress?");
        URL_address("Just Text");
        System.out.println("Does the word 'http://a.com' URL adress?");
        URL_address("http://a.com");
        System.out.println("Does the word 'httpswa://abvgde.com' URL adress?");
        URL_address("httpswa://abvgde.com");
        System.out.println("Does the word 'https://-abvgde.com' URL adress?");
        URL_address("https://-abvgde.com");
        
        //---------------------------------------------------------------------------------
        
        System.out.println("======================================================"
                + "\nQuestion #5\nDoes the word '#FFFFFF' the color ID?");
        RGB_id("#FFFFFF");
        System.out.println("Does the word '#FF3421' the color ID?");
        RGB_id("#FF3421");
        System.out.println("Does the word '#00ff00' the color ID?");
        RGB_id("#00ff00");
        System.out.println("Does the word '232323' the color ID?");
        RGB_id("232323");
        System.out.println("Does the word 'f#fddee' the color ID?");
        RGB_id("f#fddee");
        System.out.println("Does the word '#fd2' the color ID?");
        RGB_id("#fd2");
        
        //---------------------------------------------------------------------------------
        
        System.out.println("======================================================"
                + "\nQuestion #6\nDoes the date '29/02/2000' correct?");
        Date_checker("29/02/2000");
        System.out.println("Does the date '30/04/2003' correct?");
        Date_checker("30/04/2003");
        System.out.println("Does the date '01/01/2003' correct?");
        Date_checker("01/01/2003");
        System.out.println("Does the date '29/02/2001' correct?");
        Date_checker("29/02/2001");
        System.out.println("Does the date '30-04-2003' correct?");
        Date_checker("30-04-2003");
        System.out.println("Does the date '1/1/1899' correct?");
        Date_checker("1/1/1899");
        System.out.println("Does the date '08/11/2933' correct?");
        Date_checker("08/11/2933");
        System.out.println("Does the date '01/06/1703' correct?");
        Date_checker("01/06/1703");
        System.out.println("Does the date '34/04/2003' correct?");
        Date_checker("34/04/2003");
        System.out.println("Does the date '31/02/2006' correct?");
        Date_checker("31/02/2006");
        System.out.println("Does the date '31/03/2006' correct?");
        Date_checker("31/03/2006");
        
        //---------------------------------------------------------------------------------
        
        System.out.println("======================================================"
                + "\nQuestion #7\nDoes the word 'mail@mail.ru' Email address?");
        Email("mail@mail.ru");
        System.out.println("Does the word 'valid@megapochta.com' Email address?");
        Email("valid@megapochta.com");
        System.out.println("Does the word 'bug@@@com.ru' Email address?");
        Email("bug@@@com.ru");
        System.out.println("Does the word '@val.ru' Email address?");
        Email("@val.ru");
        System.out.println("Does the word 'Just Text2' Email address?");
        Email("Just Text2");
        
        //---------------------------------------------------------------------------------
        
        System.out.println("======================================================"
                + "\nQuestion #8\nDoes the word '127.0.0.1' IP address?");
        IP_address("127.0.0.1");
        System.out.println("Does the word '255.255.255.0' IP address?");
        IP_address("255.255.255.0");
        System.out.println("Does the word '1300.6.7.8' IP address?");
        IP_address("1300.6.7.8");
        System.out.println("Does the word 'abc.def.gha.bcd' IP address?");
        IP_address("abc.def.gha.bcd");
        
        //---------------------------------------------------------------------------------
        
        System.out.println("======================================================"
                + "\nQuestion #9\nDoes the word 'C00l_Pass' good password?");
        Password("C00l_Pass");
        System.out.println("Does the word 'Supperpas1' good password?");
        Password("Supperpas1");
        System.out.println("Does the word 'Cool_pass' good password?");
        Password("Cool_pass");
        System.out.println("Does the word 'C00l' good password?");
        Password("C00l");
        System.out.println("Does the word 'i6543895400l' good password?");
        Password("i6543895400l");
        System.out.println("Does the word '1hgrUs83200l' good password?");
        Password("1hgrUs83200l");
        
        //---------------------------------------------------------------------------------
        
        System.out.println("======================================================"
                + "\nQuestion #10\n'123456'");
        SixNumWord("123456");
        System.out.println("'234567'");
        SixNumWord("234567");
        System.out.println("'12345'");
        SixNumWord("12345");
        System.out.println("'1234567'");
        SixNumWord("1234567");
        System.out.println("'034567'");
        SixNumWord("034567");
        
        //---------------------------------------------------------------------------------
        
        System.out.println("======================================================"
                + "\nQuestion #11\n'23.78 USD'");
        Shekeli("23.78 USD");
        System.out.println("'22 UDD'");
        Shekeli("22 UDD");
        System.out.println("'22145 RUR'");
        Shekeli("22145 RUR");
        System.out.println("'0.002 USD'");
        Shekeli("0.002 USD");
        System.out.println("'23.7 EU'");
        Shekeli("23.7 EU");
        System.out.println("'23 RUR'");
        Shekeli("23 RUR");
        
        //---------------------------------------------------------------------------------
        
        System.out.println("======================================================"
                + "\nQuestion #12\n'(3+5)-9*4'");
        Math("(3+5)-9*4");
        System.out.println("'3/5-9*4'");
        Math("3/5-9*4");

        
        //---------------------------------------------------------------------------------
        
        System.out.println("======================================================"
                + "\nQuestion #13\n'(3+5)-9*4'");
        Math2("(3+5)–9*4");
        System.out.println("'((3+5)–9*4'");
        Math2("((3+5)–9*4");
        System.out.println("'3+5-9*4'");
        Math2("3+5-9*4");
    }
    
    
    /*public static void mymatches(String regex, 
            String input) {
    	  Pattern pattern = Pattern.compile(regex);
    	  Matcher matcher = pattern.matcher(input);
    	  if(matcher.matches()) {
            System.out.println("First group: " 
                + matcher.group(1));
            System.out.println("Second group: " 
                + matcher.group(2));
        } else
            System.out.println("nothing");
        System.out.println();
    }*/
    
    public static void question_1 (String args){
        Pattern p = Pattern.compile("abcdefghijklmnopqrstuv18340");
        Matcher m = p.matcher(args);
        boolean b = m.matches();
        System.out.println(b);
    }
    
    public static void GUID_checker(String guid){
        Pattern p = Pattern.compile("(\\w{8})-(\\w{4})-(\\w{4})-(\\w{4})-(\\w{12})");
        Matcher m = p.matcher(guid);
        boolean b = m.matches();
        System.out.println(b);
    }
    
    public static void MAC_address_checker(String mac_address){
        Pattern p = Pattern.compile("([0-9[a-f]]{2})-([0-9[a-f]]{2})-([0-9[a-f]]{2})-([0-9[a-f]]{2})-([0-9[a-f]]{2})-([0-9[a-f]]{2})");
        Matcher m = p.matcher(mac_address);
        boolean b = m.matches();
        System.out.println(b);
    }
    
    public static void URL_address(String adr){
        Pattern pattern = Pattern.compile("(http?|https?)://(www.)?([a-z A-Z 0-9]{1}\\w\\w+[a-z A-Z 0-9]{1}).([a-z]+)");
        Matcher matcher = pattern.matcher(adr);
        boolean b = matcher.matches();
        System.out.println(b);
    }
    
    public static void RGB_id(String color_id){
        Pattern p = Pattern.compile("#([0-9[a-f[A-F]]]{6})");
        Matcher m = p.matcher(color_id);
        boolean b = m.matches();
        System.out.println(b);
    }
//====================================================================================================       
    public static void Date_checker(String date) { 
        boolean b;
        Pattern pattern = Pattern.compile("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((16|17|18|19|[2-9][0-9])\\d\\d)"); 
        Matcher matcher = pattern.matcher(date);
        if (matcher.matches()) {
            b = true;
            matcher.reset();
        
            if (matcher.find()){
               String day = matcher.group(1);
               String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));
                
                if ("31".equals(day)& month.equals("01") | month.equals("03") |month.equals("05") |month.equals("07") |month.equals("08") |month.equals("10") |month.equals("12")){
                 b = true;
                 /*
                 Гoд виcoкocный, ecли oн дeлитcя нa чeтыpe бeз ocтaткa, 
                 нo ecли oн дeлитcя нa 100 бeз ocтaткa, этo нe виcoкocный гoд. 
                 Oднaкo, ecли oн дeлитcя бeз ocтaткa нa 400, этo виcokocный гoд.
                 Taкиm oбpaзom, 2000 г. являeтcя ocoбыm виcoкocныm гoдoм, кoтopый бывaeт лишь paз в 400 лeт.
                 */
                }else if("02".equals(month)){
                    if(year % 4 == 0){
                        b = true;
                    } else if(year % 4 == 0 & year % 100 == 0){
                        b = false;
                    } else if(year % 4 == 0 & year % 100 == 0 & year % 400 == 0){
                        b = true;
                    } else {
                        b = false;
                    }
                } else{
                   b = true;
                }
            }
        } else b = false;
        System.out.println(b);
    }
//====================================================================================================        
    public static void Email(String address){
        Pattern p = Pattern.compile("(\\w+)@(\\w+).(\\w{1,3})");
        Matcher m = p.matcher(address);
        boolean b = m.matches();
        System.out.println(b);
    }
    
    public static void IP_address(String ip){
        Pattern p = Pattern.compile("([0-9]{1,3}).([0-9]{1,3}).([0-9]{1,3}).([0-9]{1,3})");
        Matcher m = p.matcher(ip);
        boolean b = m.matches();
        System.out.println(b);
    }
    
    public static void Password(String pw){
        Pattern p = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,20}");
        Matcher m = p.matcher(pw);
        boolean b = m.matches();
        System.out.println(b);
        
        /*boolean b = true;
        Pattern pattern_base = Pattern.compile("[0-9[a-z[A-Z[!@#$%^&*()_]]]]{8,30}");
        Pattern patern_A = Pattern.compile("[A-Z]+");
        Pattern patern_a = Pattern.compile("[a-z]+");
        Pattern patern_1 = Pattern.compile("[0-9]+");
        Matcher matcher_base = pattern_base.matcher(pw);
        Matcher matcher_A = patern_A.matcher(pw);
        Matcher matcher_a = patern_a.matcher(pw);
        Matcher matcher_1 = patern_1.matcher(pw);
        
        if(matcher_base.matches()){
            
        } else b = false;
        System.out.println(b);*/
    }
    
    public static void SixNumWord(String wrd){
        Pattern p = Pattern.compile("[1-9][0-9]{5}");
        Matcher m = p.matcher(wrd);
        boolean b = m.matches();
        System.out.println(b);
    }
    
    public static void Shekeli(String money){
        Pattern p = Pattern.compile("[1-9]([0-9]*)(.[0-9]{1,2})? (USD|RUR|EU)");
        Matcher m = p.matcher(money);
        boolean b = m.matches();
        System.out.println(b);
    }
    
    public static void Math(String task){
        //Pattern p = Pattern.compile("[0-9[-*/[([)]]]]*");
        Pattern p2 = Pattern.compile("\\+");
        //Matcher m = p.matcher(task);
        Matcher m2 = p2.matcher(task);
        
        boolean b;
        
        if(/*m.matches() &*/ m2.find()){
            b = true;
        }else b = false;
        
        System.out.println(b);
    }
    
    public static void Math2(String task){
        //Тупой вариант
        
        //Pattern pMain = Pattern.compile("([0-9][+-=/*])");
        //Matcher mMain = pMain.matcher(task);
        
        //Pattern pSlave = Pattern.compile("[[0-9[-*+]]\\(\\)]");
//        Pattern pSlave = Pattern.compile("[0-9[-*+]]*");
//        Matcher mSlave = pSlave.matcher(task);
//        
//        while (mSlave.find()) {
//            System.out.println(task.substring(mSlave.start(), mSlave.end()));
//        }
//        boolean b = mSlave.matches();
//        System.out.println(b);
        //Вообще для дегродов
        
        /*Pattern p = Pattern.compile("[0-9]?");
        Matcher m = p.matcher(task);
        boolean b = m.matches();
        System.out.println(b);*/
        
        //Болеее прошареный варик (который не работает, к слову)
        
        /*Pattern pMain = Pattern.compile("([0-9][\\+\\-\\=\\/\\*])+");
        Matcher mMain = pMain.matcher(task);
        
        Pattern pRight = Pattern.compile("\\)");
        Matcher mRight = pRight.matcher(task);
        
        Pattern pLeft = Pattern.compile("\\(");
        Matcher mLeft = pLeft.matcher(task);
        
        boolean b;
        String check;
        
        if(mMain.matches()){
            b = true;
            check = ("Good thing");
            while(mRight.find()){
                if (mRight.matches()){
                    while (mLeft.find()){
                        if (mLeft.matches()){
                            b = true;
                        } else b = false;
                    }
                }
                
            }
        } else {b = false; check = ("Bad thing");}
        
        System.out.println(b);
        System.out.println(check);*/
        
        //Рабочий
        
        int count = 0;
        int count2 = 0;
        
        Pattern p = Pattern.compile("[(]");
        Pattern p2 = Pattern.compile("[)]");
        
        Matcher m = p.matcher(task);
        Matcher m2 = p2.matcher(task);
        
        while(m.find()) count++;
        while(m2.find()) count2++;
        
        if(count == count2 & count != 0 & count2 != 0) System.out.println("true");
        else System.out.println("false");
    }
}
