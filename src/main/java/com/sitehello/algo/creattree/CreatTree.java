package com.sitehello.algo.creattree;


import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangXuYang
 * @Date: 2024-12-17-上午12:02
 * @Description: 二叉树的创建
 */
public class CreatTree {
    public static void main(String[] args) {
        TreeNode root = buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(root);
    }


    /**
     * 当前树在preorder的根节点的索引为 pr
     * <p>
     * 在inorder中根节点的索引为ir , ir是必须查询出来的
     * 是根据 pr的值得到的——————————>建立值到索引的映射
     * <p>
     * 当前树在inorder中的索引区间为 [l,r]
     * 当前树    pr          [l,r]
     * 左子树    pr+1       [l,ir-1]
     * 右子树    pr+1 + ir-l   [ir+1,r]
     */
    public static TreeNode creatTreeByPreAndInOrder(int[] preorder, Map<Integer, Integer> inorederMap, int pr, int l, int r) {
        //递归终止条件,子树索引区间为空
        if (r - l < 0) {
            return null;
        }
        //根节点
        TreeNode root = new TreeNode(preorder[pr]);

        //根节点在中序遍历中的索引
        int ir = inorederMap.get(preorder[pr]);

        //构建左子树,pr+1是左子树的根节点,左子树的索引区间是[l,ir-1]
        root.left = creatTreeByPreAndInOrder(preorder, inorederMap, pr + 1, l, ir - 1);

        //构建右子树,pr+ir-l是右子树的根节点,右子树的索引区间是[ir+1,r]
        root.right = creatTreeByPreAndInOrder(preorder, inorederMap, pr + 1 + ir - l, ir + 1, r);
        return root;

    }


    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        //构建hash表，存储中序的值与索引的映射
        Map<Integer, Integer> inorederMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorederMap.put(inorder[i], i);
        }
        // 从根节点开始构建树
        TreeNode root = creatTreeByPreAndInOrder(preorder, inorederMap, 0, 0, inorder.length - 1);
        return root;
    }

}
