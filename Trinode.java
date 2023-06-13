public class Trinode {
    Trinode[] children = new Trinode[26]; //26 letters
    Bank bank;
    Neighbourhood nh;
    boolean end =false;
    public void make_null(){
        for (int i = 0; i <children.length ; i++) {
            children[i]=null;
        }
    }
    public Trinode(){

    }
    public Trinode( Bank bank){
        this.bank = bank;
        make_null();
    }
    public Trinode( Neighbourhood nh){
        this.nh = nh;
        make_null();
    }

}
