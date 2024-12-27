package com.sitehello.algo.hanoi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zhangXuYang
 * @Date: 2024-12-17-下午11:17
 * @Description: 汉诺塔问题
 */
public class HanoiTower {

    //src 源操作区，buf 缓冲操作区 ，tar 目标操作区
    public static void move(List<Integer> src, List<Integer> tar) {
        //移动最大的那个圆盘
        //到了底部圆盘可按规则移动的步骤
        int maxPan = src.remove(src.size() - 1);

        //将大圆盘添加到目标区域
        tar.add(maxPan);
    }

    //移动的难点在于整体思想   和  src ， tar， buf 之间的角色互换
    public static void hanoiRecursion(int i, List<Integer> src, List<Integer> buffer, List<Integer> tar) {

        //i是子问题移动规模
        if (i == 1) {
            move(src, tar);
            return;
        }

        //将i-1 子问题，借助tar缓冲，从src移动到buffer
        //如何看待子规模问题
        hanoiRecursion(i - 1, src, tar, buffer);

        //规模为1的大圆盘移动
        //这个是真正的移动
        move(src, tar);

        //将i-1 子问题，借助src缓冲，从buffer移动到tar
        //如何看待子规模问题
        hanoiRecursion(i - 1, buffer, src, tar);
    }

    public static void main(String[] args) {
        //定义src ， buffer， tar
        List<Integer> src = Arrays.stream(new int[]{1, 2, 3}).boxed().collect(Collectors.toList());
        List<Integer> buffer = new ArrayList<>();
        List<Integer> tar = new ArrayList<>();
        hanoiRecursion(src.size(), src, buffer, tar);
        System.out.println(tar);
    }


}
