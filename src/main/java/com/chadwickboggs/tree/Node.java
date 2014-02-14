package com.chadwickboggs.tree;

public class Node {

  public int value;
  public Node left;
  public Node right;


  public Node(final int value) {
    this.value = value;
  }


  public Node(final int value, final Node left, final Node right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }


  public Node setValue(final int value) {
    this.value = value;

    return this;
  }


  public Node setLeft(final Node left) {
    this.left = left;

    return this;
  }


  public Node setRight(final Node right) {
    this.right = right;

    return this;
  }


  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (!(o instanceof Node)) return false;

    final Node node = (Node) o;

    if (value != node.value) return false;
    if (left != null ? !left.equals(node.left) : node.left != null) return false;
    if (right != null ? !right.equals(node.right) : node.right != null) return false;

    return true;
  }


  @Override
  public int hashCode() {
    int result = value;
    result = 31 * result + (left != null ? left.hashCode() : 0);
    result = 31 * result + (right != null ? right.hashCode() : 0);
    return result;
  }
}
