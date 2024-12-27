package com.sitehello.algo.bucketsort;

import java.util.Collections;
import java.util.List;

/**
 * @Author: zhangXuYang
 * @Date: 2024-12-11-下午11:47
 * @Description: 桶排序
 */
public class BucketSort {


    public static void bucketSort(float[] nums) {
        //初始化桶
        int k = nums.length / 2;
        List<List<Float>> buckets = new java.util.ArrayList<>();
        for (int i = 0; i < k; i++) {
            buckets.add(new java.util.ArrayList<>());
        }
        for (float num : nums) {
            //计算数据属于哪个桶

            //分桶的过程中，已经对数据进行了排序
            int index = (int) (num * k);

            buckets.get(index).add(num);
        }
        for (List<Float> list : buckets) {
            //对每个桶进行排序
            Collections.sort(list);
        }

        //合并桶,将桶中的数据放回原数组
        int i = 0;
        for (List<Float> list : buckets) {
            for (Float num : list) {
                nums[i++] = num;
            }
        }
    }


    public static void main(String[] args) {
        float[] nums = {0.1f, 0.3f, 0.2f, 0.5f, 0.4f, 0.7f, 0.6f, 0.9f, 0.8f};
        bucketSort(nums);
        System.out.println("排序后的数组为:" + java.util.Arrays.toString(nums));
    }
}
