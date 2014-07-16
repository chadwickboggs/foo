package com.chadwickboggs.tree;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.collections.CollectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public final class TreeUtil {

    private static Gson gson = new GsonBuilder().create();


    @NotNull
    public static <T> T fromJson(@NotNull final String json, @NotNull final Class<T> clazz) {
        final T t = new GsonBuilder().create().fromJson(json, clazz);

        return t;
    }

    @NotNull
    public static <T> String toJson(@NotNull T t) {
        final String json = gson.toJson(t);

        return json;
    }

    @NotNull
    public static List<Node> lsr(@NotNull Node root) {
        final List<Node> lsr = new ArrayList<>();

        if (root.getLeft() != null) {
            lsr.addAll(lsr(root.getLeft()));
        }

        lsr.add(new Node(root.getValue()));

        if (root.getRight() != null) {
            lsr.addAll(lsr(root.getRight()));
        }

        return lsr;
    }

    @NotNull
    public static List<Node> lrs(@NotNull Node root) {
        final List<Node> lrs = new ArrayList<>();

        if (root.getLeft() != null) {
            lrs.addAll(lrs(root.getLeft()));
        }

        if (root.getRight() != null) {
            lrs.addAll(lrs(root.getRight()));
        }

        lrs.add(new Node(root.getValue()));

        return lrs;
    }

    @Nullable
    public static Node tree(@NotNull List<Node> lsr, @NotNull List<Node> lrs) {
        if (CollectionUtils.isEmpty(lsr)) {
            return null;
        }

        final Node self = lrs.get(lrs.size() - 1);
        final Node root = new Node(self.getValue());

        final int i = lsr.indexOf(self);
        final List<Node> leftLsr = lsr.subList(0, i);
        final List<Node> rightLsr = lsr.subList(i + 1, lsr.size());
        final List<Node> leftLrs = find(leftLsr, lrs);
        final List<Node> rightLrs = find(rightLsr, lrs);

        root.setLeft(tree(leftLsr, leftLrs));
        root.setRight(tree(rightLsr, rightLrs));

        return root;
    }

    @NotNull
    public static List<Node> find(@NotNull final List<Node> nodesToFind, @NotNull final List<Node> nodes) {
        final List<Node> found = new ArrayList<>();

        for (final Node node : nodes) {
            if (nodesToFind.contains(node)) {
                found.add(node);
            }
        }

        return found;
    }

    private TreeUtil() {
    }

}
