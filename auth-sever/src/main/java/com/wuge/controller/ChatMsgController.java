package com.wuge.controller;

import java.util.Arrays;
import java.util.Map;


import com.wuge.common.utils.PageUtils;

import com.wuge.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuge.entity.ChatMsgEntity;
import com.wuge.service.ChatMsgService;




/**
 * 
 *
 * @author wuge
 * @email 1712378864@qq.com
 * @date 2021-12-03 17:09:40
 */
@RestController
@RequestMapping("wuge/chatmsg")
public class ChatMsgController {
    @Autowired
    private ChatMsgService chatMsgService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wuge:chatmsg:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = chatMsgService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("wuge:chatmsg:info")
    public R info(@PathVariable("id") String id){
		ChatMsgEntity chatMsg = chatMsgService.getById(id);

        return R.ok().put("chatMsg", chatMsg);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   // @RequiresPermissions("wuge:chatmsg:save")
    public R save(@RequestBody ChatMsgEntity chatMsg){
		chatMsgService.save(chatMsg);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   // @RequiresPermissions("wuge:chatmsg:update")
    public R update(@RequestBody ChatMsgEntity chatMsg){
		chatMsgService.updateById(chatMsg);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("wuge:chatmsg:delete")
    public R delete(@RequestBody String[] ids){
		chatMsgService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
