
public class BSTree {

	private BSTNode root;

	public BSTNode search(int key) 
	{
		BSTNode T = this.root;
		while (T != null) 
		{
			if (T.getKey() > key)
				T = T.getLeft();
			else if (T.getKey() == key)
				return T;
			else
				T = T.getRight();
		}
		return T;
	}

	public void insert(int key) 
	{
		BSTNode p = this.root;
		BSTNode q = new BSTNode();
		while (p != null) 
		{
			if (key == p.getKey())
				return;
			q = p;
			if (key < p.getKey())
				p = p.getLeft();
			else
				p = p.getRight();
		}

		BSTNode newNode = new BSTNode(key);
		if (root == null)
			root = newNode;
		else if (key < q.getKey()) 
			q.setLeft(newNode);
		else 
			q.setRight(newNode);
	}

	public void delete(int key) 
	{
		BSTNode parent = root;
		BSTNode p = root;
		boolean isLeftChild = false;
		// �θ��� ����
		while (p.getKey() != key) 
		{
			parent = p;
			if (p.getKey() > key) 
			{
				isLeftChild = true;
				p = p.getLeft();
			} 
			else 
			{
				isLeftChild = false;
				p = p.getRight();
			}
			if (p == null) 
				return;
		}
		// �������
		if ((p.getLeft() == null) && (p.getRight() == null)) 
		{
			if (p == root) 
				root = null;
			if (isLeftChild == true) 
				parent.setLeft(null);
			else 
				parent.setRight(null);
		}
		// �ϳ��� �ڽ� == null
		else if((p.getRight() == null) || (p.getLeft() == null)) 
		{
			// �ϳ��� �ڽ� Right == null
			if (p.getRight() == null) 
			{
				if (p == root) 
					root = p.getLeft();
				else if (isLeftChild) 
					parent.setLeft(p.getLeft());
				else 
					parent.setRight(p.getLeft());
			} 
			// �ϳ��� �ڽ� left == null
			else if (p.getLeft() == null) 
			{
				if (p == root) 
					root = p.getRight();
				else if (isLeftChild) 
					parent.setLeft(p.getRight());
				else 
					parent.setRight(p.getRight());
			}
		}
		// �ΰ��� �ڽ� right subtree minimum value
		else if (p.getLeft() != null && p.getRight() != null) 
		{
			// ������ ����Ʈ���� �ּҰ��� ã��
			BSTNode min_R = minNode(p);
			if (p == root) 
				root = min_R;
			else if (isLeftChild) 
				parent.setLeft(min_R);
			else 
				parent.setRight(min_R);
			min_R.setLeft(p.getLeft());
		}
		return;
	}

	private BSTNode minNode(BSTNode deleleBSTNode) 
	{
		BSTNode min = null;
		BSTNode minParent = null;
		BSTNode q = deleleBSTNode.getRight();
		while (q != null) 
		{
			minParent = min;
			min = q;
			q = q.getLeft();
		}
		if (min != deleleBSTNode.getRight()) 
		{
			minParent.setLeft(min.getRight());
			min.setRight(deleleBSTNode.getRight());
		}
		return min;
	}

	public void print() {
		printBST(root);
		System.out.println("\n");
	}

	private void printBST(BSTNode root) {
		if (root != null) {
			System.out.print("<");
			printBST(root.getLeft());
			System.out.print(root.getKey());
			printBST(root.getRight());
			System.out.print(">");
		}
	}
}