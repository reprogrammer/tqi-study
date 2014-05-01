# [Exercise 1](https://github.com/reprogrammer/tqi-study/wiki/Nullness-Checker-Tutorial#exercise-1)

Because `left` and `right` are declared as `@Nullabe` the Nullness Checker will report the following warnings:

```
TreeNode.java: warning: [dereference.of.nullable] dereference of possibly-null reference left
total += left.addTree();
         ^   
TreeNode.java: warning: [dereference.of.nullable] dereference of possibly-null reference right
total += right.addTree();
         ^ 
```

# [Exercise 2](https://github.com/reprogrammer/tqi-study/wiki/Nullness-Checker-Tutorial#exercise-2)

We declare the receiver parameter of `TreeNode.setChildren` as `@UnderInitialization(TreeNode.class) TreeNode this` so that `TreeNode.setChildren` is allowed to be called from the constructor where `this` is not yet fully initialized.

We also annotate `TreeNode.setChildren` as `@EnsuresNonNull({"left", "right"})` so that the modular and intraprocedural analysis of the Nullness Checker can verify that the constructor assigns non-null values to the fields declared as `@NonNull` (i.e., `left` and `right`).

```java
class TreeNode {
  Object data;
  TreeNode left;
  TreeNode right;

  public TreeNode(Object data, TreeNode l, TreeNode r) {
    this.data = data;
    setChildren(l, r);
  }

  @EnsuresNonNull({"left", "right"})
  void setChildren(@UnderInitialization(TreeNode.class) TreeNode this, TreeNode l, TreeNode r) {
    left = l;
    right = r;
  }
}
```

# [Exercise 3](https://github.com/reprogrammer/tqi-study/wiki/Nullness-Checker-Tutorial#exercise-3)

`TreeNode.createTree(int levels)` returns a non-null value when `levels` is nonzero. However, we cannot express this property using the annotations of the Nullness Checker. Because we're confident that `createTree(2)` returns a non-null value, it's perfectly justified to suppress the warning in either of the following two ways:

```java
@SuppressWarnings("nullness")
TreeNode twoLeveledTree = createTree(2);
twoLeveledTree.printTree();
```

```java
TreeNode twoLeveledTree = createTree(2);
assert != twoLeveledTree : "@AssumeAssertion(nullness)";
twoLeveledTree.printTree();
```

Note that the following code snippet is **invalid** because Java permits annotations on declarations but not statements.

```java
TreeNode twoLeveledTree = createTree(2);
@SuppressWarnings("nullness")
twoLeveledTree.printTree();
```

# [Exercise 4](https://github.com/reprogrammer/tqi-study/wiki/Nullness-Checker-Tutorial#exercise-4)

The expression `new Object[size]` creates an array with null elements, however, the elements of field `children` are declared as `@NonNull`. To resolve the resulting warning, we declare two local variables in the constructor and annotate the declaration of one of the local variables as `@SuppressWarnings("nullness")`.

```java
public class TreeNode {
  Object[] children;

  public TreeNode(int size) {
    @Nullable Object[] localChildren = new Object[size];
    for (int i = 0; i < size; ++i) {
      localChildren[i] = new Object();
    }

    @SuppressWarnings("nullness")
    Object[] nonNullLocalChildren = localChildren;

    children = nonNullLocalChildren;
  }
```