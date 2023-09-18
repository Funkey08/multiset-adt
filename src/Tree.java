import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tree<T> {
    T root;
    List<Tree<T>> subtrees;

    public Tree(T root) {
        this.root = root;
        this.subtrees = new ArrayList<>();
    }

    public boolean is_empty() {
//        Return whether this tree is empty.
        return (this.root == null);
    }

    public int length() {
//        Return the number of items contained in this tree.
        if (this.is_empty()) return 0;
        else {
            int size = 1;
            for (Tree<T> subtree: this.subtrees) {
                size += subtree.length();
            }
            return size;
        }
    }

    public int count(T item) {
//        Return the number of occurrences of <item> in this tree.
        if (this.is_empty()) {
            return 0;
        }
        else {
            int num = 0;
            if (this.root == item) {
                num += 1;
            }
            for (Tree<T> subtree: this.subtrees) {
                num += subtree.count(item);
            }
            return num;
        }
    }

    private String strIndented() {
//        Overloaded version of strIndented.
        return this.strIndented(0);
    }
    private String strIndented(int depth) {
//        Return an indented string representation of this tree.
//        The indentation level is specified by the <depth> parameter.
        if (this.is_empty()) return "";
        else {
            String s = new String(new char[depth]).replace('\0', ' ') + this.root.toString() + "\n";
            for (Tree<T> subtree: subtrees) {
                s += subtree.strIndented(depth + 1);
            };
            return s;
        }
    }

    private boolean equal(Tree<T> other) {
//        Return whether <this> and <other> are equal.
        if (this.is_empty() && other.is_empty()) return true;
        else if (this.is_empty() || other.is_empty()) return false;
        else {
            if (this.root != other.root) {
                return false;
            }
            if (this.length() != other.length()) {
                return false;
            }
            return this.subtrees == other.subtrees;
        }
    }

    private ArrayList<T> leaves() {
//        Return a list of all the leaf items in the tree.
        if (this.is_empty()) return new ArrayList<>();
        else if (this.subtrees.isEmpty()) {
            ArrayList<T> leaves = new ArrayList<>();
            leaves.add(this.root);
            return leaves;
        }
        else {
            ArrayList<T> leaves = new ArrayList<>();
            leaves.add(this.root);
            for (Tree<T> subtree: subtrees) {
                leaves.addAll(subtree.leaves());
            }
            return leaves;
        }

    }
// -------------------------------------------------------------------------
// Mutating methods
// -------------------------------------------------------------------------
    private void deleteRoot() {
//        Remove the root item of this tree.
//        Precondition: this tree has at least one subtree.
        if (this.subtrees.isEmpty()) this.root = null;
        else {
            Tree<T> chosen_subtree = this.subtrees.remove(0);
            this.root = chosen_subtree.root;
            this.subtrees.addAll(chosen_subtree.subtrees);
        }
    }

    private void insert(T item) {
//        Insert <item> into this tree using the following algorithm.
//
//        1. If the tree is empty, <item> is the new root of the tree.
//        2. If the tree has a root but no subtrees, create a
//        new tree containing the item, and make this new tree a subtree
//        of the original tree.
//        3. Otherwise, pick a random number between 1 and 3 inclusive.
//                - If the random number is 3, create a new tree containing
//                  the item, and make this new tree a subtree of the original.
//                - If the random number is a 1 or 2, pick one of the existing
//        subtrees at random, and *recursively insert* the new item
//        into that subtree.
        if (this.is_empty()) {
            this.root = item;
        }
        else if (this.subtrees.isEmpty()) {
            ArrayList<Tree<T>> subtrees = new ArrayList<>();
            subtrees.add(new Tree<>(item));
            this.subtrees = subtrees;
        }
        else {
            Random random = new Random();
            if (random.nextInt(1, 3) == 3) {
                this.subtrees.add(new Tree<>(item));
            }
            else {
                int subtree_index = random.nextInt(0, this.subtrees.size() - 1);
                this.subtrees.get(subtree_index).insert(item);
            }
        }
    }


    public static void main(String[] args) {
        // FOR TESTING:
//        Tree<Integer> subtree1 = new Tree<Integer>(1);
//        Tree<Integer> subtree2 = new Tree<Integer>(2);
//        Tree<Integer> subtree3 = new Tree<Integer>(4);
//
//        Tree<Integer> tree = new Tree<Integer>(3);
//        tree.subtrees.add(subtree1);
//        tree.subtrees.add(subtree2);
//        subtree2.subtrees.add(subtree3);
    }
}