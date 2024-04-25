package src;

public class ArrayListTestMain {

    public static void main(String[] args) {
        ArrayListTest arrayListTest = new ArrayListTest();
        LinkedListTest linkedListTest = new LinkedListTest();
        ArrayListTestProxy arrayListTestProxy = new ArrayListTestProxy();
        arrayListTestProxy.addPerformanceTestable(arrayListTest);
        arrayListTestProxy.addPerformanceTestable(linkedListTest);
        arrayListTestProxy.test();
    }
}
