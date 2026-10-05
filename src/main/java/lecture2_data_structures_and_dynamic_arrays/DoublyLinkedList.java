package lecture2_data_structures_and_dynamic_arrays;

import java.util.Arrays;

public class DoublyLinkedList {

  static void main() {
    int[] data = {3, 2, 4};
    System.out.println("Initial array:");
    System.out.println(Arrays.toString(data));

    DoublyLinkedListImpl linkedList = new DoublyLinkedListImpl();
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
    System.out.println(
        "expected: [0, 3, 9, 5, 4], actual: " + Arrays.toString(linkedList.iter_seq()));

    // O(1)
    System.out.println("Insert_first:");
    linkedList.insert_first(-1);
    System.out.println(
        "expected: [-1, 0, 3, 9, 5, 4], actual: " + Arrays.toString(linkedList.iter_seq()));

    // O(n)
    System.out.println("Insert_last:");
    linkedList.insert_last(6);
    System.out.println(
        "expected: [-1, 0, 3, 9, 5, 4, 6], actual: " + Arrays.toString(linkedList.iter_seq()));

    // O(1)
    System.out.println("Delete_first:");
    linkedList.delete_first();
    System.out.println(
        "expected: [0, 3, 9, 5, 4, 6], actual: " + Arrays.toString(linkedList.iter_seq()));

    // O(n)
    System.out.println("Delete_last:");
    linkedList.delete_last();
    System.out.println(
        "expected: [0, 3, 9, 5, 4], actual: " + Arrays.toString(linkedList.iter_seq()));

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

  static class DoublyLinkedListImpl implements StaticSequenceInterface, DynamicSequenceInterface {

    static class Node {

      Node next;
      Node prev;
      int val;

      public Node(int val) {
        this.val = val;
      }
    }

    Node head;
    Node tail;
    int len;

    //region Static

    @Override
    public void build(int[] data) {
      if (data.length == 0) {
        return;
      }
      Node dummy = new Node(-1);
      Node curr = dummy;
      Node prev = null;
      for (int i = 0; i < data.length; i++) {
        curr.next = new Node(data[i]);
        curr = curr.next;
        curr.prev = prev;
        prev = curr;
        len++;
      }
      tail = curr;
      head = dummy.next;
    }

    @Override
    public int len() {
      return len;
    }

    @Override
    public int[] iter_seq() {
      int i = 0;
      int[] res = new int[len];
      Node curr = head;
      while (curr != null) {
        res[i++] = curr.val;
        curr = curr.next;
      }
      return res;
    }

    // d 1 2 3 5
    // l 4
    // i 2
    @Override
    public int get_at(int i) {
      int mid = len / 2;
      if (mid > i) {
        Node curr = head;
        for (int j = 0; j < i; j++) {
          curr = curr.next;
        }
        return curr.val;
      } else {
        Node curr = tail;
        // 3
        for (int j = len - 1; j > i; j--) {
          curr = curr.prev;
        }
        return curr.val;
      }
      // d 1 2 3 5
      // i 2

    }

    // 1 2 3 4
    // 1 5
    // 1 5 3 4
    // 2 5
    // 1 2 5 4
    @Override
    public void set_at(int i, int n) {
      int mid = len / 2;
      if (mid > i) {
        // from head
        Node curr = head;
        for (int j = 0; j < i; j++) {
          curr = curr.next;
        }
        curr.val = n;
      } else {
        // from tail
        Node curr = tail;
        for (int j = len - 1; j > i; j--) {
          curr = curr.prev;
        }
        curr.val = n;
      }
    }

    //endregion

    //region Dynamic
    @Override
    public void insert_at(int i, int x) {
      if (i == 0) {
        insert_first(x);
        return;
      }
      if (i == len - 1) {
        insert_last(x);
        return;
      }

      if (i < len / 2) {
        // 3 5 4
        // 3, 9, 5, 4
        Node curr = head;
        for (int j = 0; j < i; j++) {
          curr = curr.next;
        }
        Node toInsert = new Node(x);
        // curr = 5
        toInsert.next = curr;
        toInsert.prev = curr.prev;
        curr.prev.next = toInsert;
        curr.prev = toInsert;
      } else {
        // 3 5 4
        // 1, 9
        // 3, 9, 5, 4
        Node curr = tail;
        for (int j = len - 1; j > i; j--) {
          curr = curr.prev;
        }
        // curr = 5
        Node toInsert = new Node(x);
        toInsert.next = curr;
        toInsert.prev = curr.prev;
        curr.prev.next = toInsert;
        curr.prev = toInsert;
      }
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
      if (i < len / 2) {
        // 0, 3, 9, 5, 4
        // 2
        // 0 3 5 4
        Node curr = head;
        for (int j = 0; j < i; j++) {
          curr = curr.next;
        }
        // curr = 9
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        curr.prev = null;
        curr.next = null;
      } else {
        // 0, 3, 9, 5, 4
        // 2
        // 0, 3, 5, 4
        Node curr = tail;
        for (int j = len - 1; j > i; j--) {
          curr = curr.prev;
        }
        // curr = 9
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        curr.prev = null;
        curr.next = null;
      }
      len--;
    }

    @Override
    public void insert_first(int x) {
      if (head == null) {
        head = new Node(x);
        tail = head;
        len++;
        return;
      }
      Node toInsert = new Node(x);
      head.prev = toInsert;
      toInsert.next = head;
      head = toInsert;
      len++;
    }

    @Override
    public void insert_last(int x) {
      Node toInsert = new Node(x);
      if (len == 0) {
        head = toInsert;
        tail = head;
      } else {
        tail.next = toInsert;
        toInsert.prev = tail;
        tail = toInsert;
      }
      len++;
    }

    @Override
    public void delete_first() {
      if (head == null) {
        return;
      }
      if (len == 1) {
        head = null;
        tail = null;
        len--;
        return;
      }
      head.next.prev = null;
      head = head.next;
      len--;
    }

    @Override
    public void delete_last() {
      if (len == 0) {
        return;
      }
      if (len == 1) {
        head = null;
        tail = null;
        len--;
        return;
      }
      Node prev = tail.prev;
      tail.prev = null;
      prev.next = null;
      tail = prev;
      len--;
    }
    //endregion
  }
}
