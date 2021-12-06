package com.wuge.service.impl;


import com.wuge.common.utils.PageUtils;
import com.wuge.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.wuge.dao.FriendsRequestDao;
import com.wuge.entity.FriendsRequestEntity;
import com.wuge.service.FriendsRequestService;


@Service("friendsRequestService")
public class FriendsRequestServiceImpl extends ServiceImpl<FriendsRequestDao, FriendsRequestEntity> implements FriendsRequestService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FriendsRequestEntity> page = this.page(
                new Query<FriendsRequestEntity>().getPage(params),
                new QueryWrapper<FriendsRequestEntity>()
        );

        return new PageUtils(page);
    }

}