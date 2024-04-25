package src;

import java.util.LinkedList;
import java.util.List;

public class LinkedListTest implements PerformanceTestable {

    @StopWatch
    @Override
    public void test() {
        List<Integer> integerList = new LinkedList<>();
        for(int i = 0; i < 100_000_000; i++) {
            integerList.add(i);
        }
    }
}
