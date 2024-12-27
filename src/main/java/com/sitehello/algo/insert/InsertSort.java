package com.sitehello.algo.insert;

import java.util.Arrays;

/**
 * @Author: zhangXuYang
 * @Date: 2024-12-08-下午11:34
 * @Description: 插入排序
 */
public class InsertSort {


    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 5, 4, 7, 6, 9, 8};
        int[] result = insertSort(nums);
        System.out.println(Arrays.toString(result));
    }

    /**
     * 插入排序
     *
     * @param nums 待排序数组
     * @return int[]
     */
    public static int[] insertSort(int[] nums) {
        //从第二个元素开始
        for (int i = 1; i < nums.length; i++) {

            // 待插入的元素
            int base = nums[i];

            // 已排序区间的指针，初始为边界值
            int j = i - 1;

            // 从后往前遍历已排序区间,并且进行元素的后移
            while (j >= 0 && nums[j] > base) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = base;
        }

        return nums;
    }

}
