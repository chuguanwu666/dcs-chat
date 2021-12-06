package com.wuge.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.wuge.common.utils.PageUtils;
import com.wuge.entity.UsersEntity;

import java.util.Map;

/**
 * 
 *
 * @author wuge
 * @email 1712378864@qq.com
 * @date 2021-12-03 17:09:40
 */
public interface UsersService extends IService<UsersEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

