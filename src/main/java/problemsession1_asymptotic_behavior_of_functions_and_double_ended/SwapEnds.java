package problemsession1_asymptotic_behavior_of_functions_and_double_ended;

import java.util.Arrays;
import lecture2_data_structures_and_dynamic_arrays.DynamicArray.DynamicArrayImpl;

public class SwapEnds {

  // 1 2 3
  // 3 2 1
  static void main() {
    DynamicArrayImpl dynamicArray = new DynamicArrayImpl();
    int[] data = {1, 2, 3};
    dynamicArray.build(data);
    System.out.println(Arrays.toString(dynamicArray.iter_seq()));
    swapEnds(dynamicArray);
    System.out.println(Arrays.toString(dynamicArray.iter_seq()));
  }

  // 1 2 3
  // 3 2 1

  // algo
  // f = get first
  // l = get last
  // del first
  // del last
  // insert_f(l)
  // insert_l(f)
  static void swapEnds(DynamicArrayImpl dynamicArray) {
    int f = dynamicArray.get_at(0);
    int l = dynamicArray.get_at(dynamicArray.len() - 1);

    dynamicArray.delete_first();
    dynamicArray.delete_last();

    dynamicArray.insert_first(l);
    dynamicArray.insert_last(f);
  }

}
