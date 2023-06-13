
public class Most_bank {
  public static int M_sign=0;
  public static Bank[] banks = new Bank[20];


    public static void bank_adder(Bank bank){
        banks[M_sign]=bank;
        M_sign+=1;
    }
}

class Ex{
    public static Branch[] branches = new Branch[20];
    public static int B_sign=0;
    public static void branch_adder(Branch branch){
        branches[B_sign]=branch;
        B_sign+=1;
    }
}


