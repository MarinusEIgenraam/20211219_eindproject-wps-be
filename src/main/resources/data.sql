INSERT INTO projects (name, description)
VALUES ('new project', 'this is a new project');

INSERT INTO tasks (name, description, parent_task_id, parent_project_id)
VALUES ('new projecdsfgt', 'this is a new project', 1,null),
       ('new projdsfgect', 'ew project', 1, null),
       ('new projecdfsdggt', 't a new project', 2, null),
       ('new projecdfsdggt', 't a new project', 2, null),
       ('new projecdfsdggt', 't a new project', 2, null),
       ('new projfdgect', 'this project', 3, null),
       ('new projfdgect', 'this project', 3, null);

-- INSERT INTO tasks_task_list (task_id, task_list_id)
-- values (1,2),
--        (1,3),
--        (1,4),
--        (2,6),
--        (2,7),
--        (1,5);
--
-- INSERT INTO projects_task_list (project_id, task_list_id)
-- VALUES
-- (1,1);

INSERT INTO authorities (username, authority)
VALUES ('user', 'ROLE_USER'),
       ('admin', 'ROLE_USER'),
       ('superuser', 'ROLE_SUPER_USER'),
       ('admin', 'ROLE_ADMIN');

INSERT INTO users (username, password, enabled, email)
VALUES ('user', '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq', TRUE, 'dummy@novi.nl'),
       ('admin', '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq', TRUE, 'dummy@novi.nl'),
       ('superuser', '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq', TRUE, 'dummy@novi.nl');