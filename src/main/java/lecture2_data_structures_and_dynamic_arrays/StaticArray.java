package lecture2_data_structures_and_dynamic_arrays;

import java.util.Arrays;

public class StaticArray {

  static void main() {
    int[] data = {3, 2, 4};
    System.out.println("Initial array:");
    System.out.println(Arrays.toString(data));

    StaticSequenceInterface staticArray = new StaticArrayImpl();
    // O(n)
    staticArray.build(data);
    System.out.println("Data structure:");
    // O(n)
    System.out.println(Arrays.toString(staticArray.iter_seq()));

    // O(1)
    System.out.println("Len:");
    System.out.println(staticArray.len());

    // O(1)
    System.out.println("Get:");
    System.out.println(staticArray.get_at(1));

    // O(1)
    System.out.println("Set:");
    staticArray.set_at(1, 5);
    System.out.println(staticArray.get_at(1));
  }

  static class StaticArrayImpl implements StaticSequenceInterface {

    int[] data;

    public StaticArrayImpl() {
    }

    @Override
    public void build(int[] data) {
      this.data = data;
    }

    @Override
    public int len() {
      return data.length;
    }

    @Override
    public int[] iter_seq() {
      return data;
    }

    @Override
    public int get_at(int i) {
      return data[i];
    }

    @Override
    public void set_at(int i, int n) {
      data[i] = n;
    }
  }
}
