package com.xiongzehua.freetalk.utils;

import com.xiongzehua.freetalk.entity.Talk;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

/**
 * @author xiongzehua
 * @date 2019/6/29 21:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RequiredTest {

    @Test
    public void test1() throws Exception {
        Talk talk = new Talk();
        talk.setId(5L);
        List<String> list = CheckUtil.validate(talk);
        StringBuilder str = new StringBuilder();
        for (String value : list) {
            str.append(value).append(",");
        }
        System.out.println(str.toString());
    }
}