CREATE TABLE IF NOT EXISTS state (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  code char(2) NOT NULL,
  name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS zip (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  code char(5) NOT NULL,
  state_id bigint NOT NULL,
  city varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY unique_zip (code, state_id, city),
  KEY state_id_idx (state_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS address (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  address_1 varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  address_2 varchar(255) COLLATE utf8mb4_general_ci,
  zip_id bigint NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY zip_id_idx (zip_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
