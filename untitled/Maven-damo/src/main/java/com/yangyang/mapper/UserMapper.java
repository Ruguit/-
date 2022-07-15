package com.yangyang.mapper;

import com.yangyang.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> selectByCardId(@Param("cardId") String cardId);
    void add(User user);
    void updateMoneyByCardId(@Param("cardId") String cardId,@Param("money") double money);

}
