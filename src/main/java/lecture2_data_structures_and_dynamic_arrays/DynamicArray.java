package lecture2_data_structures_and_dynamic_arrays;

import java.util.Arrays;

public class DynamicArray {

  static void main() {
    int[] data = {3, 2, 4};
    System.out.println("Initial array:");
    System.out.println(Arrays.toString(data));

    DynamicArrayImpl dynamicArray = new DynamicArrayImpl();
    // O(n)
    dynamicArray.build(data);
    System.out.println("Data structure:");
    // O(n)
    System.out.println(
        "expected: [3, 2, 4], actual: " + Arrays.toString(dynamicArray.iter_seq()));

    // O(1)
    System.out.println("Len:");
    System.out.println("expected: 3, actual: " + dynamicArray.len());

    // O(n)
    System.out.println("Get:");
    System.out.println("expected: 2, actual: " + dynamicArray.get_at(1));

    // O(n)
    System.out.println("Set:");
    dynamicArray.set_at(1, 5);
    System.out.println("expected: [3, 5, 4], actual: " + Arrays.toString(dynamicArray.iter_seq()));

    // O(n)
    System.out.println("Insert_at:");
    dynamicArray.insert_at(1, 9);
    dynamicArray.insert_at(0, 0);
    System.out.println(
        "expected: [0, 3, 9, 5, 4], actual: " + Arrays.toString(dynamicArray.iter_seq()));

    // O(n)
    System.out.println("Insert_first:");
    dynamicArray.insert_first(-1);
    System.out.println(
        "expected: [-1, 0, 3, 9, 5, 4], actual: " + Arrays.toString(dynamicArray.iter_seq()));

    // O(1)
    System.out.println("Insert_last:");
    dynamicArray.insert_last(6);
    System.out.println(
        "expected: [-1, 0, 3, 9, 5, 4, 6], actual: " + Arrays.toString(dynamicArray.iter_seq()));

    // O(n)
    System.out.println("Delete_first:");
    dynamicArray.delete_first();
    System.out.println(
        "expected: [0, 3, 9, 5, 4, 6], actual: " + Arrays.toString(dynamicArray.iter_seq()));

    // O(1)
    System.out.println("Delete_last:");
    dynamicArray.delete_last();
    System.out.println(
        "expected: [0, 3, 9, 5, 4], actual: " + Arrays.toString(dynamicArray.iter_seq()));

    // O(n)
    System.out.println("Delete_at:");
    dynamicArray.delete_at(2);
    System.out.println(
        "expected: [0, 3, 5, 4], actual: " + Arrays.toString(dynamicArray.iter_seq()));

    System.out.println("Delete on empty:");
    dynamicArray.delete_last();
    dynamicArray.delete_last();
    dynamicArray.delete_last();
    dynamicArray.delete_last();
    dynamicArray.delete_last();
    dynamicArray.delete_first();
    System.out.println(Arrays.toString(dynamicArray.iter_seq()));

    System.out.println("Insert_first on empty");
    dynamicArray.insert_first(1);
    System.out.println("expected: [1], actual: " + Arrays.toString(dynamicArray.iter_seq()));
  }

  public static class DynamicArrayImpl implements StaticSequenceInterface, DynamicSequenceInterface {

    int size;
    int len;
    int[] data;

    public DynamicArrayImpl() {
      this.size = 1;
      this.len = 0;
      this.data = new int[this.size];
    }

    void growIfNeeded() {
      if (len == size) {
        size *= 2;
        int[] tmp = new int[size];
        for (int i = 0; i < len; i++) {
          tmp[i] = data[i];
        }
        data = tmp;
        return;
      }
    }

    void shrinkIfNeeded() {
      if (len < size / 2) {
        size /= 2;
        int[] tmp = new int[size];
        for (int i = 0; i < tmp.length; i++) {
          tmp[i] = data[i];
        }
        data = tmp;
        return;
      }
    }

    //region Static

    @Override
    public void build(int[] data) {
      for (int i = 0; i < data.length; i++) {
        insert_last(data[i]);
      }
    }

    @Override
    public int len() {
      return this.len;
    }

    @Override
    public int[] iter_seq() {
      int[] tmp = new int[len];
      for (int i = 0; i < tmp.length; i++) {
        tmp[i] = data[i];
      }
      return tmp;
    }

    @Override
    public int get_at(int i) {
      return data[i];
    }

    @Override
    public void set_at(int i, int n) {
      data[i] = n;
    }

    //endregion

    //region Dynamic

    // 1, 2, 3
    @Override
    public void insert_at(int i, int x) {
      if (i == len - 1) {
        insert_last(x);
        return;
      }
      growIfNeeded();
      // 3 5 4
      // 1 9
      // 3 9 5 4

      //
      for (int j = len - 1; j >= i; j--) {
        data[j + 1] = data[j];
      }
      data[i] = x;
      len++;
    }

    @Override
    public void delete_at(int i) {
      if (len == 0) {
        return;
      }
      if (i == len - 1) {
        delete_last();
        return;
      }
      // 1 2 3
      // 1 3
      for (int j = i; j < len; j++) {
        data[j] = data[j + 1];
      }
      len--;
      shrinkIfNeeded();
    }

    @Override
    public void insert_first(int x) {
      insert_at(0, x);
    }

    @Override
    public void insert_last(int x) {
      growIfNeeded();
      data[len] = x;
      len++;
    }

    @Override
    public void delete_first() {
      delete_at(0);
    }

    @Override
    public void delete_last() {
      if (len == 0) {
        return;
      }
      data[len - 1] = 0;
      len--;
      shrinkIfNeeded();
    }

    //endregion


  }
}
