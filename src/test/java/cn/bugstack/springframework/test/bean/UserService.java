package cn.bugstack.springframework.test.bean;

/**
 * @description:
 * @author：肖杰
 * @date: 2023-03-21
 */
public class UserService {

    public void queryUserInfo(){
        System.out.println("查询用户信息");
    }

    public UserService(String name) {
    }
}
