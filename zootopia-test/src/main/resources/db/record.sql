CREATE TABLE IF NOT EXISTS record(
        id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
        create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
        modify_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
        operator VARCHAR(64) COMMENT '操作者',
        operate_method VARCHAR(32) COMMENT '方法',
        request_data TEXT COMMENT '请求数据',
        response_data TEXT COMMENT '返回结果',
        response_time BIGINT UNSIGNED COMMENT '响应耗时',
        channel VARCHAR(32) COMMENT '来源',
        PRIMARY KEY (id),
        INDEX idx_createtime(create_time),
        INDEX idx_channel(channel),
        INDEX idx_operator(operator)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='记录表';