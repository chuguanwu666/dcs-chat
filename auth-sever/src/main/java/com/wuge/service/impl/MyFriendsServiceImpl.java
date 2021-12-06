package com.wuge.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuge.common.utils.PageUtils;
import com.wuge.common.utils.Query;

import com.wuge.dao.MyFriendsDao;
import com.wuge.entity.MyFriendsEntity;
import com.wuge.service.MyFriendsService;


@Service("myFriendsService")
public class MyFriendsServiceImpl extends ServiceImpl<MyFriendsDao, MyFriendsEntity> implements MyFriendsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MyFriendsEntity> page = this.page(
                new Query<MyFriendsEntity>().getPage(params),
                new QueryWrapper<MyFriendsEntity>()
        );

        return new PageUtils(page);
    }

}