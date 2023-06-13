public class KNode {
    String name;
    boolean is_Bank;
    double[] location = new double[2];
    KNode left, right;
    Bank bank;
    Branch branch;
    public KNode(){

    }
    public  KNode (double[] arr, String n,boolean bank) {
        this.name = n;
        this.is_Bank=bank;
        this.left = null;
        this.right = null;
        for (int i = 0; i < 2; i++) {
            this.location[i] = arr[i];
        }
    }
      KNode (double[] arr, String n) {
        this.name = n;
        this.left = null;
        this.right = null;
        for (int i = 0; i < 2; i++) {
            this.location[i] = arr[i];
        }
    }
}
