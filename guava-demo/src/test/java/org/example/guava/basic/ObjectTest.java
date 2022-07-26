package org.example.guava.basic;

import com.google.common.base.MoreObjects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qinfengsa
 * @date 2022/01/07 10:35
 */
public class ObjectTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectTest.class);

    @Test public void testToString() {
        ObjectMain.User user = new ObjectMain.User();
        user.setId(1);
        user.setName("qin");
        user.setAge(33);
        String strUser = user.toString();
        String stringHelper =
            MoreObjects.toStringHelper(user).add("id", 1).add("name", "'qin'").add("age", 33).toString();
        LOGGER.debug("user:{}", strUser);
        LOGGER.debug("user:{}", stringHelper);
        Assertions.assertEquals(stringHelper, strUser);
    }
}
