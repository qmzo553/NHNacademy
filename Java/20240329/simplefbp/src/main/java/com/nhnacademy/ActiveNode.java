package com.nhnacademy;

public abstract class ActiveNode extends Node {

    protected ActiveNode(String name, String identifier) {
        super(name, identifier);
    }
    
    // 동작 시작
    public abstract void start();

    // 동작 시작 후 내부적인 초기화 과정
    abstract void initialize();

    // 기능 수행
    abstract void perform();

    // main 에서 시간내에 업무 완료 후 idle에서 남는 시간 대기
    abstract void idle();

    // 리소스 해지
    abstract void finalizing();
    
    // 실행 종료
    abstract void terminated();
}
