package com.zbw.springboot.controller;

/**
 * Created by 郑博文 on 2019/12/26.
 */
public class TextController {

    public static void main(String[] args){
        //数组下标
        int[] duplication={0,1,2,3,4};
        //数组长度
        int length=5;
        //数组本身
        int[] num={0,2,3,3,4};
        boolean theValue=duplicate(num,length,duplication);
        System.out.println("这是结果"+theValue+"  "+duplication[0]);
    }
    //判断数组是否重复
    public static boolean duplicate(int[] nums, int length, int[] duplication) {
        //如果长度为0则直接返回不用判断
        if (nums == null || length <= 0){
            return false;
        }
       //遍历循环
        for (int i = 0; i < length; i++) {
            //当数值不等于下标时
            while (nums[i] != i) {
                //如果
                if (nums[i] == nums[nums[i]]) {
                    //将值换到第一个位置 可打印输出
                    duplication[0] = nums[i];
                    return true;
                }
                //将值换到对应的下标
                swap(nums, i, nums[i]);
            }
        }
        return false;
    }

    //数值位置交换（本身是几就交换到那个位置）
    private static void swap(int[] nums, int i, int j) {

        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
