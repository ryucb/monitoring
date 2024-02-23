CREATE TABLE `host` (
	`seq` INT(10) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(20) NOT NULL COMMENT '서버이름' COLLATE 'utf8mb4_0900_ai_ci',
	`ip` VARCHAR(15) NOT NULL COMMENT '서버ip' COLLATE 'utf8mb4_0900_ai_ci',
	`state` VARCHAR(50) NOT NULL DEFAULT '0' COMMENT '서버상태' COLLATE 'utf8mb4_0900_ai_ci',
	`state_alive_last_time` TIMESTAMP NULL DEFAULT (now()) COMMENT '최종유효시간',
	`create_time` TIMESTAMP NULL DEFAULT (now()) COMMENT '서버등록일시',
	`create_user` VARCHAR(30) NOT NULL COMMENT '서버등록자' COLLATE 'utf8mb4_0900_ai_ci',
	`update_time` TIMESTAMP NULL DEFAULT (now()) ON UPDATE CURRENT_TIMESTAMP COMMENT '서버수정일시',
	`update_user` VARCHAR(30) NOT NULL COMMENT '서버수정자' COLLATE 'utf8mb4_0900_ai_ci',
	PRIMARY KEY (`seq`) USING BTREE,
	UNIQUE INDEX `name` (`name`) USING BTREE,
	UNIQUE INDEX `ip` (`ip`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=16
;

CREATE TABLE `log` (
	`seq` INT(10) NOT NULL AUTO_INCREMENT,
	`user_id` VARCHAR(30) NOT NULL COMMENT '실행유저' COLLATE 'utf8mb4_0900_ai_ci',
	`type` VARCHAR(50) NOT NULL COMMENT '사건유형' COLLATE 'utf8mb4_0900_ai_ci',
	`description` VARCHAR(200) NOT NULL COMMENT '설명' COLLATE 'utf8mb4_0900_ai_ci',
	`result` VARCHAR(50) NOT NULL COMMENT '결과' COLLATE 'utf8mb4_0900_ai_ci',
	`create_time` TIMESTAMP NOT NULL DEFAULT (now()) COMMENT '생성시간',
	PRIMARY KEY (`seq`) USING BTREE,
	INDEX `FK1_userId` (`user_id`) USING BTREE,
	CONSTRAINT `FK1_userId` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=10
;

CREATE TABLE `user` (
	`seq` INT(10) NOT NULL AUTO_INCREMENT,
	`user_id` VARCHAR(30) NOT NULL COMMENT '유저id' COLLATE 'utf8mb4_0900_ai_ci',
	`password` VARCHAR(30) NOT NULL COMMENT '패스워드' COLLATE 'utf8mb4_0900_ai_ci',
	`role` VARCHAR(10) NOT NULL COMMENT '유저등급' COLLATE 'utf8mb4_0900_ai_ci',
	`create_user` VARCHAR(30) NOT NULL COMMENT '유저생성자' COLLATE 'utf8mb4_0900_ai_ci',
	`create_time` TIMESTAMP NOT NULL DEFAULT (now()) COMMENT '유저생성일시',
	`update_user` VARCHAR(30) NOT NULL COMMENT '유저정보업데이트자' COLLATE 'utf8mb4_0900_ai_ci',
	`update_time` TIMESTAMP NOT NULL DEFAULT (now()) ON UPDATE CURRENT_TIMESTAMP COMMENT '유저정보업데이트일시',
	PRIMARY KEY (`seq`) USING BTREE,
	UNIQUE INDEX `id` (`user_id`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=8
;
