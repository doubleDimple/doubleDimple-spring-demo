CREATE TABLE `price` (
        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        `price` DECIMAL NOT NULL DEFAULT 0 COMMENT '价格',
        `product_id` bigint NOT NULL DEFAULT 0 COMMENT '产品id',
        `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        PRIMARY KEY (`id`),
        UNIQUE KEY `index_id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;