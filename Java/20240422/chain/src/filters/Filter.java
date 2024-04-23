package src.filters;

import src.FilterChain;
import src.request.Request;

public interface Filter {
    void doFilter(Request request, FilterChain filterChain);
}
