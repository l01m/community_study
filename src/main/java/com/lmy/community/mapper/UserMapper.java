package com.lmy.community.mapper;

import com.lmy.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 *添加了@Mapper注解之后这个接口在编译时会生成相应的实现类
 *需要注意的是：这个接口中不可以定义同名的方法，因为会生成相同的id
 *也就是说这个接口是不支持重载的
 *
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(String token);
}
