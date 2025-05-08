package org.goodday.mybatis.service;

import lombok.RequiredArgsConstructor;
import org.goodday.mybatis.Enum.DataSourceType;
import org.goodday.mybatis.entity.User;
import org.goodday.mybatis.mapper.UserMapper;
import org.goodday.mybatis.muldatasource.MutiDataSourceAnnotaton;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MultiDataSourceService {

    private final UserMapper userMapper;

    @MutiDataSourceAnnotaton(value = DataSourceType.SLAVE)
    public List<User> queryAll() {
        User userById = userMapper.selectByPrimaryKey(1L);
        return userMapper.selectList(null);
    }
}
