public class Tree {
}

public class TreeMultiSet extends Multiset{
    private Tree tree;

    public TreeMultiSet(){
        this.tree = new Tree();
    }

    public Boolean add(Object item){
        tree.insert(item);
        return true;
    }

    public void remove(Object item){
        tree.delete_item(item);
    }

    public Boolean contains(Object item){
        return tree.contains(item);
    }

    public Boolean isEmpty(){
        return tree.isEmpty();
    }

    public Integer count(Object item){
        return tree.count(item);
    }

    public Integer size(){
        return tree.size();
    }
}