package com.wuge.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author wuge
 * @email 1712378864@qq.com
 * @date 2021-12-03 17:09:40
 */
@Data
@TableName("users")
public class UsersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 用户名，账号，慕信号
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 我的头像，如果没有默认给一张
	 */
	private String faceImage;
	/**
	 * 
	 */
	private String faceImageBig;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 新用户注册后默认后台生成二维码，并且上传到阿里云oss
	 */
	private String qrcode;
	/**
	 * 
	 */
	private String cid;

}
