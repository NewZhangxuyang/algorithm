package com.sitehello.algo.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhangXuYang
 * @Date: 2024-12-22-下午11:34
 * @Description: 子集和问题
 */
public class SubTarget {

    //元素可以被重复选取
    public static void backTrackTarget(List<Integer> state, int target, int total, int[] choices, List<List<Integer>> res) {
        if (target == total) {
            res.add(new ArrayList<>(state));
            return;
        }

        for (int i = 0; i < choices.length; i++) {
            int choice = choices[i];
            //剪枝total + choice[i] > target的元素
            if (total + choice > target) {
                continue;
            }
            //尝试
            state.add(choice);
            //进入下一轮
            backTrackTarget(state, target, total + choice, choices, res);

            //回退
            state.remove(state.size() - 1);
        }
    }


    //剪掉重复的[4, 5], [5, 4] 类似结果
    //为实现重复子集剪枝，我们初始化变量start，用于指示遍历起始点
    public static void backTrackTargetCutRepeat(List<Integer> state, int start, int target, int total, int[] choices, List<List<Integer>> res) {
        //得到解
        if (total == target) {
            res.add(new ArrayList<>(state));
            return;
        }

        for (int i = start; i < choices.length; i++) {
            int choice = choices[i];
            //剪枝过早大于target的分支
            if (choice + total > target) {
                continue;
            }
            //尝试
            state.add(choice);
            //开启下一轮，注意start，若本轮到了i+1，那下一轮也从i+1开始,跳过重复的元素
            backTrackTargetCutRepeat(state, i, target, choice + total, choices, res);
            //回退state
            state.remove(state.size() - 1);
        }
    }

    //给定数组可能包含重复元素，每个元素只可被选择一次
    //会出现相等元素在某轮中被多次选择，利用数组预先排序
    // i>start 防止越界，也确保该for已经进行到第二个元素，才比较choices[i]== choices[i-1],跳过
    //规定每个数组元素只能被选择一次,利用start进一步，start=i+1
    public static void backTrackTargetPlus(List<Integer> state, int start, int target, int total, int[] choices, List<List<Integer>> res) {

        if (target == total) {
            res.add(new ArrayList<>(state));
            return;
        }

        //choice 已经预先排序了,剪枝1  i= start
        for (int i = start; i < choices.length; i++) {
            int choice = choices[i];
            //剪枝2
            if (choice + total > target) {
                // 升序的数组，后续元素一定大，for循环可以break
                break;
            }
            //剪枝3,从正确的第二个元素开始，去除左边相等元素的选择
            if (start < i && choice == choices[i - 1]) {
                continue;
            }
            //选择
            state.add(choice);

            //下一轮,剪枝4  start = i+1 ，不仅是筛除重复子集，也是保证一个元素在一个全路径下只选择一次
            backTrackTargetPlus(state, i + 1, target, choice + total, choices, res);

            //回退
            state.remove(state.size() - 1);
        }

    }


    public static void main(String[] args) {
        List<List<Integer>> res = new ArrayList<>();
        int[] nums = {3, 4, 5};
        //递归是轮次的定义，for循环是分支的意思
        backTrackTargetCutRepeat(new ArrayList<>(), 0, 9, 0, nums, res);


        List<List<Integer>> resPlus = new ArrayList<>();
        int[] numsRepeat = {3, 4, 6, 4, 5, 3};
        Arrays.sort(numsRepeat);
        backTrackTargetPlus(new ArrayList<>(), 0, 9, 0, numsRepeat, resPlus);
        System.out.println(resPlus);


    }
}
