package com.wuge.controller;

import java.util.Arrays;
import java.util.Map;


import com.wuge.common.utils.PageUtils;

import com.wuge.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuge.entity.UsersEntity;
import com.wuge.service.UsersService;




/**
 * 
 *
 * @author wuge
 * @email 1712378864@qq.com
 * @date 2021-12-03 17:09:40
 */
@RestController
@RequestMapping("wuge/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wuge:users:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = usersService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("wuge:users:info")
    public R info(@PathVariable("id") String id){
		UsersEntity users = usersService.getById(id);

        return R.ok().put("users", users);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   // @RequiresPermissions("wuge:users:save")
    public R save(@RequestBody UsersEntity users){
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
		usersService.save(users);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   // @RequiresPermissions("wuge:users:update")
    public R update(@RequestBody UsersEntity users){
		usersService.updateById(users);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("wuge:users:delete")
    public R delete(@RequestBody String[] ids){
		usersService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
