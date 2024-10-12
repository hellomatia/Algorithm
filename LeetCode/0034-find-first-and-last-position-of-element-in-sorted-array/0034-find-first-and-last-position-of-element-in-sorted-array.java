// Find First and Last Position of Element in Sorted Array

// Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
// if target is not found in the array, return [-1, -1]/
// MUST WRITE AN ALGORITHM WITH O(log n) RUNTIME COMPLEXITY.

// IMPORTANT
// * array order is incereasing
// * index start at 0
// * array item is not unique

// Solution
// * I will use binary search -> Time Complexity O(log n)
// * Use Two Binary Search
//   1. Find target start index
//   2. Find target end index

// I will use Java util librery.
// This librery has many useful classes.
// Set, Map, List, etc...
import java.util.*;

class Solution {

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                return new int[]{0, 0};
            }
            return new int[]{-1, -1};
        }
        return new int[] {findStartIndex(nums, target), findEndIndex(nums, target)};
    }

    private int findStartIndex(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int index = (start + end) / 2;
            
            if (nums[index] < target) {
                start = index + 1;
            } else {
                end = index - 1;
            }
        }
        
        if (start >= nums.length) start = nums.length - 1;
        return nums[start] == target ? start : -1;
    }

    private int findEndIndex(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int index = (start + end) / 2;
            
            if (nums[index] <= target) {
                start = index + 1;
            } else {
                end = index - 1;
            }
        }
        
        if (end < 0) end = 0;
        return nums[end] == target ? end : -1;
    }
}