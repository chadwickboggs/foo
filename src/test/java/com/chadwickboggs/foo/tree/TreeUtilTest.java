package com.chadwickboggs.foo.tree;

import com.chadwickboggs.tree.Node;
import com.chadwickboggs.tree.TreeUtil;
import org.apache.commons.collections.ListUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class TreeUtilTest {

  //
  // {
  //     "value": 6
  //     "left": {
  //         "value": 5
  //         "left": {
  //             "value": 3
  //         },
  //         "right": {
  //             "value": 10
  //         },
  //     },
  //     "right": {
  //         "value": 11
  //         "right": {
  //             "value": 12
  //         },
  //     },
  // }
  //
  private static final String TREE =
      "{\"value\":6,\"left\":{\"value\":5,\"left\":{\"value\":3},\"right\":{\"value\":10}},"
          + "\"right\":{\"value\":11,\"right\":{\"value\":12}}}";
  private static final List<Node> LSR = nodes( 3, 5, 10, 6, 11, 12 );
  private static final List<Node> LRS = nodes( 3, 10, 5, 12, 11, 6 );


  @NotNull
  private static List<Node> nodes( int... ids ) {
    final List<Node> nodes = new ArrayList<>();

    for ( final int id : ids ) {
      nodes.add( new Node( id ) );
    }

    return nodes;
  }


  @Test
  public void testFromJson() {
    final Node tree = TreeUtil.fromJson( TREE, Node.class );
    final String roundTripped = TreeUtil.toJson( tree );

    final String message = String.format(
        "Round tripped tree does not match original input tree.  Original: %s  Round-tripped: %s",
        TREE, roundTripped );
    assertEquals( message, TREE, roundTripped );
  }


  @Test
  public void testToJson() {
    final Node node = TreeUtil.fromJson( TREE, Node.class );
    final String json = TreeUtil.toJson( node );

    final String message = String.format(
        "Returned tree does not match expected tree.  Tree: %s", TreeUtil.toJson( json ) );
    assertEquals( message, TREE, json );
  }


  @Test
  public void testLsr() {
    final Node node = TreeUtil.fromJson( TREE, Node.class );
    final List<Node> lsr = TreeUtil.lsr( node );

    final String message = String.format(
        "Returned LSR does not match expected LSR.  LSR: %s", TreeUtil.toJson( lsr )
    );
    assertTrue( message, ListUtils.isEqualList( LSR, lsr ) );
  }


  @Test
  public void testLrs() {
    final Node node = TreeUtil.fromJson( TREE, Node.class );
    final List<Node> lrs = TreeUtil.lrs( node );

    final String message = String.format(
        "Returned LRS does not match expected LRS.  LRS: %s", TreeUtil.toJson( lrs )
    );
    assertTrue( message, ListUtils.isEqualList( LRS, lrs ) );
  }


  @Test
  public void testTree() {
    final Node tree = TreeUtil.tree( LSR, LRS );

    final String message = String.format(
        "Returned tree does not match original tree.  Original: %s  Returned: %s",
        TREE, TreeUtil.toJson( tree )
    );
    assertEquals( message, TREE, TreeUtil.toJson( tree ) );
  }


  @Test
  public void testFind() {
    final List<Node> nodes = nodes( 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 );

    List<Node> expected = nodes( 4 );
    List<Node> found = TreeUtil.find( nodes( 4 ), nodes );
    String message = String.format(
        "Found, nodes does not match expected nodes.  Expected: %s  Found: %s",
        TreeUtil.toJson( expected ), TreeUtil.toJson( found ) );
    assertEquals( message, expected, found );

    expected = nodes( 2, 3, 5, 7 );
    message = String.format(
        "Found, nodes does not match expected nodes.  Expected: %s  Found: %s",
        TreeUtil.toJson( expected ), TreeUtil.toJson( found ) );
    found = TreeUtil.find( nodes( 2, 3, 5, 7 ), nodes );
    assertEquals( message, expected, found );

    expected = nodes( 2, 3, 5, 7 );
    message = String.format(
        "Found, nodes does not match expected nodes.  Expected: %s  Found: %s",
        TreeUtil.toJson( expected ), TreeUtil.toJson( found ) );
    found = TreeUtil.find( nodes( 7, 5, 3, 2 ), nodes );
    assertEquals( message, expected, found );

    expected = nodes( 2, 3, 5, 7 );
    message = String.format(
        "Found, nodes does not match expected nodes.  Expected: %s  Found: %s",
        TreeUtil.toJson( expected ), TreeUtil.toJson( found ) );
    found = TreeUtil.find( nodes( 5, 7, 2, 3 ), nodes );
    assertEquals( message, expected, found );

    expected = nodes( 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 );
    message = String.format(
        "Found, nodes does not match expected nodes.  Expected: %s  Found: %s",
        TreeUtil.toJson( expected ), TreeUtil.toJson( found ) );
    found = TreeUtil.find( nodes( 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ), nodes );
    assertEquals( message, expected, found );

    expected = nodes( 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 );
    message = String.format(
        "Found, nodes does not match expected nodes.  Expected: %s  Found: %s",
        TreeUtil.toJson( expected ), TreeUtil.toJson( found ) );
    found = TreeUtil.find( nodes( 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 ), nodes );
    assertEquals( message, expected, found );

    expected = nodes( 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 );
    message = String.format(
        "Found, nodes does not match expected nodes.  Expected: %s  Found: %s",
        TreeUtil.toJson( expected ), TreeUtil.toJson( found ) );
    found = TreeUtil.find( nodes( -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 ), nodes );
    assertEquals( message, expected, found );
  }


  private void verifyTree(
      final Node node, final List<String> nulls, final List<String> notNulls
  ) {
    assertNotNull( "Null tree.", node );
    String message =
        String.format( "Node.value does not match expected node.value.  Value: %s",
            node.getValue()
        );
    assertEquals( message, 6, node.getValue() );

    assertNotNull( "Null node.left.", node.getLeft() );
    message = String.format(
        "Node.left.value does not match expected node.left.value.  Value: %s",
        node.getLeft().getValue()
    );
    assertEquals( message, 5, node.getLeft().getValue() );

    assertNotNull( "Null node.left.left.", node.getLeft().getLeft() );
    message = String.format(
        "Node.left.left.value does not match expected node.left.left.value.  Value: %s",
        node.getLeft().getLeft().getValue() );
    assertEquals( message, 3, node.getLeft().getLeft().getValue() );
    assertNull( "Null node.left.left.left.", node.getLeft().getLeft().getLeft() );
    assertNull( "Null node.left.left.right.", node.getLeft().getLeft().getRight() );

    assertNotNull( "Null node.left.right.", node.getLeft().getRight() );
    message = String.format(
        "Node.left.right.value does not match expected node.left.right.value.  Value: %s",
        node.getLeft().getRight().getValue()
    );
    assertEquals( message, 10, node.getLeft().getRight().getValue() );
    assertNull( "Null node.left.right.left.", node.getLeft().getRight().getLeft() );
    assertNull( "Null node.left.right.right.", node.getLeft().getRight().getRight() );

    assertNotNull( "Null node.right.", node.getRight() );
    message = String.format(
        "Node.right.value does not match expected node.right.value.  Value: %s",
        node.getRight().getValue()
    );
    assertEquals( message, 11, node.getRight().getValue() );

    assertNull( "Null node.right.left.", node.getRight().getLeft() );

    assertNotNull( "Null node.right.right.", node.getRight().getRight() );
    message = String.format(
        "Node.right.right.value does not match expected node.right.right.value.  Value: %s",
        node.getRight().getRight().getValue()
    );
    assertEquals( message, 12, node.getRight().getRight().getValue() );
    assertNull( "Null node.right.right.left.", node.getRight().getRight().getLeft() );
    assertNull( "Null node.right.right.right.", node.getRight().getRight().getRight() );
  }

}
