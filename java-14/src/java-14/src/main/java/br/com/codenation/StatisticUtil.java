package br.com.codenation;

import java.util.Arrays;

public class StatisticUtil {

	public static int average(int[] elements) {
		int average = 0;
		for (int index : elements) {
			average += index;
		}
		return average / elements.length;
	}

	public static int mode(int[] elements) {
		int moreRepeated = 0, mode = 0;
		Arrays.sort(elements);
		for (int element : elements) {
			int counter = 0;
			for (int index : elements) {
				if (element == index) {
					counter++;
				} else {
					continue;
				}
				if (counter > moreRepeated) {
					moreRepeated = counter;
					mode = index;
				}
			}
		}
		return mode;
	}

	public static int median(int[] elements) {
		Arrays.sort(elements);
		if (elements.length % 2 == 0) {
			return (elements[(elements.length / 2) - 1] + elements[(elements.length / 2)]) / 2;
		}
		return elements[(elements.length / 2)];
	}
}