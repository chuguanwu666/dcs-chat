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
@TableName("friends_request")
public class FriendsRequestEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String sendUserId;
	/**
	 * 
	 */
	private String acceptUserId;
	/**
	 * 发送请求的事件
	 */
	private Date requestDateTime;

}
