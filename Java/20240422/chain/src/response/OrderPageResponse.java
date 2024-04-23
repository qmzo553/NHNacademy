package src.response;

import src.Member;
import src.request.Request;

public class OrderPageResponse implements Response {

    @Override
    public void doResponse(Request request) {
        System.out.println("###### src.response:src.response.OrderPageResponse #####");
        Member member = (Member) request.get("member");
        System.out.println("아이디:" + member.getId());
        System.out.println("이름:" + member.getName());
        System.out.println("등급:" + Member.Role.USER);
        System.out.println("##### 구매 물품 #####");
        System.out.println("사과");
        System.out.println("배");
        System.out.println("귤");
        System.out.println("do something ... USER ...");
    }
}
