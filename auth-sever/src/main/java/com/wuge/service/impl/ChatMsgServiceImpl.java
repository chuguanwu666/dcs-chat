package com.wuge.service.impl;




import com.wuge.common.utils.PageUtils;
import com.wuge.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



import com.wuge.dao.ChatMsgDao;
import com.wuge.entity.ChatMsgEntity;
import com.wuge.service.ChatMsgService;


@Service("chatMsgService")
public class ChatMsgServiceImpl extends ServiceImpl<ChatMsgDao, ChatMsgEntity> implements ChatMsgService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ChatMsgEntity> page = this.page(
                new Query<ChatMsgEntity>().getPage(params),
                new QueryWrapper<ChatMsgEntity>()
        );

        return new PageUtils(page);
    }

}