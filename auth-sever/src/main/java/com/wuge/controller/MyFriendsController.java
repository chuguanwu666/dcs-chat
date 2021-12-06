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

import com.wuge.entity.MyFriendsEntity;
import com.wuge.service.MyFriendsService;




/**
 * 
 *
 * @author wuge
 * @email 1712378864@qq.com
 * @date 2021-12-03 17:09:40
 */
@RestController
@RequestMapping("wuge/myfriends")
public class MyFriendsController {
    @Autowired
    private MyFriendsService myFriendsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wuge:myfriends:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = myFriendsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("wuge:myfriends:info")
    public R info(@PathVariable("id") String id){
		MyFriendsEntity myFriends = myFriendsService.getById(id);

        return R.ok().put("myFriends", myFriends);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   // @RequiresPermissions("wuge:myfriends:save")
    public R save(@RequestBody MyFriendsEntity myFriends){
		myFriendsService.save(myFriends);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   // @RequiresPermissions("wuge:myfriends:update")
    public R update(@RequestBody MyFriendsEntity myFriends){
		myFriendsService.updateById(myFriends);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("wuge:myfriends:delete")
    public R delete(@RequestBody String[] ids){
		myFriendsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
