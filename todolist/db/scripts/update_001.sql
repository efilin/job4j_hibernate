CREATE TABLE IF NOT EXISTS item (
  id      SERIAL PRIMARY KEY,
  description    VARCHAR(100),
  created TIMESTAMP WITHOUT TIME ZONE,
  done    BOOLEAN
);