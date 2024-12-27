package com.sitehello.algo.heapsort;

import java.util.Arrays;

/**
 * @Author: zhangXuYang
 * @Date: 2024-12-11-下午10:40
 * @Description:
 */
public class HeapSort {

    //类比出堆操作，但是实际是以数组的形式存储堆，n是待排序的堆区
    public static void siftDown(int n, int[] nums, int i) {
        //n是待排序堆的长度，出堆后动态变化
        //i是待排序堆的当前节点
        while (true) {
            int l = i * 2 + 1;
            int r = i * 2 + 2;
            int max = i;
            if (l < n && nums[l] > nums[max]) {
                max = l;
            }
            if (r < n && nums[r] > nums[max]) {
                max = r;
            }
            if (max == i) {
                break;
            }
            //交换元素
            int temp = nums[i];
            nums[i] = nums[max];
            nums[max] = temp;
            //向下调整
            i = max;
        }
    }

    public static void heapSort(int[] nums) {
        //建堆，找最后一个非叶子节点
        for (int i = (nums.length - 1) / 2; i >= 0; i--) {
            siftDown(nums.length, nums, i);
        }
        //排序,交换最大值到数组末尾，然后调整堆
        for (int i = nums.length - 1; i > 0; i--) {
            //堆顶元素的出堆操作
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            //i是待排序堆的长度
            siftDown(i, nums, 0);
        }
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, 11, 5, 2, 7, 6, 9, 8, 15};
        heapSort(nums);
        System.out.println("排序后的数组为:" + Arrays.toString(nums));
    }

}
