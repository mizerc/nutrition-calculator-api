CREATE TABLE food (
  id UUID PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  portion_size_grams INT NOT NULL,
  kcals INT NOT NULL,
  created_at DATE NOT NULL
);