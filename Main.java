import java.util.Scanner;

public class Main {



    public static void add_Nh(double x_min,double y_min,double x_max,double y_max , String name , TriNH Nh){
        Neighbourhood neighbourhood = new Neighbourhood(x_min,y_min,x_max,y_max,name);
        Nh.insertTrie(name,neighbourhood);
    }
    public static void add_bank(double[] location,String name,Triebank triebank,Kdtree kdtree){
        Bank bank = new Bank(name,location);
        Most_bank.bank_adder(bank);
        triebank.insertTrie(name,bank);
        KNode b = new KNode(location,name,true);
        b.bank=bank;
        kdtree.Main_root = kdtree.insertKnode(kdtree.Main_root,b,0);
    }
    public static void add_branch(String name_bank, String name_branch,double[] location,Kdtree kdtree,Triebank triebank){
        Bank bank = triebank.search(name_bank).bank;
        Branch branch = new Branch(name_branch,name_bank,location);
        Ex.branch_adder(branch);
        KNode branch_kdnode = new KNode(location,name_branch,false);
        branch_kdnode.branch=branch;
        bank.branchnumber++;
        // bank.branches.Main_root = bank.branches.insertKnode(bank.branches.Main_root,branch_kdnode,0);
        bank.branches.insert(branch);
        kdtree.Main_root = kdtree.insertKnode(kdtree.Main_root,branch_kdnode,0 );
    }
    public static void delete_branch(double[] location,Kdtree kdtree,Triebank triebank){
        KNode branch = kdtree.searchKD(kdtree.Main_root, location,0);
        if(branch==null){
            System.out.println("there is no branch here");
        }
        else{
            if(branch.is_Bank){
                System.out.println("it is a Main bank");
            }
            else{
                kdtree.deleteKNode(kdtree.Main_root, location,0);
                Bank bank = triebank.search(branch.branch.bank_name).bank;
                bank.branches.deleteKNode(bank.branches.Main_root,location,0);
            }

        }

    }
    public static void list_B(String name,TriNH triNH,Kdtree kdtree){
        Neighbourhood neighbourhood =  triNH.search(name).nh;
        kdtree.nodesInNH(kdtree.Main_root, neighbourhood,0);
    }

    public static void list_Br(String name,Triebank triebank){
        Bank bank = triebank.search(name).bank;
        bank.printKD(bank.branches.Main_root,bank.branchnumber);
    }

    public static void near_B(double[] location,Kdtree kdtree){
        KNode near_B = kdtree.close_Nh(kdtree.Main_root,location,0 );
        System.out.println("x => "+near_B.location[0]+"y => "+near_B.location[1]);
    }

    public static void near_Br(double[] location,String bank_name,Triebank triebank){
        Bank bank = triebank.search(bank_name).bank;
        KNode near_B = bank.branches.close_Nh(bank.branches.Main_root,location,0 );
        System.out.println("x => "+near_B.location[0]+"y => "+near_B.location[1]);
    }

