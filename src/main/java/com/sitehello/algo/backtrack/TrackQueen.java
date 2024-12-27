package com.sitehello.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangXuYang
 * @Date: 2024-12-27-下午11:12
 * @Description: n皇后回溯算法
 */
public class TrackQueen {

    public static void main(String[] args) {
        //8x8的棋盘
        int n = 4;
        //初始化棋盘
        List<List<String>> states = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add("#");
            }
            states.add(row);
        }
        boolean[] cols = new boolean[n];
        // row-col 和 row+col 决定元素在哪条对角线上
        //他们的个数是 2*n - 1
        boolean[] diags1 = new boolean[(2 * n) - 1];
        boolean[] diags2 = new boolean[(2 * n) - 1];
        List<List<List<String>>> res = new ArrayList<>();
        TrackQueen(0, n, states, res, cols, diags1, diags2);
        //输出res
        for (List<List<String>> re : res) {
            for (List<String> row : re) {
                System.out.println(row);
            }
            System.out.println();
        }

    }

    // 根据每行进行深度递归,n为最后的行数
    // state 是每时每刻棋盘的状态  List<List<>> 是二维棋盘
    // List<List<List<String>>> 是解法路径
    // 以col为循环主体,boolean[] cols，记录该列已被占用
    // boolean[] diags1, boolean[] diags2 记录对角线的占用情况
    public static void TrackQueen(int row, int n, List<List<String>> state, List<List<List<String>>> res,
                                  boolean[] cols, boolean[] diags1, boolean[] diags2) {
        if (row == n) {
            List<List<String>> copyState = new ArrayList<>();
            for (List<String> rowCopy : state) {
                copyState.add(new ArrayList<>(rowCopy));
            }
            res.add(copyState);
        }
        //遍历所有的列
        //以行放置，开启列循环，尝试每一列
        for (int col = 0; col < n; col++) {
            // row-col 和 row+col 决定元素在哪条对角线上
            //这里的 + n - 1 是为了保证row-col对角线的值不为负数
            //每条对角线都有恒定的数来对应
            int diag1 = row - col + n - 1;
            int diag2 = row + col;


            if (!cols[col] && !diags1[diag1] && !diags2[diag2]) {
                //放置该行
                state.get(row).set(col, "Q");

                cols[col] = diags1[diag1] = diags2[diag2] = true;
                //进入下一行
                TrackQueen(row + 1, n, state, res, cols, diags1, diags2);
                //回溯,进行下一列的循环
                state.get(row).set(col, "#");
                cols[col] = diags1[diag1] = diags2[diag2] = false;
            }
        }
    }
}
