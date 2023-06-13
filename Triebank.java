public class Triebank {

    Trinode root ;

    void insertTrie(String name, Bank bank) {
        if(root==null){
            root=new Trinode();
        }

        Trinode temp = root ;
        for (int i = 0; i < name.length(); i++) {
           int  code = name.charAt(i)-'a';
            if (temp.children[code] == null) { //age khali bud
                temp.children[code] = new Trinode(bank);
            }
            temp = temp.children[code]; //mizare roo akharish
        }
        temp.end=true;
    }

    Trinode search(String key) {
        Trinode temp = root;

        for (int i = 0; i < key.length(); i++) {
           int code = key.charAt(i)-'a';
            if (temp.children[code] == null)
                return null;
            temp = temp.children[code];
        }
        if (temp.end) {
            return temp;
        } else
            return null;
    }


}
