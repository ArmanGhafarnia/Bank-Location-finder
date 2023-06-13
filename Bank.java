public class Bank {
    String name;
    double[] location ;
    int branchnumber=0;
    Kdtree branches ;
    int sanjesh=0;

    public Bank(String name , double[] location  ){
        this.name=name;
        this.location=location;
        this.branches=new Kdtree();
    }

    void printKD(KNode node,int branchnumber) {
        if (node == null) {
            return;
        }
            if(sanjesh<=branchnumber && sanjesh>0) {
                System.out.println("{ " + node.location[0] + " , " + node.location[1] + " }");
            }
            else if(sanjesh!=0){
                return;
            }
        sanjesh++;
        printKD(node.left,branchnumber);
        printKD(node.right,branchnumber);
    }

}
