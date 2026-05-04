package pc.quicksort;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class QuickSort {
	public static int partition(int[] array, int low, int high) {
		int pivot = array[low];
		int i = low - 1;
		int j = high + 1;
		while (true) {
			do {
				i++;
			} while (array[i] < pivot);

			do {
				j--;
			} while (array[j] > pivot);

			if (i >= j) {
				return j;
			}
			swap(array, i, j);
		}
	}

	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void quickSort(int[] array, int low, int high) {
		if (low < high) {
			int pi = partition(array, low, high);
			quickSort(array, low, pi);
			quickSort(array, pi + 1, high);
		}
	}
	
	public static int[] generateRandomArray(int size) {
		Random rand = new Random();
		int[] result = new int[size];
		for (int i = 0; i < size; i++) {
			result[i] = rand.nextInt();
		}
		return result;
	}
	
	// Q3 - pool commun, seuil fixe
	public static void parQuickSort(int[] array, int low, int high) {
    	ForkJoinPool.commonPool().invoke(new QuickSortTask(array, low, high, 10000));
	}

	// Q4 & Q5 - pool dédié avec threads et seuil configurables
	public static void parQuickSort(int[] array, int low, int high, int threads, int threshold) {
		ForkJoinPool pool = new ForkJoinPool(threads);
		pool.invoke(new QuickSortTask(array, low, high, threshold));
		pool.shutdown();
	}
	static class QuickSortTask extends RecursiveAction {

		private final int[] array;
		private final int low;
		private final int high;
		private final int threshold;

		QuickSortTask(int[] array, int low, int high, int threshold) {
			this.array  = array;
			this.low  = low;
			this.high = high;
			this.threshold = threshold;
		}

		@Override
		protected void compute() {
			if (high - low + 1 <= threshold) {
				quickSort(array, low, high);
			} else {
				int pi = partition(array, low, high);
				QuickSortTask gauche = new QuickSortTask(array, low, pi, threshold);
				QuickSortTask droite = new QuickSortTask(array, pi + 1, high, threshold);
				gauche.fork();
				droite.compute();
				gauche.join();
			}
		}
	}
}
