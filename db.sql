-- 用户表
CREATE TABLE `cloud_music_player`.`user` (
                                             `user_id` INT NOT NULL AUTO_INCREMENT ,
                                             `user_name` VARCHAR(45) NOT NULL,
                                             `user_email` VARCHAR(45) NULL COMMENT '忘记密码时候要输入邮箱和用户名才可以修改密码',
                                             `user_password` VARCHAR(128) NOT NULL,
                                             `gmt_created` DATETIME NULL,
                                             `gmt_modified` DATETIME NULL,
                                             `is_deleted` INT NULL DEFAULT 0,
                                             PRIMARY KEY (`user_id`));
-- 音乐表
CREATE TABLE `cloud_music_player`.`music` (
                                              `music_id` INT NOT NULL AUTO_INCREMENT ,
                                              `user_id` INT NOT NULL,
                                              `music_name` VARCHAR(128) NOT NULL,
                                              `music_singer` VARCHAR(45) NULL DEFAULT 'unknown' COMMENT '非必填项，默认unknown',
                                              `music_time` INT NULL COMMENT '音乐时长，以秒为单位',
                                              `music_lyrics` TEXT NULL DEFAULT 'NULL',
                                              `music_path` VARCHAR(45) NOT NULL COMMENT '音乐的存储路径',
                                              `gmt_created` DATETIME NULL,
                                              `is_deleted` INT NULL DEFAULT 0,
                                              PRIMARY KEY (`music_id`));
-- 歌单表
CREATE TABLE `cloud_music_player`.`music_list` (
                                                   `music_list_id` INT NOT NULL AUTO_INCREMENT ,
                                                   `user_id` INT NOT NULL,
                                                   `music_list_name` VARCHAR(45) NOT NULL,
                                                   `gmt_created` DATETIME NULL,
                                                   `is_deleted` INT NULL DEFAULT 0,
                                                   PRIMARY KEY (`music_list_id`))
    COMMENT = '歌单列表';
-- 歌单里面的具体音乐
CREATE TABLE `cloud_music_player`.`music_list_detail` (
                                                          `music_list_detail_id` INT NOT NULL AUTO_INCREMENT ,
                                                          `music_list_id` INT NOT NULL,
                                                          `music_id` INT NOT NULL,
                                                          `gmt_created` DATETIME NULL DEFAULT NULL,
                                                          `is_deleted` INT NULL DEFAULT 0,
                                                          PRIMARY KEY (`music_list_detail_id`))
    COMMENT = '每个歌单具体的歌';
-- 背景图片
CREATE TABLE `cloud_music_player`.`background_picture` (
                                                           `background_picture_id` INT NOT NULL AUTO_INCREMENT ,
                                                           `user_id` INT NOT NULL,
                                                           `background_picture_path` VARCHAR(128) NOT NULL,
                                                           `is_deleted` INT NULL DEFAULT 0,
                                                               PRIMARY KEY (`background_picture_id`))
    COMMENT = '主页的背景图片';