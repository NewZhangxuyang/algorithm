package com.sitehello.algo.countsort;

import java.util.Arrays;

/**
 * @Author: zhangXuYang
 * @Date: 2024-12-12-上午12:46
 * @Description: 计数排序
 */
public class CountSort {

    public static void countSort(int[] nums) {
        //统计数组最大元素
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        //创建计数数组,count[num]表示num出现的次数
        //count数组本身的下标就是待排序数组的元素
        int[] count = new int[max + 1];
        for (int num : nums) {
            count[num]++;
        }

        //初始化nums数组的指针，i
        int i = 0;
        //根据count数组，重造有序的nums数组
        for (int num = 0; num < count.length; num++) {
            //count[num]表示num出现的次数
            while (count[num] > 0) {
                nums[i++] = num;
                count[num]--;
            }
        }
    }

    //可排序相关对象
    public static void countSortPlus(int[] nums) {
        //找到nums数组的最大值，max+1 为count数组的长度
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }

        //初始化count数组，添加元素
        int[] count = new int[max + 1];
        for (int num : nums) {
            count[num]++;
        }

        // 将count数组的元素转换为，前缀和数组
        // 求 counter 的前缀和，将“出现次数”转换为“尾索引”
        // 尾索引是最后一次出现位置
        // 而 counter[num]-1 是 num 在 res 中最后一次出现的索引
        //注意i+1的越界问题，最高到max，即length-1
        for (int i = 0; i < max; i++) {
            count[i + 1] += count[i];
        }

        int[] result = new int[nums.length];
        //遍历nums数组，利用尾索引数组得到最后一次出现位置
        //因为result数组填充元素利用尾索引，是从右向左的

        //倒序遍历nums的可以防止相对位置变动，是稳定的
        //正序便利也不会错
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            result[count[num] - 1] = num;
            count[num]--;
        }
        //用result数组替换nums数组
        for (int i = 0; i < nums.length; i++) {
            nums[i] = result[i];
        }
    }


    public static void main(String[] args) {
        int[] numsPlus = {1, 2, 3, 9, 4, 7, 6, 7, 8};
        countSortPlus(numsPlus);
        System.out.println("排序后的数组为:" + Arrays.toString(numsPlus));
        int[] nums = {1, 2, 3, 9, 4, 7, 6, 7, 8};
        countSort(nums);
        System.out.println("排序后的数组为:" + Arrays.toString(nums));
    }
}
