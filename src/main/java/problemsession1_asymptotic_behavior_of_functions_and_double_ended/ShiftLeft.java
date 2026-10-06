package problemsession1_asymptotic_behavior_of_functions_and_double_ended;

import java.util.Arrays;
import lecture2_data_structures_and_dynamic_arrays.DynamicArray.DynamicArrayImpl;

public class ShiftLeft {

  static void main() {
    DynamicArrayImpl dynamicArray = new DynamicArrayImpl();
    int[] data = {1, 2, 3};
    dynamicArray.build(data);
    System.out.println(Arrays.toString(dynamicArray.iter_seq()));
    shiftLeft(dynamicArray, 2);
    System.out.println(Arrays.toString(dynamicArray.iter_seq()));
  }

  // 1 2 3, 2
  // 2 3 1
  // 3 1 2
  static void shiftLeft(DynamicArrayImpl d, int k) {
    if (k < 1) {
      return;
    }
    int f = d.get_at(0);
    d.delete_first();
    d.insert_last(f);
    shiftLeft(d, k - 1);
  }
}
