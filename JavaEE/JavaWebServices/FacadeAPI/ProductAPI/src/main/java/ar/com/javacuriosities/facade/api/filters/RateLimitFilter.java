package ar.com.javacuriosities.facade.api.filters;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;

/*
 * Es recomendable tener rate limit en nuestros servicios
 */
@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final Bucket bucket;

    public RateLimitFilter(@Value("${rate-limit.refill.amount}") int tokens,
                           @Value("${rate-limit.refill.interval}") int period,
                           @Value("${rate-limit.capacity}") int capacity) {
        Refill refill = Refill.greedy(tokens, Duration.ofMillis(period));
        Bandwidth limit = Bandwidth.classic(capacity, refill);
        bucket = Bucket4j.builder().addLimit(limit).build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().append("Too many requests");
        }
    }
}
