package problemsession1_asymptotic_behavior_of_functions_and_double_ended;

import java.util.Arrays;

public class AddFirstRemoveFirstGetIInO1 {

  static void main() {
    DoublyDynamicArrayImpl dynamicArray = new DoublyDynamicArrayImpl();
    int[] data = {1, 2, 3};
    dynamicArray.build(data);

    System.out.println("Init:");
    System.out.println(Arrays.toString(dynamicArray.iter_seq()));

    System.out.println("Get:");
    System.out.println("Expected: 2, Actual: " + dynamicArray.get(1));

    dynamicArray.insertFirst(9);
    dynamicArray.insertFirst(8);
    dynamicArray.insertFirst(7);

    System.out.println("Insert first:");
    System.out.println(
        "Expected: [7, 8, 9, 1, 2, 3], Actual: " + Arrays.toString(dynamicArray.iter_seq()));

    System.out.println("Delete first:");
    dynamicArray.deleteFirst();
    dynamicArray.deleteFirst();
    dynamicArray.deleteFirst();
    dynamicArray.deleteFirst();
    System.out.println("Expected: [2, 3], Actual: " + Arrays.toString(dynamicArray.iter_seq()));
    // Need to shrink
    System.out.println(
        "dynamicArray.data.length = " + dynamicArray.data.length + " dynamicArray.len = "
            + dynamicArray.len);

    System.out.println("Delete empty:");
    dynamicArray.deleteFirst();
    dynamicArray.deleteFirst();
    dynamicArray.deleteFirst();
    System.out.println(
        "dynamicArray.data.length = " + dynamicArray.data.length + " dynamicArray.len = "
            + dynamicArray.len);

    System.out.println("Insert again:");
    dynamicArray.insertFirst(6);
    dynamicArray.insertFirst(8);
    dynamicArray.insertFirst(2);
    dynamicArray.insertFirst(3);
    System.out.println("Expected: [3, 2, 8, 6], Actual: " + Arrays.toString(dynamicArray.iter_seq()));
    System.out.println(
        "dynamicArray.data.length = " + dynamicArray.data.length + " dynamicArray.len = "
            + dynamicArray.len);
  }

  // It's essentially Dequeue
  static class DoublyDynamicArrayImpl {

    int len;
    int size;
    int startIndex;
    int[] data;

    public DoublyDynamicArrayImpl() {
      len = 0;
      size = 3;
      // 1
      startIndex = size / 3;
      data = new int[size];
    }

    // x 2 x
    // x x 2 1 x x
    // x x x x x x x x x x x x
    void growIfNeeded() {
      // size / 2
      if (len > size / 3) {
        int newSize = size * 3;
        int newStartIndex = size;
        int j = newStartIndex;
        int[] tmp = new int[newSize];
        for (int i = startIndex; i < startIndex + len; i++) {
          tmp[j++] = data[i];
        }
        startIndex = newStartIndex;
        size = newSize;
        data = tmp;
      }
    }

    void shrinkIfNeeded() {
      if (size == 3) {
        return;
      }
      if (len < size / 3) {
        size = size / 3;
        int[] tmp = new int[size];

        int newStartI = size / 3;
        // x 1 x
        // x x 1 2 x x
        // x x x x x 1 2 3 x x x x
        for (int j = newStartI, i = startIndex; i < startIndex + len; i++, j++) {
          tmp[j] = data[i];
        }
        startIndex = newStartI;
        data = new int[size];
        for (int i = 0; i < tmp.length; i++) {
          data[i] = tmp[i];
        }
      }
    }

    // x 1 x
    // x x 1 2 x x
    void build(int[] data) {
      for (int i = 0; i < data.length; i++) {
        insert_last(data[i]);
      }
    }

    int[] iter_seq() {
      int[] tmp = new int[len];
      int j = startIndex;
      for (int i = 0; i < len; i++) {
        tmp[i] = data[j++];
      }
      return tmp;
    }

    // x 1 x
    // x x 1 2 x x
    void insert_last(int x) {
      growIfNeeded();
      data[len + startIndex] = x;
      len++;
    }

    // O(1)
    void insertFirst(int x) {
      growIfNeeded();
      int newStart = startIndex - 1;
      data[newStart] = x;
      startIndex = newStart;
      len++;
    }

    // O(1)
    int get(int i) {
      return data[startIndex + i];
    }

    void deleteFirst() {
      if (len == 0) {
        return;
      }
      data[startIndex++] = 0;
      len--;
      shrinkIfNeeded();
    }

  }

}
