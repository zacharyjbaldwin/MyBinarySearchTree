/*
 * Zachary Baldwin
 * ZJB190000
 * zjb190000@utdallas.edu
 * CS3345.004
 * Assignment #4
 */

package project2;

public class MySearchTree<T extends Comparable<? super T>>
{
    private BinaryNode<T> root;
    public MySearchTree() { root = null; }
        
    // Class for the node in the binary search tree.
    public static class BinaryNode<T>
    {
	T element;
	BinaryNode<T> left, right;
	
	BinaryNode(T element) { this(element, null, null); }
	
	BinaryNode(T element, BinaryNode<T> lt, BinaryNode<T> rt)
	{
	    this.element = element;
	    this.left = lt;
	    this.right = rt;
	}
    }
    
    // Returns true if the tree is empty and false otherwise.
    public boolean isEmpty() { return (root == null); }

    // Adds a node the binary search tree.
    public void add(T x) { root = add(x, root); }
    
    private BinaryNode<T> add(T x, BinaryNode<T> t)
    {
	if (t == null) { return new BinaryNode<>(x, null, null); }
	
	int compareResult = x.compareTo(t.element);
	
	if (compareResult < 0) { t.left = add(x, t.left); }
	else if (compareResult > 0) { t.right = add(x, t.right); }
	else;
	return t;
    }
    
    // Returns a boolean if the given target is found in the tree.
    public boolean find(T x) { return find(x, root); }
    
    private boolean find(T x, BinaryNode<T> t)
    {
	if (t == null) { return false; }
	
	int compareResult = x.compareTo(t.element);
	
	if (compareResult < 0) { return find(x, t.left); }
	else if (compareResult > 0) { return find(x, t.right); }
	else { return true; }
    }
    
    // Returns the number of leaves present in the tree.
    public int leafCount() { return leafCount(root); }
    
    private int leafCount(BinaryNode<T> t)
    {
	if (t == null) { return 0; }
	else if ((t.left == null) && (t.right == null)) { return 1; }
	else { return (leafCount(t.left) + leafCount(t.right)); }
    }
    
    // Returns the number of parents present in the tree.
    public int parentCount() { return parentCount(root); }
    
    private int parentCount(BinaryNode<T> t)
    {
	if ((t == null) || ((t.left != null) && (t.right != null)))
	{
	    return leafCount(t.left) + leafCount(t.right);
	}
	else { return 0; }
    }
    
    // Returns the height of the tree.
    public int height()
    {
	return height(root);
    }
    
    private int height(BinaryNode<T> t)
    {
	if (t == null) { return -1; }
	else { return 1 + Math.max(height(t.left), height(t.right)); }
    }
    
    // Determines whether or not the tree is a perfect tree and returns that value.
    public boolean isPerfect() { return isPerfect(root); }
    
    private boolean isPerfect(BinaryNode<T> t)
    {
	return (height(t.left) == height(t.right));
//	if (root == null) { return true; }
//	if ((root.left == null) && (root.right == null)) { return (height(root) == 0); }
//	return isPerfect(root.left);
    }
    
    // Prints the ancestors of a given leaf.
    public boolean ancestors(T x) { return ancestors(root, x); }
    
    private boolean ancestors(BinaryNode<T> t, T x)
    {
	if (t == null) { return false; }
	else if (t.element == x) { return true; }
	
	if (ancestors(t.left, x) || ancestors(t.right, x))
	{
	    System.out.print(t.element + " ");
	    return true;
	}
	
	return false;
    }

    // Prints the binary search tree in the inorder fashion.
    public void inOrderPrint()
    {
	if (isEmpty()) { System.out.println("Empty tree."); }
	else { inOrderPrint(root); }
    }
    
    private void inOrderPrint(BinaryNode<T> t)
    {
	if (t != null)
	{
	    inOrderPrint(t.left);
	    System.out.print(t.element + " ");
	    inOrderPrint(t.right);
	}
    }
    
    // Prints the binary search tree in the preorder fashion.
    public void preOrderPrint()
    {
	if (isEmpty()) { System.out.println("Empty tree."); }
	else { preOrderPrint(root); }
    }
    
    private void preOrderPrint(BinaryNode<T> t)
    {
	if (t != null)
	{
	    System.out.print(t.element + " ");
	    preOrderPrint(t.left);
	    preOrderPrint(t.right);
	}
    }
    
    public static void main(String[] args)
    {
	MySearchTree<Integer> t = new MySearchTree<Integer>();
	
	System.out.println("a) Adding 5, 2, 1, 22, 16, 11, 17, and 123 to the binary search tree:");
	
	t.add(5);
	t.add(2);
	t.add(1);
	t.add(22);
	t.add(16);
	t.add(11);
	t.add(17);
	t.add(123);
	
	System.out.println("");
	
	System.out.println("Now my tree looks like this:");
	System.out.println("         5");
	System.out.println("        / \\");
	System.out.println("       2   22");
	System.out.println("      /   /  \\");
	System.out.println("     1   16   123");
	System.out.println("        /  \\");
	System.out.println("      11    17");
	System.out.println("");
	
	
	// Adding these numbers creates a tree that looks like this:
	//         5
	//        / \
	//       2   22
	//      /   /  \
	//     1   16   123
	//        /  \
	//      11    17
	//
	// Leaves: 4
	// Parents: 4
	// Height: 3
	// Perfect? no
	
	System.out.println("b) Demonstrating use of of the find() function:");
	System.out.println("\t-> Is 22 in the binary search tree? " + t.find(22));
	System.out.println("\t-> Is 18 in the binary search tree? " + t.find(18));
	
	System.out.println("\nc) Demonstrating use of the leafCount() function:");
	System.out.println("\t-> Leaf count: " + t.leafCount());
	
	System.out.println("\nd) Demonstrating use of the parentCount() function:");
	System.out.println("\t-> Parent count: " + t.parentCount());
	
	System.out.println("\ne) Demonstrating use of the height() function:");
	System.out.println("\t-> Tree height: " + t.height());
	
	System.out.println("\nf) Demonstrating use of the isPerfect() function:");
	System.out.println("\t-> Is tree perfect? " + t.isPerfect());
	
	System.out.println("\ng) Demonstrating use of the ancestors() function:");
	System.out.print("\t-> Ancestors of 17: "); t.ancestors(17);
	
	System.out.print("\n\t-> Ancestors of 123: "); t.ancestors(123);
	
	System.out.println("\n\nh) Demonstrating use of the inOrderPrint() function:");
	System.out.print("\t-> Inorder print: "); t.inOrderPrint();
	
	
	System.out.println("\n\ni) Demonstrating use of the preOrderPrint() function:");
	System.out.print("\t-> Preorder print: "); t.preOrderPrint();
    }
}