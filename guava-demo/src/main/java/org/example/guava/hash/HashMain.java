package org.example.guava.hash;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qinfengsa
 * @date 2022/02/09 15:59
 */
public class HashMain {

    private final static Logger LOGGER = LoggerFactory.getLogger(HashMain.class);

    public static void main(String[] args) {
        HashFunction hf = Hashing.goodFastHash(32);
        Long id = 1L;
        boolean isActive = true;
        HashCode hc = hf.newHasher().putLong(id).putBoolean(isActive).hash();
        LOGGER.debug("hash:{}", hc.hashCode());
    }
}
