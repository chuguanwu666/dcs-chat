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

import com.wuge.entity.FriendsRequestEntity;
import com.wuge.service.FriendsRequestService;




/**
 * 
 *
 * @author wuge
 * @email 1712378864@qq.com
 * @date 2021-12-03 17:09:40
 */
@RestController
@RequestMapping("wuge/friendsrequest")
public class FriendsRequestController {
    @Autowired
    private FriendsRequestService friendsRequestService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wuge:friendsrequest:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = friendsRequestService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("wuge:friendsrequest:info")
    public R info(@PathVariable("id") String id){
		FriendsRequestEntity friendsRequest = friendsRequestService.getById(id);

        return R.ok().put("friendsRequest", friendsRequest);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   // @RequiresPermissions("wuge:friendsrequest:save")
    public R save(@RequestBody FriendsRequestEntity friendsRequest){
		friendsRequestService.save(friendsRequest);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   // @RequiresPermissions("wuge:friendsrequest:update")
    public R update(@RequestBody FriendsRequestEntity friendsRequest){
		friendsRequestService.updateById(friendsRequest);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("wuge:friendsrequest:delete")
    public R delete(@RequestBody String[] ids){
		friendsRequestService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
