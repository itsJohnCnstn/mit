package lectures.l4_hashing.chaining;

import static lectures.l4_hashing.chaining.Chaining_HashTable.HashTable.printHashTable;

import java.lang.reflect.Array;

public class Chaining_HashTable {

  public static void main(String[] args) {
    HashTable<Integer, String> hashTable = new HashTable<>();
    System.out.println("Empty:");
    printHashTable(hashTable);
    System.out.println();

    hashTable.put(111, "pawa");
    hashTable.put(231, "miha");
    hashTable.put(555, "nastya");

    System.out.println("Filled:");
    printHashTable(hashTable);
    System.out.println();

    System.out.println("Get(K key):");
    System.out.println("get(111) expected: pawa, actual: " + hashTable.get(111));
    System.out.println("size: " + hashTable.size);
    System.out.println();

    System.out.println("Remove(K key):");
    System.out.println("remove(111) expected: pawa, actual: " + hashTable.remove(111));
    System.out.println("size: " + hashTable.size);
    System.out.println();

    System.out.println("Updated:");
    printHashTable(hashTable);

  }

  // chaining
  // HashItem
  // HashTable
  // printHashTable
  // put
  // get
  // remove

  static class HashTable<K, V> {

    private static final int MAX_SIZE = 5;
    private HashItem<K, V>[] hashTable;
    private int size;

    public HashTable() {
      hashTable = (HashItem<K, V>[]) Array.newInstance(HashItem.class, MAX_SIZE);
    }

    void put(K key, V val) {
      int index = hash(key);
      HashItem<K, V> item = hashTable[index];
      // update existing
      while (item != null) {
        if (key.equals(item.getKey())) {
          item.setVal(val);
          return;
        }
        item = item.getNextHashItem();
      }
      // create new
      HashItem<K, V> hashItem = new HashItem<>(key, val);
      hashItem.setNextHashItem(hashTable[index]);
      hashTable[index] = hashItem;
      size++;
    }

    V get(K key) {
      // get index
      // get item
      // if found
      // if not

      int index = hash(key);
      HashItem<K, V> item = hashTable[index];
      while (item != null) {
        if (key.equals(item.getKey())) {
          return item.getVal();
        }
        item = item.getNextHashItem();
      }
      return null;
    }

    V remove(K key) {
      // get index
      // get item
      // create prev
      // find item to remove and prev
      // if prev is null then it's the first item and we can just null it
      // if it's not we need to update pointers
      int index = hash(key);
      HashItem<K, V> item = hashTable[index];
      HashItem<K, V> prev = null;
      while (item != null) {
        if (key.equals(item.getKey())) {
          break;
        }
        prev = item;
        item = item.getNextHashItem();
      }

      if (item == null) {
        return null;
      }

      if (prev == null) {
        hashTable[index] = null;
      } else {
        prev.setNextHashItem(item.getNextHashItem());
        item.setNextHashItem(null);
      }
      size--;
      return item.getVal();
    }

    int hash(K key) {
      return key.hashCode() % MAX_SIZE;
    }

    static <K, V> void printHashTable(HashTable<K, V> hashTable) {
      for (HashItem<K, V> item : hashTable.hashTable) {
        while (item != null) {
          System.out.println("k: " + item.getKey() + " v: " + item.getVal());
          item = item.getNextHashItem();
        }
      }
    }
  }

  static class HashItem<K, V> {

    private final K key;
    private V val;
    private HashItem<K, V> nextHashItem;

    public HashItem(K key, V val) {
      this.key = key;
      this.val = val;
    }

    public K getKey() {
      return key;
    }

    public V getVal() {
      return val;
    }

    public void setVal(V val) {
      this.val = val;
    }

    public HashItem<K, V> getNextHashItem() {
      return nextHashItem;
    }

    public void setNextHashItem(HashItem<K, V> nextHashItem) {
      this.nextHashItem = nextHashItem;
    }
  }


}
