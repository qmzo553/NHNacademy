package src;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArrayListTestProxy implements PerformanceTestable {

    private final List<PerformanceTestable> performanceTestables;

    public ArrayListTestProxy() {
        performanceTestables = new ArrayList<>();
    }

    public void addPerformanceTestable(PerformanceTestable performanceTestable) {
        performanceTestables.add(performanceTestable);
    }

    @Override
    public void test() {
        for(PerformanceTestable performanceTestable : performanceTestables) {
            if(hasStopWatch(performanceTestable)) {
                long start = System.currentTimeMillis();
                System.out.println("##시간측정 시작 : " + start);
                performanceTestable.test();
                long end = System.currentTimeMillis();
                System.out.println("##시간측정 종료 : " + end);
                long result = (end - start) / 1000;
                System.out.println(performanceTestable.getClass().getName() + " 실행시간(초) : " + result);
            }
        }
    }

    private boolean hasStopWatch(PerformanceTestable performanceTestable) {
        for(Method method : performanceTestable.getClass().getDeclaredMethods()) {
            StopWatch stopWatch = method.getAnnotation(StopWatch.class);

            if(Objects.nonNull(stopWatch)) {
                return true;
            }
        }

        return false;
    }
}
