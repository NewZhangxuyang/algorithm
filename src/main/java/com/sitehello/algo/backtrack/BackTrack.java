package com.sitehello.algo.backtrack;

import com.sitehello.algo.backtrack.TreeNode;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhangXuYang
 * @Date: 2024-12-19-上午12:50
 * @Description: 回溯算法
 * 回溯算法（backtracking algorithm）是一种通过穷举来解决问题的方法
 * 它的核心思想是从一个初始状态出发，暴力搜索所有可能的解决方案
 * 当遇到正确的解则将其记录，直到找到解或者尝试了所有可能的选择都无法找到解为止
 */

public class BackTrack {

    //是否是解
    public static boolean isSolution(List<TreeNode> state) {
        return !state.isEmpty() && state.get(state.size() - 1).val == 7;
    }

    //记录解
    public static void recordSolution(List<TreeNode> state, List<List<TreeNode>> res) {
        res.add(state);
    }

    //判断当前状态下，该选择是否合法
    public static boolean isValid(TreeNode choices) {
        return choices != null && choices.val != 3;
    }

    //更新状态
    public static void makeChoice(List<TreeNode> state, TreeNode choice) {
        state.add(choice);
    }

    //恢复状态
    public static void undoChoice(List<TreeNode> state) {
        state.remove(state.size() - 1);
    }


    //“尝试、回退、剪枝”
    //状态state为节点遍历路径       状态表示问题在某一时刻的情况，包括已经做出的选择
    //尝试                      根据   可用选择   来探索解空间的过程，包括做出选择，更新状态，检查是否为解
    //回退                       指遇到不满足  约束条件   的状态时，撤销前面做出的选择，回到上一个状态
    //选择choices为当前节点的左子节点和右子节点    当前路径下可做出的探索选择
    //结果res记录是路径列表
    public static void backTrack(List<TreeNode> state, List<TreeNode> choices, List<List<TreeNode>> res) {
        //检查是否为解
        if (isSolution(state)) {
            //添加解
            recordSolution(state, res);
        }

        for (TreeNode choise : choices) {
            // 剪枝：判断选择是否合法
            if (isValid(choise)) {
                //尝试做出选择
                makeChoice(state, choise);

                //进行下一轮选择
                backTrack(state, Arrays.asList(choise.left, choise.right), res);

                //回退撤销，恢复到之前的状态
                undoChoice(state);
            }
        }


    }

}
