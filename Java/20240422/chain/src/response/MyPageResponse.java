package src.response;

import src.Member;
import src.request.Request;

public class MyPageResponse implements Response {

    @Override
    public void doResponse(Request request) {
        System.out.println("###### src.response:src.response.MyPageResponse #####");
        Member member = (Member) request.get("member");
        System.out.println("아이디:" + member.getId());
        System.out.println("이름:" + member.getName());
        System.out.println("등급:" + Member.Role.USER);
        System.out.println("주소:" + "광주 광역시 조선대 NHN아카데미");
        System.out.println("do something ... USER ...");
    }
}
