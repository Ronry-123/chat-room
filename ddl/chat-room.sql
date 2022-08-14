create table `user`(
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `db_create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '数据库插入时间',
    `db_modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '数据库更新时间',
    `chat_uid` bigint(20) unsigned unique NOT NULL COMMENT 'chat uid',
    `email` varchar(127) unique NOT NULL COMMENT '用户邮箱',
    `password` varchar(255) DEFAULT NULL COMMENT '密码',
    `username` varchar(255) DEFAULT NULL COMMENT '用户名',
    `nickname` varchar(255) DEFAULT NULL COMMENT '用户名',
    `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
    `delete_uid` bigint(20) unsigned DEFAULT NULL COMMENT '删除id',
    unique key `uni_uid_delete` (`uid`, `delete_uid`),
    primary key (`id`)
)ENGINE=InnoDB  comment 'user';

create table `room`(
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `db_create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '数据库插入时间',
    `db_modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '数据库更新时间',
    `room_id` bigint(20) unsigned unique NOT NULL COMMENT '房间id',
    `name` varchar(255) NOT NULL COMMENT '房间名称(FRIEND, GROUP, CHANNEL)',
    `type` tinyint(4) unsigned NOT NULL COMMENT '房间类型',
    `max_num` int unsigned default 500 COMMENT '房间最大人数',
    `curr_num` int unsigned default 1 COMMENT '房间当前人数',
    `owner_chat_uid` bigint(20) unsigned NOT NULL COMMENT '房间创建者uid',
    `can_search` tinyint(1) unsigned default 1 COMMENT '是否公开, 默认公开',
    `delete_room_id` bigint(20) unsigned DEFAULT NULL COMMENT '删除 room id',
    unique key `uni_name_delete` (`name`, `delete_room_id`),
    primary key (`id`)
)ENGINE=InnoDB  comment 'room';

create table `room_member`(
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `db_create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '数据库插入时间',
    `db_modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '数据库更新时间',
    `room_id` bigint(20) unsigned NOT NULL COMMENT '房间id',
    `uid` bigint(20) unsigned NOT NULL COMMENT '用户uid',
    `admin` tinyint(1) unsigned default 0 COMMENT '是否管理员',
    primary key (`id`)
)ENGINE=InnoDB  comment 'room';

create table `room_message`(
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `db_create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '数据库插入时间',
    `db_modify_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '数据库更新时间',
    `sequence_id` bigint(20) unsigned NOT NULL COMMENT '消息序列id, 每个房间消息序列id严格递增',
    `room_id` bigint(20) unsigned NOT NULL COMMENT '房间id',
    `sender_chat_uid` bigint(20) unsigned NOT NULL COMMENT '用户uid',
    `content` varchar(255) NOT NULL COMMENT '消息内容',
    `type` tinyint(4) unsigned NOT NULL COMMENT '消息类型(文本, 图片, 语音, 视频, 文件)',
    unique index `uni_room_seq_id`(`room_id`, `sequence_id`),
    primary key (`id`)
)ENGINE=InnoDB  comment 'room';