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
@TableName("chat_msg")
public class ChatMsgEntity implements Serializable {
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
	 * 
	 */
	private String msg;
	/**
	 * 消息是否签收状态
1：签收
0：未签收

	 */
	private Integer signFlag;
	/**
	 * 发送请求的事件
	 */
	private Date createTime;

}
