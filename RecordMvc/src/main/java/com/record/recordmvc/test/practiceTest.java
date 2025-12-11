package com.record.recordmvc.test;

import java.util.Arrays;

/**
 * @Author: Echo
 * @Date: 2025/3/20 16:07
 * @Description: 没事练习用的
 */
public class practiceTest {
    public static void main(String[] args) {
        practiceTest text1 =new practiceTest();
        int [] nums1 = {1,2,3,0,0,0};
        int [] nums2 = {2,5,6};
        text1.merge(nums1,3,nums2,3);
    }
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //nums1升序排列
        for (int i = 0; i < nums2.length; i++) {
            nums1[m+i]=nums2[i];
        }
        Arrays.sort(nums1);
    }
}
