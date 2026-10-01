package lecture2_data_structures_and_dynamic_arrays;

import java.util.Arrays;

public class LinkedList {

  static void main() {
    int[] data = {3, 2, 4};
    System.out.println("Initial array:");
    System.out.println(Arrays.toString(data));

    StaticSequenceInterface staticSequence = new LinkedListImpl();
    // O(n)
    staticSequence.build(data);
    System.out.println("Data structure:");
    // O(n)
    System.out.println(
        "expected: [3, 2, 4], actual: " + Arrays.toString(staticSequence.iter_seq()));

    // O(1)
    System.out.println("Len:");
    System.out.println("expected: 3, actual: " + staticSequence.len());

    // O(1)
    System.out.println("Get:");
    System.out.println("expected: 2, actual: " + staticSequence.get_at(1));

    // O(1)
    System.out.println("Set:");
    staticSequence.set_at(1, 5);
    System.out.println("expected: 5, actual: " + staticSequence.get_at(1));
  }

  static class LinkedListImpl implements StaticSequenceInterface {

    static class Node {

      Node next;
      int val;

      public Node(int val) {
        this.val = val;
      }
    }

    Node head;
    int len;

    @Override
    public void build(int[] data) {
      Node dummy = new Node(-1);
      Node curr = dummy;
      for (int val : data) {
        curr.next = new Node(val);
        curr = curr.next;
        len++;
      }
      head = dummy.next;
    }

    @Override
    public int len() {
      return len;
    }

    @Override
    public int[] iter_seq() {
      Node curr = head;
      int[] data = new int[len];
      int i = 0;
      while (curr != null) {
        data[i++] = curr.val;
        curr = curr.next;
      }
      return data;
    }

    @Override
    public int get_at(int i) {
      Node curr = head;
      for (int j = 0; j < i; j++) {
        curr = curr.next;
      }
      return curr.val;
    }

    @Override
    public void set_at(int i, int n) {
      Node curr = head;
      for (int j = 0; j < i; j++) {
        curr = curr.next;
      }
      curr.val = n;
    }
  }
}
