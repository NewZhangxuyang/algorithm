package com.sitehello.algo.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangXuYang
 * @Date: 2024-12-11-上午12:09
 * @Description: 堆
 * 入堆和出堆都是在原有堆的基础上进行调整
 */
public class Heap {
    // 大根堆
    public static List<Integer> heap = new ArrayList<>();

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(3);
        nums.add(7);
        nums.add(5);
        nums.add(4);
        nums.add(6);
        creatHeap(nums);
        System.out.println(heap);


        push(7);
        System.out.println(heap);
        int val = pop();
        System.out.println(val);
        System.out.println(heap);
    }


    public static int left(int i) {
        return 2 * i + 1;
    }

    public static int right(int i) {
        return 2 * i + 2;
    }

    public static int parent(int i) {
        return (i - 1) / 2;
    }

    public static void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public static void push(int val) {
        heap.add(val);
        siftUp(heap.size() - 1);
    }

    //元素入堆，从底部开始调整
    public static void siftUp(int i) {
        while (true) {
            //获取父节点
            int p = parent(i);
            // 如果父节点大于当前节点，调整完毕
            // 或者已经到达根节点
            if (p < 0 || heap.get(i) <= heap.get(p)) {
                break;
            }
            swap(i, p);
            i = p;
        }
    }

    public static int pop() {
        if (heap.isEmpty()) {
            throw new RuntimeException("heap is empty");
        }
        //将堆顶元素与最后一个元素交换
        swap(0, heap.size() - 1);

        //删除最后一个元素
        int val = heap.remove(heap.size() - 1);

        //从堆顶开始调整
        siftDown(0);

        return val;
    }

    //i为调整节点所在位置的索引
    public static void siftDown(int i) {
        while (true) {
            //获取左右子节点,记录max是最大值节点
            int l = left(i), r = right(i), max = i;
            if (l < heap.size() && heap.get(l) > heap.get(max)) {
                max = l;
            }
            if (r < heap.size() && heap.get(r) > heap.get(max)) {
                max = r;
            }
            // 若节点 i 最大或索引 l, r 越界，则无须继续堆化，跳出循环
            // 节点i最大，会发生在倒数第二层，即最后一个非叶子节点
            // 因为i是从最后一层拿上来的，最后一层都是它的兄弟节点
            if (max == i) {
                break;
            }
            swap(i, max);
            i = max;
        }
    }

    //创建堆,从最后一个非叶子节点开始调整
    //每当堆化一个节点后，以该节点为根节点的子树就形成一个合法的子堆
    //能够保证当前节点之下的子树已经是合法的子堆，这样堆化当前节点才是有效的
    public static void creatHeap(List<Integer> nums) {
        //直接接收nums，不需要一个一个添加
        heap = new ArrayList<>(nums);
        //堆化除了叶子节点的所有节点
        for (int i = parent(heap.size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

}
