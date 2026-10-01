package lecture2_data_structures_and_dynamic_arrays;

public interface DynamicSequenceInterface {

  void insert_at(int i, int x);

  void delete_at(int i);

  void insert_first(int x);

  void insert_last(int x);

  void delete_first();

  void delete_last();
}
