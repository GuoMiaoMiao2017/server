package com.xiongzehua.freetalk.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiongzehua.freetalk.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author gmm
 * @date 2019-06-26 21:15:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    private UserMapper userMapper;

    @Autowired
    private void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Test
    public void testAdd() {
        int result = userMapper.insert(new User().setUserName("果苗").setEmail("123456@qq.com").setPassword("123456").setPhone("18578234323")
                .setAvatarUrl("http").setSignInIp("127.0.0.1").setToken("FSGFDSG").setSignInTime(LocalDateTime.now()).setCreateTime(LocalDateTime.now()).setUpdateTime(LocalDateTime.now()));
        System.out.println("result = " + result);
    }

    @Test
    public void testUpdate() {
        int result = userMapper.updateById(new User().setId(1L).setUserName("过么么么么"));
        System.out.println("result = " + result);
    }

    @Test
    public void testSelect() {
        System.out.println(userMapper.selectById(1L));
    }

    @Test
    public void testDelete() {
        int result = userMapper.deleteById(2L);
        System.out.println("result = " + result);
    }

    @Test
    public void test1() {
        QueryWrapper condition = new QueryWrapper();
        condition.eq("user_name", "过么么么么");
        System.out.println(userMapper.selectList(condition));
    }
}
