INSERT INTO films( id, title, release_year, rating, description) VALUES (default, 'Film1', 2023, 8,  'Good Film')
INSERT INTO films( id, title, release_year, rating, description) VALUES (default, 'Film2', 2023, 8,  'Good Film')
INSERT INTO films( id, title, release_year, rating, description) VALUES (default, 'Film3', 2023, 8,  'Good Film')
INSERT INTO films( id, title, release_year, rating, description) VALUES (default, 'Film4', 2023, 8,  'Good Film')
INSERT INTO films( id, title, release_year, rating, description) VALUES (default, 'Film5', 2023, 8,  'Good Film')
INSERT INTO films( id, title, release_year, rating, description) VALUES (default, 'Film6', 2023, 8,  'Good Film')
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
INSERT INTO authors (id, name, surname, country, age, rating) VALUES (default,'Quentin', "Tarantino", "EEUU","59", 9)
INSERT INTO authors (id, name, surname, country, age, rating) VALUES (default,'Quentina', "Tarantina", "EEUU","59", 9)
INSERT INTO authors (id, name, surname, country, age, rating) VALUES (default,'Quentine', "Tarantine", "EEUU","59", 9)
INSERT INTO film_genre (film_id, genre_id) VALUES (1,1)
INSERT INTO film_genre (film_id, genre_id) VALUES (2,1)
INSERT INTO film_genre (film_id, genre_id) VALUES (3,2)
INSERT INTO film_genre (film_id, genre_id) VALUES (4,2)
INSERT INTO film_genre (film_id, genre_id) VALUES (5,3)
INSERT INTO film_genre (film_id, genre_id) VALUES (5,3)
INSERT INTO users (id, username, password) VALUES (default, "enol", "1234")
INSERT INTO roles (id, role_name) VALUES (default, "ROLE_ADMIN")
INSERT INTO roles (id, role_name) VALUES (default, "ROLE_USER")
INSERT INTO user_roles (user_id, role_id) VALUES (1,1)

UPDATE films SET author_id = 1