    public static void availB(double[] location ,double r , Kdtree kdtree ){
        kdtree.nodesInRadius(kdtree.Main_root, location,r,0,0);
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Kdtree kdtree = new Kdtree();
        TriNH trienh = new TriNH();
        Triebank triebank = new Triebank();


        String order = "arman";
        while (!order.equals("Exit")) {

            if (order.equals("arman")) {
                System.out.println("Welcome");
                System.out.println("choose your service");
                System.out.println();
            }
            else if (order.equals("addN")) {
                System.out.println("enter neighbourhood name");
                String name = input.next();
                System.out.println("Enter x y x2 y2 ");
                double x_min = input.nextDouble();
                double y_min = input.nextDouble();
                double x_max = input.nextDouble();
                double y_max = input.nextDouble();
                add_Nh(x_min,y_min,x_max,y_max,name ,trienh);

            }
            else if (order.equals("addB")) {
                System.out.println("Enter Bank's name");
                String name = input.next();
                System.out.println("Enter Bank x y");
                double[] location = new double[2];
                location[0] = input.nextDouble();
                location[1] = input.nextDouble();
                boolean f = false;
                for (int i = 0; i <Most_bank.M_sign ; i++) {
                    if (Most_bank.banks[i].location[0] == location[0] && Most_bank.banks[i].location[1] == location[1]) {
                        f = true;
                        break;
                    }
                }
                boolean u = false;
                for (int i = 0; i <Ex.B_sign ; i++) {
                    if(Ex.branches[i].location[0]==location[0] && Ex.branches[i].location[1]==location[1]){
                        u = true;
                        break;
                    }
                }
                if(f || u){
                    System.out.println("there is a bank in this location");
                }
                else {
                    add_bank(location, name, triebank, kdtree);
                }
            }
            else if (order.equals("addBr")) {
                System.out.println("Enter Bank's name");
                String name_bank = input.next();
                System.out.println("Enter branch's name");
                String name_branch = input.next();
                System.out.println("Enter branch x y");
                double[] location = new double[2];
                location[0] = input.nextDouble();
                location[1] = input.nextDouble();
                boolean f = false;
                for (int i = 0; i <Most_bank.M_sign ; i++) {
                    if (Most_bank.banks[i].location[0] == location[0] && Most_bank.banks[i].location[1] == location[1]) {
                        f = true;
                        break;
                    }
                }
                boolean u = false;
                for (int i = 0; i <Ex.B_sign ; i++) {
                    if(Ex.branches[i].location[0]==location[0] && Ex.branches[i].location[1]==location[1]){
                        u = true;
                        break;
                    }
                }
                if(f || u){
                    System.out.println("there is a bank in this location");
                }
                else {
                    add_branch(name_bank, name_branch, location, kdtree, triebank);
                }

            }
            else if (order.equals("delBr")) {
                System.out.println("Enter Bank x y");
                double[] location = new double[2];
                location[0] = input.nextDouble();
                location[1] = input.nextDouble();
                delete_branch(location,kdtree,triebank);

            }
            else if (order.equals("listB")) {
                System.out.println("Enter Neighborhood Name");
                String name = input.next();
                list_B(name,trienh,kdtree);
            }

            else if (order.equals("listBrs")) {
                System.out.println("Enter Bank Name");
                String name = input.next();
                list_Br(name,triebank);

            }
            else if (order.equals("nearB")) {
                System.out.println("Enter Location x y ");
                double[] location = new double[2];
                location[0] = input.nextDouble();
                location[1] = input.nextDouble();
                near_B(location,kdtree);
            }
            else if (order.equals("nearBr")) {
                System.out.println("Enter Bank Name");
                String name = input.next();
                System.out.println("Enter Location x y");
                double[] location = new double[2];
                location[0] = input.nextDouble();
                location[1] = input.nextDouble();
                near_Br(location,name,triebank);

            }
            else if (order.equals("availB")) {
                System.out.println("Enter Range Center x y ");
                double[] location = new double[2];
                location[0] = input.nextDouble();
                location[1] = input.nextDouble();
                System.out.println("Enter Range Radius");
                double r = input.nextDouble();
                availB(location,r,kdtree);

            }
            else if (order.equals("mostBrs")) {
              int max =0;
              String name="xx";
//                System.out.println(most[1].bank.branchnumber);
                for (int i = 0; i < Most_bank.M_sign ; i++) {
                        if (Most_bank.banks[i].branchnumber > max) {
                            max = Most_bank.banks[i].branchnumber;
                            name = Most_bank.banks[i].name;
                        }
                }
                System.out.println(name);
            }

            System.out.println("addN  => add neighbourhood");
            System.out.println("addB  => add bank");
            System.out.println("addBr => add branch");
            System.out.println("delBr => delete branch");
            System.out.println("listB => All Banks In Neighborhood");
            System.out.println("listBrs => All Branches of Bank");
            System.out.println("nearB => Nearest Bank");
            System.out.println("nearBr => Closest Branch");
            System.out.println("availB => All Banks In Range");
            System.out.println("mostBrs => Bank With Most Branches");
            System.out.println("Exit => Exit");


            order = input.next();
        }
    }

}
