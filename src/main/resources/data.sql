insert into 2_2_3.roles (role) values ('ROLE_ADMIN');
insert into 2_2_3.roles (role) values ('ROLE_USER');

insert into 2_2_3.users (last_name, age, name, password) VALUES ('Cobain', 27, 'Kurt', '$2a$10$cuEoA9shZ8rHPrctA6KlN.SqXTNmlBfsKsHgqm/F69/02xQg9hfb6');

insert into 2_2_3.users_roles (user_id, roles_id) VALUES (1, 1);