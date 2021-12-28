INSERT INTO users (username, email, enabled, "password")
VALUES
    ('user', 'dummy@novi.nl', true, '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq'),
    ('admin', 'dummy@novi.nl', true, '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq'),
    ('superuser', 'dummy@novi.nl', true, '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq'),
    ('marinus', 'marinus@wilpoweredstudents.nl', true, '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq'),
    ('usermarinus', 'marinus@wilpoweredstudents.nl', true, '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq'),
    ('adminmarinus', 'marinus@wilpoweredstudents.nl', true, '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq');
INSERT INTO authorities (authority, username)
VALUES
    ('ROLE_USER', 'user'),
    ('ROLE_ADMIN', 'admin'),
    ('ROLE_USER', 'admin'),
    ('ROLE_SUPER_USER', 'admin'),
    ('ROLE_SUPER_USER', 'superuser'),
    ('ROLE_USER', 'superuser'),
    ('ROLE_USER', 'usermarinus'),
    ('ROLE_USER', 'superusermarinus'),
    ('ROLE_SUPER_USER', 'superusermarinus'),
    ('ROLE_ADMIN', 'adminmarinus'),
    ('ROLE_SUPER_USER', 'adminmarinus'),
    ('ROLE_USER', 'adminmarinus');
INSERT INTO categories (id,description, name)
VALUES
    (1,'science of technology', 'technology'),
    (2,'science of psychology', 'psychology'),
    (3,'science of writing', 'literature'),
    (4,'mathematics', 'match'),
    (5,'science of computers', 'computer science');
-- INSERT INTO projects (end_time, description, project_name, start_time, url, vote_count, project_owner)
-- VALUES
--     ('6/6/2021', 'creating a pokemon application', 'homework assignment', '6/6/2020', 'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla', 1, 'superuser'),
--     ('6/6/2021', 'creating a flying carpet', 'exam project', '6/6/2020', 'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla', 1, 'superuser'),
--     ('6/6/2021', 'creating a unicorn', 'hobby project', '6/6/2020', 'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla', 1, 'adminmarinus');
-- INSERT INTO tasks (description, end_time, is_running, start_time, task_name, parent_project_id, parent_task_task_id, task_owner_username)
-- VALUES
--     ('got to catch them all', '9/9/2023', true, '1/1/1', 'fetch pokemon', 1, null, 'user'),
--     ('buy pokeballs from store', '9/9/2023', true, '1/1/1', 'get pokeballs', null, 1, 'user'),
--     ('practice my pokeball throw with pikachu', '9/9/2023', true, '1/1/1', 'practice trowing', null, 1, 'user'),
--     ('check if pokemon still exist', '9/9/2023', true, '1/1/1', 'do research', null, 1, 'user'),
--     ('is pokemon.com stil open?', '9/9/2023', true, '1/1/1', 'get domain name', 1, null, 'user'),
--     ('does internet want to work with me?', '9/9/2023', true, '1/1/1', 'call internet', null, 5, 'user'),
--     ('internet had internet money?', '9/9/2023', false, '1/1/1', 'ask for money', null, 6, 'user');
-- INSERT INTO votes (vote_id, vote_type, user_name, project_id)
-- VALUES
--     (1, 1, 'user', 1),
--     (2,-1, 'superuser', 1),
--     (3,1, 'admin', 1),
--     (4,-1, 'usermarinus', 1),
--     (5,1, 'superusermarinus', 1),
--     (6,-1, 'adminmarinus', 1);
-- INSERT INTO setting_schemas (id,comment_alert, task_alert)
-- VALUES
--     (1,false, true),
--     (2,false, false),
--     (3,true, false),
--     (4,true, false),
--     (5,false, true),
--     (6,false, false);
-- INSERT INTO portals ( user_username,settings_schema_id)
-- VALUES
--     ('user', 1),
--     ('superuser', 2),
--     ('admin', 3),
--     ('usermarinus', 4),
--     ('superusermarinus', 5),
--     ('adminmarinus', 6);
-- INSERT INTO alerts ("text", title, portal_id)
-- VALUES
--        ('something happend', 'you have got mail', 1),
--        ('something happend', 'you have got mail', 1),
--        ('something happend', 'you have got mail', 1),
--        ('something happend', 'you have got mail', 1),
--        ('something happend', 'you have got mail', 1),
--        ('something happend', 'you have got mail', 1);
-- INSERT INTO "comments" (created_date, "text", username, project_id)
-- VALUES
-- ('1/1/1', 'Haha nice you are awsome', 'user', 1);
--
-- INSERT INTO "comments" (created_date, "text", parent_comment_id, username, project_id)
-- VALUES
--        ('1/1/1', 'Haha nice you are awsome', 1, 'superuser', 1),
--        ('1/1/1', 'Haha nice you are awsome', 2, 'admin', 1),
--        ('1/1/1', 'Haha nice you are awsome', 3, 'usermarinus', 1),
--        ('1/1/1', 'Haha nice you are awsome', 4, 'superusermarinus', 1),
--        ('1/1/1', 'Haha nice you are awsome', 5, 'adminmarinus', 1);
-- INSERT INTO comments_comments (parent_comment_id, comment_id)
-- VALUES
--        (1, 2),
--        (2, 3),
--        (3, 4),
--        (5, 6);
-- INSERT INTO portals_alerts (portal_id, alert_id)
-- VALUES
--        (1, 1),
--        (2, 2),
--        (3, 3),
--        (4, 4),
--        (5, 5),
--        (6, 6);
-- INSERT INTO project_categories (category_id, project_id)
-- VALUES
--        (1, 1),
--        (2, 2),
--        (3, 3);
-- INSERT INTO projects_collaborators (project_id, user_id)
-- VALUES
--        (1, '2'),
--        (1, '3'),
--        (1, '4'),
--        (1, '5'),
--        (2, '2'),
--        (2, '3'),
--        (2, '4'),
--        (2, '5'),
--        (3, '2'),
--        (3, '3'),
--        (3, '4'),
--        (3, '5');
-- INSERT INTO projects_tasks (parent_project_id, task_id)
-- VALUES
--        (1, 1),
--        (1, 5);
-- INSERT INTO tasks_tasks (parent_task_id, task_id)
-- VALUES
--        (1, 2),
--        (1, 3),
--        (1, 4),
--        (5, 6),
--        (5, 7);
