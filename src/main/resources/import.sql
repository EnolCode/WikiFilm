
INSERT INTO genres (id, name) VALUES (default,'Acción');
INSERT INTO genres (id, name) VALUES (default,'Comedia');
INSERT INTO genres (id, name) VALUES (default,'Horror');
INSERT INTO genres (id, name) VALUES (default,'Ciencia ficción');
INSERT INTO genres (id, name) VALUES (default,'Western');
INSERT INTO genres (id, name) VALUES (default,'Romance');
INSERT INTO genres (id, name) VALUES (default,'Suspenso');
INSERT INTO genres (id, name) VALUES (default,'Fantasía');
INSERT INTO genres (id, name) VALUES (default,'Aventura');
INSERT INTO genres (id, name) VALUES (default,'Drama');
INSERT INTO genres (id, name) VALUES (default,'Misterio');
INSERT INTO genres (id, name) VALUES (default,'Animación');
INSERT INTO genres (id, name) VALUES (default,'Documental');
INSERT INTO genres (id, name) VALUES (default,'Musical');
INSERT INTO genres (id, name) VALUES (default,'Crimen');
INSERT INTO authors (id, name, surname, country, birth_year, rating) VALUES (default,'Quentin', "Tarantino", "EEUU", 1963, 9)
INSERT INTO authors (id, name, surname, country, birth_year, rating) VALUES (default,'Francis', "Copola", "EEUU", 1939, 9)
INSERT INTO authors (id, name, surname, country, birth_year, rating) VALUES (default,'Christopher', "Nolan", "Reino Unido", 1970, 8)
INSERT INTO authors (id, name, surname, country, birth_year, rating) VALUES (default,'Martin', "Scorsese", "EEUU", 1942, 8)
INSERT INTO authors (id, name, surname, country, birth_year, rating) VALUES (default,'James', "Cameron", "Canada", 1954, 8)
INSERT INTO authors (id, name, surname, country, birth_year, rating) VALUES (default,'Clint', "Eastwood", "EEUU", 1930, 9)
INSERT INTO authors (id, name, surname, country, birth_year, rating) VALUES (default,'Roman', "Polanski", "Francia", 1933, 9)
INSERT INTO authors (id, name, surname, country, birth_year, rating) VALUES (default,'David', "Fincher", "EEUU", 1962, 8)
INSERT INTO authors (id, name, surname, country, birth_year, rating) VALUES (default,'Damien', "Chazelle", "EEUU", 1985, 8)
INSERT INTO authors (id, name, surname, country, birth_year, rating) VALUES (default,'Todd', "Philips", "EEUU", 1970, 5)
INSERT INTO authors (id, name, surname, country, birth_year, rating) VALUES (default,'Rodrigo', "Sorogoyen", "España", 1981, 5)
INSERT INTO authors (id, name, surname, country, birth_year, rating) VALUES (default,'Pedro', "Almodovar", "España", 1949, 4)
INSERT INTO authors (id, name, surname, country, birth_year, rating) VALUES (default,'Guillermo', "del Toro", "Mexico", 1964, 7)
INSERT INTO film_genre (film_id, genre_id) VALUES (1,1)
INSERT INTO film_genre (film_id, genre_id) VALUES (2,1)
INSERT INTO film_genre (film_id, genre_id) VALUES (3,2)
INSERT INTO film_genre (film_id, genre_id) VALUES (4,2)
INSERT INTO film_genre (film_id, genre_id) VALUES (5,3)
INSERT INTO film_genre (film_id, genre_id) VALUES (5,3)
INSERT INTO users (id, username, password) VALUES (default, 'admin', '$2a$12$ZlqRb.X/BlEYvOSEaqh0uePb8XNGPYms25wrIWE1lvgSopFTxhYny')
INSERT INTO roles (id, role_name) VALUES (default, "ROLE_ADMIN")
INSERT INTO roles (id, role_name) VALUES (default, "ROLE_USER")
INSERT INTO user_roles (user_id, role_id) VALUES (1,1)

UPDATE films SET author_id = 1
