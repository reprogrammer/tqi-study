package treeadd;

import static org.junit.Assert.*;

import org.junit.Test;

public class TreeAddTest {

	@Test
	public void test(TreeAddTest this) {
		int levels = 10;
		TreeNode root = new TreeNode(levels);
		int result = root.addTree();
		assertEquals(1023, result);
	}

}
