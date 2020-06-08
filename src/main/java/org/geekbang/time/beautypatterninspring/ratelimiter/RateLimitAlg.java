package org.geekbang.time.beautypatterninspring.ratelimiter;

import org.geekbang.time.beautypatterninspring.exception.InternalErrorException;

public interface RateLimitAlg {

    boolean tryAcquire() throws InternalErrorException;

}
