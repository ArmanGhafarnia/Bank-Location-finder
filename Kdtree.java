public class Kdtree {
    KNode Main_root = new KNode();

    void printKD(KNode node) {
        if (node == null) {
            return;
        }
        else {
            System.out.println("{ " + node.location[0] + " , " +node.location[1] + " }");
        }
        printKD(node.left);
        printKD(node.right);
    }

    public KNode insertKnode(KNode root, KNode voroodi, int Dp){
        if (root == null) {                        // derakht khali ast va yek node miszim va be onvan rishe migozarim
            return voroodi;
        }
        int zojfard = Dp%2;
        Dp++;
        if (voroodi.location[zojfard] >= (root.location[zojfard])) {
            root.right = insertKnode(root.right, voroodi, Dp);
        }
        else  {
            root.left = insertKnode(root.left,voroodi, Dp);
        }

        return root;
    }

    void insert(Branch voroodi) {
        double[] location;
        location = new double[]{voroodi.location[0], voroodi.location[1]};
        Main_root = insert_branch(Main_root, voroodi,location, 0);
    }

    private KNode insert_branch(KNode root,  Branch voroodi , double[] location, int Dp) {
        if (root == null) {
            return new KNode(location, voroodi.name,false);
        }
        int zojfard = Dp % 2;
        if (location[zojfard] < root.location[zojfard]) {
            root.left = insert_branch(root.left, voroodi,location, Dp + 1);
        } else {
            root.right = insert_branch(root.right,  voroodi,location, Dp + 1);
        }
        return root;
    }



     KNode searchKD(KNode root, double location[], int Dp) {
        if (root == null) {
            return null; //same location with root
        }
        int sign = 1;
        for (int i = 0; i < 2; ++i) {
            if (root.location[i] != location[i]) {
                sign = 0;
            }
        }
        if (sign==1) {
            return root;
        }

        int zojfard = Dp % 2;
        Dp+=1;
        if (location[zojfard] < root.location[zojfard]) {
            return searchKD(root.left, location, Dp);
        }

        return searchKD(root.right, location, Dp);
    }



     KNode deleteKNode(KNode root, double location[], int Dp) {
        if (root == null)
            return null;
        int sign = 1;
        int zojfard = Dp % 2;
        KNode min;
        for (int i = 0; i < 2; ++i) {
            if (root.location[i] != location[i]) {
                sign=0;
            }
        }
        if (sign==1) {
            if (root.right != null) {
                min = minimum(root.right, zojfard, Dp);
                for (int i = 0; i < 2; i++)
                    root.location[i] = min.location[i];
                root.name = min.name;
                root.right = deleteKNode(root.right, min.location, Dp + 1); // age min tah nabashe
            } else if (root.left != null) {
                min = minimum(root.left, zojfard, Dp);
                for (int i = 0; i < 2; i++)
                    root.location[i] = min.location[i];
                root.name = min.name;
                root.right = deleteKNode(root.left, min.location, Dp + 1);
            } else {
                root = null;
                return null;
            }
            return root;
        }
        Dp+=1;
        if (location[zojfard] > root.location[zojfard])
            root.right = deleteKNode(root.right, location, Dp);

        else
            root.left = deleteKNode(root.left, location, Dp);

        return root;
    }

     void nodesInNH(KNode root, Neighbourhood loc, int Dp) {
        if (root == null) {
            return;
        }
        if(root.location[1] > loc.y_min && root.location[1] < loc.y_max) {
            if (root.location[0] > loc.x_min && root.location[0] < loc.x_max) {
                System.out.println(root.name + " in {" + root.location[0] + "," + root.location[1] + "}\n");
            }
        }
        if (root.left != null ) {
            if(((Dp % 2 == 0 && root.location[0] > loc.x_min) || (Dp % 2 == 1 && root.location[1] > loc.y_min))) {
                nodesInNH(root.left, loc, Dp + 1);
            }
        }
        if (root.right != null) {
            if(((Dp % 2 == 0 && root.location[0] < loc.x_max) || (Dp % 2 == 1 && root.location[1] < loc.y_max))) {
                nodesInNH(root.right, loc, Dp + 1);
            }
        }
    }


     void nodesInRadius(KNode root, double[] location, double radius, int Dp , int sign) {
        double[] point = root.location;
        double rad = (point[0] - location[0]) * (point[0] - location[0]) + (point[1] - location[1]) * (point[1] - location[1]);
        if (rad <= (radius * radius) && sign==1) {
            System.out.println(root.name + " is in " + point[0] + "," + point[1]);
        }
        if (root.left != null) {
            if(((Dp % 2 == 0 && (point[0] > location[0] - radius)) || (Dp % 2 == 1 && (point[1] < location[1] + radius)))) {
                nodesInRadius(root.left, location, radius, Dp + 1, 1);
            }
        }
        if (root.right != null) {
            if(((Dp % 2 == 0 && (point[0] < location[0] + radius)) || (Dp % 2 == 1 && (point[1] > location[1] - radius)))) {
                nodesInRadius(root.right, location, radius, Dp + 1, 1);
            }
        }
    }

     KNode close_Nh(KNode root, double[] location, int Dp) {

        if (root == null){
            return null;
        }
        int zojfard = Dp%2;
        KNode first_branch,second_branch;
        first_branch=null;
        second_branch=null;
        if (location[zojfard] < root.location[zojfard]) {
            second_branch = root.right;
            first_branch = root.left;
        }
        if(location[zojfard] >= root.location[zojfard]){
            second_branch = root.left;
            first_branch = root.right;
        }

        KNode temp = close_Nh(first_branch, location, Dp + 1);
        KNode shortest = near(temp,	root, location);
         int whole = 0;
         int hasel =0;
         for (int i = 0; i < 2; i++) {
             if(location[i]-shortest.location[i] >0 ){
                 hasel = (int) (location[i]-shortest.location[i]);
             }
             if(location[i]-shortest.location[i] <=0){
                 hasel = (int) (shortest.location[i]-location[i]);
             }
             whole = (hasel*hasel) + whole;
         }
         int radius = whole;
        double D = location[zojfard] - root.location[zojfard];
        if (radius >= D * D) {
            temp = close_Nh(second_branch, location, Dp + 1);
            shortest = near(temp, shortest, location);
        }
        return shortest;
    }
    KNode near(KNode first, KNode second, double[] location) {
        if (second == null) {
            return first;
        }
        if (first == null){
            return second;
        }
        int whole = 0;
        int hasel =0;
        for (int i = 0; i < 2; i++) {
            if(first.location[i]-location[i] >0 ){
                hasel = (int) (first.location[i]-location[i]);
            }
            if(first.location[i]-location[i] <=0){
                hasel = (int) (location[i]-first.location[i]);
            }
            whole = whole + (hasel*hasel);
        }
        int distance1 = whole;

        int whole_2 = 0;
        int hasel_2 =0;
        for (int i = 0; i < 2; i++) {
            if(second.location[i]-location[i] >0 ){
                hasel_2 = (int) (second.location[i]-location[i]);
            }
            if(second.location[i]-location[i] <=0){
                hasel_2 = (int) (location[i]-second.location[i]);
            }
            whole_2 = whole_2+(hasel_2*hasel_2);
        }
        int distance2 = whole_2;


        if (distance1 < distance2) {
            return first;
        }
        if(distance2 <= distance1 ) {
            return second;
        }
        return null;
    }

    KNode minimum(KNode root, int dm, int Dp) {
        if (root == null)
            return null;

        int zojfard = Dp % 2;
        Dp++;
        if (zojfard == dm)
        {
            if (root.left == null)
                return root;
            return minimum(root.left, dm, Dp);
        }
        KNode first = root;
        KNode second = minimum(root.left,dm,Dp);
        KNode third = minimum(root.right,dm,Dp);
        KNode tmp = first;
        if (second != null && second.location[dm] < tmp.location[dm])
            tmp = second;
        if (third != null && third.location[dm] < tmp.location[dm])
            tmp = third;
        return tmp;
    }

}


