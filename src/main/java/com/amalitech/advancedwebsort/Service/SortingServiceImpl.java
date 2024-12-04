package com.amalitech.advancedwebsort.Service;
import com.amalitech.advancedwebsort.Requests.SortRequest;
import com.amalitech.advancedwebsort.Enums.SortTypeEnums;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class SortingServiceImpl implements SortingService {
    private final ParsingService parsingService;

    public String Sorting(SortRequest sortRequest) {
        SortTypeEnums sortTypeEnums = SortTypeEnums.convertFromString(sortRequest.sortType());
        return switch (sortTypeEnums) {
            case HEAP_SORT -> Heapsort(sortRequest);
            case MERGE_SORT -> mergeSort(sortRequest);
            case QUICK_SORT -> Quicksort(sortRequest);
            case RADIX_SORT -> RadixSort(sortRequest);
            case BUCKET_SORT -> bucketSort(sortRequest);
        };
    }

    public String Heapsort(SortRequest sortRequest) {
        int[] arrayPassed = parsingService.IntParser(sortRequest);
        int n = arrayPassed.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arrayPassed, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = arrayPassed[0];
            arrayPassed[0] = arrayPassed[i];
            arrayPassed[i] = temp;

            heapify(arrayPassed, i, 0);
        }
        return  Arrays.stream(arrayPassed).mapToObj(String::valueOf).collect(Collectors.joining(","));
    }

    private void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    public String  mergeSort(SortRequest sortRequest) {
        int[] arrayPassed = parsingService.IntParser(sortRequest);

        if (arrayPassed.length <= 1) {
            return Arrays.toString(arrayPassed);
        }

        int mid = arrayPassed.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arrayPassed.length - mid];

        System.arraycopy(arrayPassed, 0, left, 0, mid);
        System.arraycopy(arrayPassed, mid, right, 0, arrayPassed.length - mid);
        mergeSortHelper(left);
        mergeSortHelper(right);
        merge(arrayPassed, left, right);
        return Arrays.stream(arrayPassed).mapToObj(String::valueOf).collect(Collectors.joining(","));
    }

    private void mergeSortHelper(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);

        mergeSortHelper(left);
        mergeSortHelper(right);

        merge(arr, left, right);
    }

    private void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    public String Quicksort(SortRequest sortRequest) {
        int[] arrayPassed = parsingService.IntParser(sortRequest);
        quickSortHelper(arrayPassed, 0, arrayPassed.length - 1);
        return Arrays.stream(arrayPassed).mapToObj(String::valueOf).collect(Collectors.joining(","));
    }

    private void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSortHelper(arr, low, pivotIndex - 1);
            quickSortHelper(arr, pivotIndex + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public String RadixSort(SortRequest sortRequest) {
        int[] arrayPassed = parsingService.IntParser(sortRequest);
        int max = getMax(arrayPassed);

        for (int place = 1; max / place > 0; place *= 10) {
            countingSort(arrayPassed, place);
        }
        return Arrays.stream(arrayPassed).mapToObj(String::valueOf).collect(Collectors.joining(","));
    }

    private void countingSort(int[] arr, int place) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int value : arr) {
            count[(value / place) % 10]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[--count[(arr[i] / place) % 10]] = arr[i];
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    private int getMax(int[] arr) {
        int max = arr[0];
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public  String bucketSort(SortRequest sortRequest) {
        float[] arrayPassed = parsingService.floatParser(sortRequest);
        int n = arrayPassed.length;

        float maxValue = getMax(arrayPassed);

        @SuppressWarnings("unchecked")
        List<Float>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (float value : arrayPassed) {
            int bucketIndex = (int) ((value / maxValue) * (n - 1));
            buckets[bucketIndex].add(value);
        }
        for (List<Float> bucket : buckets) {
            Collections.sort(bucket);
        }

        List<Float> sortedList = new ArrayList<>();
        for (List<Float> bucket : buckets) {
            sortedList.addAll(bucket);
        }

        return sortedList.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    private float getMax(float[] array) {
        float max = array[0];
        for (float value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}

