package com.example.nacostest.mapper;

import com.example.nacostest.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2023/3/14 20:27
 */
@Mapper
@Repository
public interface UserMapper {
    User getUserInfoByName(String name);
}
