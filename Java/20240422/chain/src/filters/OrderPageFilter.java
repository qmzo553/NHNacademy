package src.filters;

import src.FilterChain;
import src.Member;
import src.request.Request;

import java.util.Objects;

public class OrderPageFilter implements Filter {

    @Override
    public void doFilter(Request request, FilterChain filterChain) {
        if(request.getPath().equals("/order")){
            Member member = (Member) request.get("member");
            if(Objects.nonNull(member)) {
                if(!member.hasRole(Member.Role.NONE)) {
                    System.out.println("path : " + request.getPath() + " : has " + member.getRole());
                    filterChain.doFilter(request);
                } else {
                    System.out.println("path : " + request.getPath() + " : has NO " + member.getRole());
                }
            } else {
                System.out.println("OrderPageCheckFilter : 다음 필터로 넘김! ");
                filterChain.doFilter(request);
            }
        }
    }
}
