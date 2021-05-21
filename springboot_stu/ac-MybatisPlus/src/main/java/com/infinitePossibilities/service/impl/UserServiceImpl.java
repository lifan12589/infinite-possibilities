package com.infinitePossibilities.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infinitePossibilities.model.entity.UserEntity;
import com.infinitePossibilities.mapper.UserMapper;
import com.infinitePossibilities.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 154594742@qq.com
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
