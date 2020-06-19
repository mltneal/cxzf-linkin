CREATE TABLE `key_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` varchar(16)  NOT NULL COMMENT 'appid',
  `pri_key` varchar(2048)  NOT NULL COMMENT '平台私钥',
  `pub_key` varchar(2048) NOT NULL COMMENT 'app公钥',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;