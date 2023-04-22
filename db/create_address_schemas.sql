CREATE TABLE IF NOT EXISTS state (
  id int PRIMARY KEY AUTO_INCREMENT,
  code char(2) NOT NULL,
  name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS zip (
  zip_code char(5) NOT NULL,
  state_id int NOT NULL,
  city varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (zip_code, city, state_id),
  FOREIGN KEY (state_id)
    REFERENCES state (id)
    ON UPDATE RESTRICT ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS address (
  id int PRIMARY KEY AUTO_INCREMENT,
  address_1 varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  address_2 varchar(255) COLLATE utf8mb4_general_ci,
  zip_id char(5) NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (zip_id)
    REFERENCES zip (zip_code)
    ON UPDATE RESTRICT ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
