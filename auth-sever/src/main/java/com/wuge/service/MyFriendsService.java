package com.wuge.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.wuge.common.utils.PageUtils;
import com.wuge.entity.MyFriendsEntity;

import java.util.Map;

/**
 * 
 *
 * @author wuge
 * @email 1712378864@qq.com
 * @date 2021-12-03 17:09:40
 */
public interface MyFriendsService extends IService<MyFriendsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

