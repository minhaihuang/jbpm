package com.hhm.jbpm.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// int[] arr={1,3,2,5,6,8,7,4,9};
		// sortAsc(arr);
		// test03(50);
		getMaxSameString("abcd", "abc");
	}

	/**
	 * 降序
	 */
	public static void sortDesc(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				// 比较，把大数向前排序
				if (arr[i] < arr[j]) {
					// 交互数据
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;

				}
			}
		}

		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 升序
	 */
	public static void sortAsc(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				// 比较，把大数向前排序
				if (arr[i] > arr[j]) {
					// 交互数据
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;

				}
			}
		}

		System.out.println(Arrays.toString(arr));
	}

	public static void mergesort(int[] a) {
		int[] b = new int[a.length];

		int s = 1;

		while (s < a.length) {
			mergePass(a, b, s);
			s += s;
			mergePass(b, a, s);
			s += s;
		}

		System.out.println(Arrays.toString(a));
	}

	public static void mergePass(int[] x, int[] y, int s) {
		int i = 0;

		while (i <= x.length - 2 * s) {
			merge(x, y, i, i + s - 1, i + 2 * s - 1);
			i = i + 2 * s;
		}

		if (i + s < x.length) {
			merge(x, y, i, i + s - 1, x.length - 1);

		} else {
			for (int j = i; j < x.length; j++) {
				y[j] = x[j];
			}
		}
	}

	private static void merge(int[] c, int[] d, int w, int m, int r) {
		int i = w;
		int j = m + 1;
		int k = w;

		while ((i <= m) && (j <= r)) {
			if (c[i] < c[j]) {
				d[k++] = c[i++];
			} else {
				d[k++] = c[j++];
			}

			if (i > m) {
				for (int q = j; q <= r; q++) {
					d[k++] = c[q];
				}
			} else {
				for (int q = i; q <= m; q++) {
					d[k++] = c[q];
				}
			}
		}
	}

	public static void test03(int index) {
		List<Integer> list = new ArrayList<Integer>();

		for (int i = 2; i <= 200;) {
			list.add(i);
			i = i + 2;
		}

		// 放入数据到数组
		int a[] = new int[list.size()];
		for (int i = 0; i < a.length; i++) {
			a[i] = list.get(i);
		}
		if (index - 1 >= a.length) {
			System.out.println("索引越界");
		}

		System.out.println(a[index - 1]);

	}

	/**
	 * 获取最长公共子序列
	 * 
	 * abcdefg,bacabcde。者两个字符串最长的公共子序列为abcde.
	 */
	public static void getMaxSameString(String str1, String str2) {
		// 将第一个字符串换为字符数组
		char[] c1 = str1.toCharArray();
		// 将第2个字符串换为字符数组
		char[] c2 = str1.toCharArray();
		int maxLen=0;
		int temp=0;
		
		
		// 开始遍历
		for (int m = 0; m < c1.length; m++) {
			int k = 0;
			for (int i = m; i < c1.length; i++) {
				if(k==c2.length){
					k=0;
					break;
				}
				for (; k < c2.length;) {
					// 找到第一个字符相同的索引
					if (c1[i] == c2[k]) {
						// 记录当前k，返回c1的下一个字符'`
						temp++;
						
						if(maxLen<=temp){
							maxLen=temp;
						}
						
						k++;
						break;
					}
				}

			}

		}
		
		System.out.println(maxLen);
	}
}