package lecture2_data_structures_and_dynamic_arrays;

import java.util.Arrays;

public class LinkedList {

  static void main() {
    int[] data = {3, 2, 4};
    System.out.println("Initial array:");
    System.out.println(Arrays.toString(data));

    LinkedListImpl linkedList = new LinkedListImpl();
    // O(n)
    linkedList.build(data);
    System.out.println("Data structure:");
    // O(n)
    System.out.println(
        "expected: [3, 2, 4], actual: " + Arrays.toString(linkedList.iter_seq()));

    // O(1)
    System.out.println("Len:");
    System.out.println("expected: 3, actual: " + linkedList.len());

    // O(n)
    System.out.println("Get:");
    System.out.println("expected: 2, actual: " + linkedList.get_at(1));

    // O(n)
    System.out.println("Set:");
    linkedList.set_at(1, 5);
    System.out.println("expected: [3, 5, 4], actual: " + Arrays.toString(linkedList.iter_seq()));

    // O(n)
    System.out.println("Insert_at:");
    linkedList.insert_at(1, 9);
    linkedList.insert_at(0, 0);
    System.out.println("expected: [0, 3, 9, 5, 4], actual: " + Arrays.toString(linkedList.iter_seq()));

    // O(1)
    System.out.println("Insert_first:");
    linkedList.insert_first(-1);
    System.out.println("expected: [-1, 0, 3, 9, 5, 4], actual: " + Arrays.toString(linkedList.iter_seq()));

    // O(n)
    System.out.println("Insert_last:");
    linkedList.insert_last(6);
    System.out.println("expected: [-1, 0, 3, 9, 5, 4, 6], actual: " + Arrays.toString(linkedList.iter_seq()));

    // O(1)
    System.out.println("Delete_first:");
    linkedList.delete_first();
    System.out.println("expected: [0, 3, 9, 5, 4, 6], actual: " + Arrays.toString(linkedList.iter_seq()));

    // O(n)
    System.out.println("Delete_last:");
    linkedList.delete_last();
    System.out.println("expected: [0, 3, 9, 5, 4], actual: " + Arrays.toString(linkedList.iter_seq()));

    // O(n)
    System.out.println("Delete_at:");
    linkedList.delete_at(2);
    System.out.println("expected: [0, 3, 5, 4], actual: " + Arrays.toString(linkedList.iter_seq()));

    System.out.println("Delete on empty:");
    linkedList.delete_last();
    linkedList.delete_last();
    linkedList.delete_last();
    linkedList.delete_last();
    linkedList.delete_last();
    linkedList.delete_first();
    System.out.println(Arrays.toString(linkedList.iter_seq()));

    System.out.println("Insert_first on empty");
    linkedList.insert_first(1);
    System.out.println("expected: [1], actual: " + Arrays.toString(linkedList.iter_seq()));
  }

  static class LinkedListImpl implements StaticSequenceInterface, DynamicSequenceInterface {

    @Override
    public void insert_at(int i, int x) {
      if (i == 0) {
        insert_first(x);
        return;
      }
      // 3
      Node curr = head;
      // 5
      for (int j = 0; j < i; j++) {
        curr = curr.next;
      }

      Node toInsert = new Node(x);
      // 5
      toInsert.next = curr;

      Node prev = head;
      while (prev.next != curr) {
        prev = prev.next;
      }
      prev.next = toInsert;
      len++;
    }

    @Override
    public void delete_at(int i) {
      if (i == 0) {
        delete_first();
        return;
      }
      if (i == len - 1) {
        delete_last();
        return;
      }
      // 1 2 3
      // 1
      Node curr = head;
      for (int j = 0; j < i; j++) {
        curr = curr.next;
      }
      // curr 2
      Node prev = head;
      while (prev.next != curr) {
        prev = prev.next;
      }
      prev.next = curr.next;
      curr.next = null;
      len--;
    }

    @Override
    public void insert_first(int x) {
      Node toInsert = new Node(x);
      toInsert.next = head;
      head = toInsert;
      len++;
    }

    @Override
    public void insert_last(int x) {
      if (head == null) {
        head = new Node(x);
        len++;
        return;
      }
      Node curr = head;
      while (curr.next != null) {
        curr = curr.next;
      }
      curr.next = new Node(x);
      len++;
    }

    @Override
    public void delete_first() {
      if (head == null) {
        return;
      }
      head = head.next;
      len--;
    }

    @Override
    public void delete_last() {
      if (head == null) {
        return;
      }
      if (len == 1) {
        head = null;
        len--;
        return;
      }
      Node curr = head;
      while (curr.next != null) {
        curr = curr.next;
      }

      Node newLast = head;
      while (newLast.next != curr) {
        newLast = newLast.next;
      }
      newLast.next = null;
      len--;
    }

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
