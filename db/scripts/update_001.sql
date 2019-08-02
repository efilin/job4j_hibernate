CREATE TABLE IF NOT EXISTS users_hibernate (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(100),
  expired_date TIMESTAMP WITHOUT TIME ZONE
);
