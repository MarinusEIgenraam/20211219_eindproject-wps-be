INSERT INTO users (username, password, enabled, email)
VALUES ('user', '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq', TRUE, 'dummy@novi.nl'),
       ('admin', '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq', TRUE, 'dummy@novi.nl'),
       ('superuser', '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq', TRUE, 'dummy@novi.nl');

INSERT INTO authorities (username, authority)
VALUES ('user', 'ROLE_USER'),
       ('admin', 'ROLE_USER'),
       ('superuser', 'ROLE_SUPER_USER'),
       ('admin', 'ROLE_ADMIN');
