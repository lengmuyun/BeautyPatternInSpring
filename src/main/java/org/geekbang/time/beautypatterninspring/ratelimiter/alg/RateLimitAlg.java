package org.geekbang.time.beautypatterninspring.ratelimiter.alg;

import org.geekbang.time.beautypatterninspring.exception.InternalErrorException;

public interface RateLimitAlg {

    boolean tryAcquire() throws InternalErrorException;

}