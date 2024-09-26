package CustomMap;

import java.util.LinkedList;
import java.util.List;

public class CustomHashMap<K, V>{
    private final int DEFAULT_SIZE = 4;
    private int nodesCount;
    private int bucketSize;
    private LinkedList<Node>[] buckets;

    public CustomHashMap(){
        this.nodesCount = 0;
        this.bucketSize = DEFAULT_SIZE;
        this.buckets = new LinkedList[DEFAULT_SIZE];
        for(int i=0;i< buckets.length;i++) buckets[i] = new LinkedList<>();
    }

    private int getBucketIndex(K key){
        return Math.abs(key.hashCode())%bucketSize;
    }

    private void rehash(){
        LinkedList<Node>[] oldBuckets = buckets;

        this.bucketSize *= 2;
        this.nodesCount = 0;
        this.buckets = new LinkedList[bucketSize];
        for(int i=0;i<bucketSize;i++) buckets[i] = new LinkedList<>();

        for(int i=0;i<oldBuckets.length;i++){
            LinkedList<Node> ll = oldBuckets[i];
            for(Node node : ll){
                K key = (K) node.getKey();
                V value = (V) node.getValue();
                put(key, value);
            }
        }
    }

    private int getKeyIndexInList(K key, int bucketIdx){
        LinkedList<Node> ll = buckets[bucketIdx];
        for(int i=0;i<ll.size();i++){
            Node node = ll.get(i);
            if(node.getKey().equals(key)){
                return i;
            }
        }
        return -1;
    }

    public synchronized void put(K key, V value){
        int bucketIdx = getBucketIndex(key);

        int keyIdx = getKeyIndexInList(key, bucketIdx);

        if(keyIdx == -1){
            buckets[bucketIdx].add(new Node(key, value));
            this.nodesCount++;
        }else{
            buckets[bucketIdx].get(keyIdx).setValue(value);
        }

        double lambda = ((double) this.nodesCount / this.bucketSize);
        if(lambda > 1.0){
            System.out.println("Rehashing in progress");
            rehash();
        }
    }

    public V get(K key){
        int bucketIdx = getBucketIndex(key);

        synchronized (buckets[bucketIdx]){
            int keyIdx = getKeyIndexInList(key, bucketIdx);

            if(keyIdx != -1) return (V) buckets[bucketIdx].get(keyIdx).getValue();

            System.out.println("key does not exists: " + key);
            return null;
        }
    }

    public void remove(K key){
        int bucketIdx = getBucketIndex(key);

        synchronized (buckets[bucketIdx]){
            int keyIdx = getKeyIndexInList(key, bucketIdx);

            if(keyIdx != -1) buckets[bucketIdx].remove(keyIdx);
        }
    }

    public int size(){
        return this.nodesCount;
    }

    public boolean containsKey(K key){
        int bucketIdx = getBucketIndex(key);

        synchronized (buckets[bucketIdx]){
            int keyIdx = getKeyIndexInList(key, bucketIdx);

            if(keyIdx != -1) return true;
            return false;
        }
    }

    public List<K> keySet(){
        List<K> keys = new LinkedList<>();
        for(int i=0;i<bucketSize;i++){
            LinkedList<Node> ll = buckets[i];
            for(Node node : ll){
                K key = (K) node.getKey();
                keys.add(key);
            }
        }

        return keys;
    }
}
