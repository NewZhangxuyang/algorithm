package com.sitehello.algo.creattree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangXuYang
 * @Date: 2024-12-19-上午12:20
 * @Description: 树的遍历
 */
public class Traversal {

    public static List<TreeNode> res;


    //符合规则的，全路径
    public static List<List<TreeNode>> resPath;

    //path 代表路径
    public static List<TreeNode> path;


    //正常的前序遍历
    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.val == 7) {
            res.add(root);
        }
        preOrder(root.left);
        preOrder(root.right);
    }


    public static void preOrderPath(TreeNode root) {
        //当前访问的节点
        if (root == null) {
            return;
        }

        //将当前节点加入路径
        path.add(root);

        //如何符合搜索规则，则将全路径加入
        if (root.val == 7) {
            resPath.add(new ArrayList<>(path));
        }
        preOrderPath(root.left);
        preOrderPath(root.right);

        //递归再次回到某一当前节点，将路径回退到当前
        //就是将添加的下层路径点删除
        path.remove(path.size() - 1);

    }





}
