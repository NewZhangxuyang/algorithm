package com.sitehello.algo.backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhangXuYang
 * @Date: 2024-12-20-上午12:07
 * @Description: 全排列
 */
public class FullOrder {


    //state     状态，即为全排列过程中的 已被选择元素的集合
    //choices   输入的数组      choice 数组元素
    //boolean型数组selected     对应标记某一条路径下的，choices元素是否已被选择
    public static void backTrack(List<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res) {

        //state数组长度达到完整，即视为解
        //这一路径结束
        if (state.size() == choices.length) {
            //这里不能直接用state的引用，因为state会被后续的回退操作修改
            //根据state的值，创建一个新的list
            res.add(new ArrayList<>(state));
            return;
        }

        for (int i = 0; i < choices.length; i++) {
            int choice = choices[i];
            //当前i元素未被选择,也是剪枝的含义
            if (!selected[i]) {
                selected[i] = true;
                state.add(choice);

                //进入下了一轮
                backTrack(state, choices, selected, res);

                //清除当前路径，准备回退
                //恢复状态
                state.remove(state.size() - 1);
                selected[i] = false;
            }

        }
    }

    public static List<List<Integer>> fullOrder(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTrack(new ArrayList<Integer>(), nums, new boolean[nums.length], res);
        return res;
    }

    //存在重复元素时，回溯算法
    //duplicated    约束的是本轮循环选择元素，不允许选择相等的元素
    //select        是解决纵向重复选择问题的关键
    //duplicated    是解决横向有相等元素的问题
    public static void backTrackPlus(List<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res) {
        if (state.size() == choices.length) {
            res.add(new ArrayList<>(state));
            return;
        }
        Set<Integer> duplicated = new HashSet<>();
        //某路径的途经点，选择当前节点元素的for循环
        for (int i = 0; i < choices.length; i++) {
            //选择的节点元素
            int choice = choices[i];
            if (!selected[i] && !duplicated.contains(choice)) {
                //标记当前元素已被选择
                selected[i] = true;
                state.add(choice);
                //将当前元素加入本此循环的判定相同元素的组
                duplicated.add(choice);
                backTrackPlus(state, choices, selected, res);
                //状态回退
                selected[i] = false;
                state.remove(state.size() - 1);
            }

        }

    }

    //selected 和 duplicated 都用于剪枝，但两者的目标不同。
    //一个完整搜索路径中只有一个 selected
    //它记录的是当前状态中包含哪些元素，其作用是避免某个元素在 state 中重复出现。

    //duplicated 它记录的是在本轮遍历（for 循环）中哪些元素已被选择过
    // 其作用是保证相等元素只被选择一次。
    public static List<List<Integer>> fullOrderPlus(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTrackPlus(new ArrayList<>(), nums, new boolean[nums.length], res);
        return res;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 3};
        List<List<Integer>> res = fullOrderPlus(nums);
        System.out.println(res);

    }
}
