package com.sitehello.algo.mgergesort;

/**
 * @Author: zhangXuYang
 * @Date: 2024-12-10-上午12:41
 * @Description: 归并排序
 */
public class MergeSort {


    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 5, 4, 7, 6, 9, 8};
        MergeSort.mergeSort(nums, 0, nums.length - 1);
        System.out.println("排序后的数组为:" + java.util.Arrays.toString(nums));
    }

    public static void merge(int[] nums, int left, int mid, int right) {
        //左数组空间 [left,mid] 右数组空间[mid+1,right]
        //创建临时数组，容纳合并结果
        int[] temp = new int[right - left + 1];

        //初始化位置指针
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            //比较两个数组的元素，将较小的元素放入临时数组
            if (nums[i] <= nums[j])
                temp[k++] = nums[i++];
            else
                temp[k++] = nums[j++];
        }
        //将剩余元素放入临时数组,左右数组长度可能不一样，造成数组有剩余元素
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }

        for (k = 0; k < temp.length; k++) {
            nums[left + k] = temp[k];
        }
    }

    public static void mergeSort(int[] nums, int left, int right) {

        if (left >= right) {
            return;
        }
        //分治中间位置
        int mid = (left + right) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        //合并两个有序数组
        merge(nums, left, mid, right);
    }

}
