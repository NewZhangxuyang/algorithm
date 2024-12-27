package com.sitehello.algo.baseCountSort;

/**
 * @Author: zhangXuYang
 * @Date: 2024-12-15-上午1:21
 * @Description: 基数排序
 * <p>
 * 前提是数据必须可以表示为固定位数的格式
 */
public class BaseCountSort {

    public static void main(String[] args) {
        //初始化都是8位数的数组
        int[] nums = {745, 743, 742, 741, 749, 739, 738, 787, 736, 735};
        baseCountSort(nums);
        System.out.println("排序后的数组为:" + java.util.Arrays.toString(nums));

    }

    //获取元素的第k位，exp为10进制的基础底数，1，10，100....
    //用除exp的方法，将基础位置进一位
    //再用模10的方法，得到最后一位
    public static int digit(int num, int exp) {
        return (num / exp) % 10;
    }


    public static void baseCount(int[] nums, int exp) {

        //计数数组,10是每个位置的最大值，10进制 0-9
        int[] count = new int[10];

        //构造统计数组count
        for (int i = 0; i < nums.length; i++) {
            int digit = digit(nums[i], exp);
            count[digit]++;
        }
        //将计数数组改造成，前缀和数组
        //注意长度为 count.length - 1 ，防止越界
        for (int i = 0; i < count.length - 1; i++) {
            count[i + 1] += count[i];
        }

        int[] result = new int[nums.length];
        //倒叙遍历nums
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            //当前排序的是哪一个位
            int digit = digit(num, exp);
            //获取最后出现在第几位，count[digit]
            //转换成数组索引，需要减一
            int locate = count[digit] - 1;
            result[locate] = num;
            count[digit]--;
        }
        //覆盖原nums[]数组
        for (int i = 0; i < nums.length; i++) {
            nums[i] = result[i];
        }
    }


    public static void baseCountSort(int[] nums) {
        // 获取数组的最大元素，用于判断最大位数
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        //exp是各位置的基础底数，10，100......
        // 从个位开始，到最大位数
        // 在连续的排序轮次中，后一轮排序会覆盖前一轮排序的结果
        // 而第二轮排序结果,那么第二轮的结果将取代第一轮的结果
        // 由于数字的高位优先级高于低位，因此应该先排序低位再排序高位
        for (int exp = 1; exp <= max; exp *= 10) {
            baseCount(nums, exp);
        }

    }

}
