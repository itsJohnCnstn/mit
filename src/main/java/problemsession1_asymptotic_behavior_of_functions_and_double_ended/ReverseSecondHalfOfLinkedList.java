package problemsession1_asymptotic_behavior_of_functions_and_double_ended;

public class ReverseSecondHalfOfLinkedList {


  // create node class
  // build list
  // traverse it

  // find mid
  // reverse from mid
  // point mid prev to new node 3:

  // 1 2 3
  // 2
  // 3 2
  // 1 3 2
  static void main() {
    Node head = new Node(1);
    Node curr = head;
//    for (int i = 0; i < 2; i++) {
    for (int i = 0; i < 4; i++) {
      Node next = new Node(i + 2);
      curr.next = next;
      curr = curr.next;
    }
    System.out.println("Initial list:");
    traverse(head);
    int size = findSize(head);
    System.out.println("Size - expected: 3, actual: " + size);

    int midI = size / 2;
    System.out.println("Mid - expected: 1, actual: " + midI);

    Node mid = head;
    for (int i = 0; i < midI; i++) {
      mid = mid.next;
    }

    //reverse
    Node newMid = reverse(mid);
    System.out.println("Reversed from mid:");
    traverse(newMid);

//    traverse(mid);

    Node prev = head;
    while (prev.next != mid) {
      prev = prev.next;
    }
    prev.next = newMid;
    System.out.println("Result:");
    traverse(head);
  }

  // 1
  // 1 2
  static Node reverse(Node head) {
    if (head == null || head.next == null) {
      return head;
    }
    Node newHead = reverse(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
  }

  // 1, 0
  // 1, 2
  // 1, 2, 3 = 1
  static int findSize(Node head) {
    if (head == null) {
      return 0;
    }
    return findSize(head.next) + 1;
  }

  static void traverse(Node head) {
    if (head == null) {
      return;
    }
    System.out.println(head.val);
    traverse(head.next);
  }

  static class Node {
    Node next;
    int val;

    public Node(int val) {
      this.val = val;
    }
  }

}
