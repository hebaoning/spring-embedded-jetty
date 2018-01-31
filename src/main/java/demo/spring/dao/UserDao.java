package demo.spring.dao;

import demo.spring.model.User;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Select("select * from users where id = #{id}")
    User selectUser(Integer id);
}
