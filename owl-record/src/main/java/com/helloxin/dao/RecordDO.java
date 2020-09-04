package com.helloxin.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "record")
public class RecordDO implements Serializable {
    private static final long serialVersionUID = 7205430922339013650L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Date createTime;

    private Date modifyTime;

    private String operator;

    private String operateMethod;

    private String requestData;

    private String responseData;

    private Long responseTime;

    private String channel;
}
