package com.sitehello.algo.quick;

import java.util.Arrays;

/**
 * @Author: zhangXuYang
 * @Date: 2024-12-09-上午12:06
 * @Description: 快速排序
 */
public class QuickSort {


    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 5, 4, 7, 6, 9, 8};
        quickSort(nums, 0, nums.length - 1);
        System.out.println("排序后的数组为:" + Arrays.toString(nums));
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(nums, left, right);
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    /**
     * 进行排序区间的划分
     */
    public static int partition(int[] nums, int left, int right) {
        //基准值nums[left]
        int i = left, j = right;
        while (i < j) {
            //从右往左找到第一个小于基准值的元素
            while (i < j && nums[j] >= nums[left]) {
                j--;
            }
            //从左往右找到第一个大于基准值的元素
            while (i < j && nums[i] <= nums[left]) {
                i++;
            }
            //交换两个元素
            swap(nums, i, j);
        }
        //最终i和j相遇，i的位置就是中间位置,交换基准值到分界线
        swap(nums, left, i);
        return i;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
