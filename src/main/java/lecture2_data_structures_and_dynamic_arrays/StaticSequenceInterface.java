package lecture2_data_structures_and_dynamic_arrays;

interface StaticSequenceInterface {

  void build(int[] data);

  int len();

  int[] iter_seq();

  int get_at(int i);

  void set_at(int i, int n);
}
