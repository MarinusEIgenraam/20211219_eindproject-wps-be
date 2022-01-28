INSERT INTO users (username, email, enabled, "password")
VALUES ('user', 'dummy@novi.nl', true, '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq'),
       ('admin', 'dummy@novi.nl', true, '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq'),
       ('superuser', 'dummy@novi.nl', true, '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq'),
       ('marinus', 'marinus@wilpoweredstudents.nl', true, '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq'),
       ('usermarinus', 'marinus@wilpoweredstudents.nl', true, '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq'),
       ('adminmarinus', 'marinus@wilpoweredstudents.nl', true, '$2y$10$vkCdc8it8.DuZpdFLZju1uK9SkzSQ0olcI/6WcggH1JanRMu6uMdq');
INSERT INTO authorities (authority, username)
VALUES ('ROLE_USER', 'user'),
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
INSERT INTO categories (id, description, name)
VALUES (1, 'science of technology', 'technology'),
       (2, 'science of psychology', 'psychology'),
       (3, 'science of writing', 'literature'),
       (4, 'mathematics', 'match'),
       (5, 'science of computers', 'computer science');
INSERT INTO projects (publicly_visible, end_time, project_name, description, start_time, url, image_url, vote_count, category_id,
                      project_owner)
VALUES (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 5, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 4, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 3, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 2, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 1, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 1, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 5, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 4, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 3, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 2, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 1, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 1, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 5, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 4, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 3, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 2, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 1, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 1, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 5, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 4, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 3, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 2, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 1, 'superuser'),
       (true, '6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
            magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
            doloremque ipsam laudantium libero temporibus.', '6/6/2021',
        'https://technorati.com/libero/ut/massa/volutpat/convallis/morbi.js?mattis=in&pulvinar=lacus&nulla=curabitur&pede=at&ullamcorper=ipsum&augue=ac&a=tellus&suscipit=semper&nulla=interdum&elit=mauris&ac=ullamcorper&nulla=purus&sed=sit&vel=amet&enim=nulla',
        'https://i.imgur.com/Sdi0eAB.jpeg', 1, 1, 'superuser');
INSERT INTO blogs (start_time, blog_name, description, url, image_url, blog_owner)
VALUES ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser'),
       ('6/6/2021', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Expedita, voluptatum.', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae consequatur cumque deserunt ex fuga ipsa
               magnam rerum sint! Aperiam architecto enim maxime nisi officiis quos rem vitae! Consequatur cupiditate
               doloremque ipsam laudantium libero temporibus.

               Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, nihil quam. Asperiores corporis esse
               laborum minus quaerat quidem reprehenderit tenetur.', 'www.google.com', 'https://i.imgur.com/Sdi0eAB.jpeg', 'superuser');

INSERT INTO tasks (description, end_time, is_running, start_time, task_name, parent_project_id, parent_task_task_id,
                   task_owner_username)
VALUES ('got to catch them all', '9/9/2023', true, '1/1/1', 'fetch pokemon', 1, null, 'user'),
       ('buy pokeballs from store', '9/9/2023', true, '1/1/1', 'get pokeballs', 2, null, 'superuser'),
       ('practice my pokeball throw with pikachu', '9/9/2023', true, '1/1/1', 'practice trowing', 3, null, 'user'),
       ('check if pokemon still exist', '9/9/2023', true, '1/1/1', 'do research', 4, null, 'admin'),
       ('is pokemon.com stil open?', '9/9/2023', true, '1/1/1', 'get domain name', 5, null, 'superuser'),
       ('got to catch them all', '9/9/2023', true, '1/1/1', 'fetch pokemon', 6, null, 'user'),
       ('buy pokeballs from store', '9/9/2023', true, '1/1/1', 'get pokeballs', 7, null, 'superuser'),
       ('practice my pokeball throw with pikachu', '9/9/2023', true, '1/1/1', 'practice trowing', 8, null, 'user'),
       ('check if pokemon still exist', '9/9/2023', true, '1/1/1', 'do research', 9, null, 'admin'),
       ('is pokemon.com stil open?', '9/9/2023', true, '1/1/1', 'get domain name', 10, null, 'superuser'),
       ('got to catch them all', '9/9/2023', true, '1/1/1', 'fetch pokemon', 11, null, 'user'),
       ('buy pokeballs from store', '9/9/2023', true, '1/1/1', 'get pokeballs', 12, null, 'superuser'),
       ('practice my pokeball throw with pikachu', '9/9/2023', true, '1/1/1', 'practice trowing', 13, null, 'user'),
       ('check if pokemon still exist', '9/9/2023', true, '1/1/1', 'do research', 14, null, 'admin'),
       ('is pokemon.com stil open?', '9/9/2023', true, '1/1/1', 'get domain name', 15, null, 'superuser'),
       ('got to catch them all', '9/9/2023', true, '1/1/1', 'fetch pokemon', 16, null, 'user'),
       ('buy pokeballs from store', '9/9/2023', true, '1/1/1', 'get pokeballs', 17, null, 'superuser'),
       ('practice my pokeball throw with pikachu', '9/9/2023', true, '1/1/1', 'practice trowing', 18, null, 'user'),
       ('check if pokemon still exist', '9/9/2023', true, '1/1/1', 'do research', 19, null, 'admin'),
       ('is pokemon.com stil open?', '9/9/2023', true, '1/1/1', 'get domain name', 20, null, 'superuser'),
       ('got to catch them all', '9/9/2023', true, '1/1/1', 'fetch pokemon', null, 1, 'user'),
       ('buy pokeballs from store', '9/9/2023', true, '1/1/1', 'get pokeballs', null, 2, 'superuser'),
       ('practice my pokeball throw with pikachu', '9/9/2023', true, '1/1/1', 'practice trowing', null, 3, 'user'),
       ('check if pokemon still exist', '9/9/2023', true, '1/1/1', 'do research', null, 4, 'admin'),
       ('is pokemon.com stil open?', '9/9/2023', true, '1/1/1', 'get domain name', null, 5, 'superuser'),
       ('got to catch them all', '9/9/2023', true, '1/1/1', 'fetch pokemon', null, 6, 'user'),
       ('buy pokeballs from store', '9/9/2023', true, '1/1/1', 'get pokeballs', null, 7, 'superuser'),
       ('practice my pokeball throw with pikachu', '9/9/2023', true, '1/1/1', 'practice trowing', null, 8, 'user'),
       ('check if pokemon still exist', '9/9/2023', true, '1/1/1', 'do research', null, 9, 'admin'),
       ('is pokemon.com stil open?', '9/9/2023', true, '1/1/1', 'get domain name', null, 10, 'superuser'),
       ('got to catch them all', '9/9/2023', true, '1/1/1', 'fetch pokemon', null, 11, 'user'),
       ('buy pokeballs from store', '9/9/2023', true, '1/1/1', 'get pokeballs', null, 12, 'superuser'),
       ('practice my pokeball throw with pikachu', '9/9/2023', true, '1/1/1', 'practice trowing', null, 13, 'user'),
       ('check if pokemon still exist', '9/9/2023', true, '1/1/1', 'do research', null, 14, 'admin'),
       ('is pokemon.com stil open?', '9/9/2023', true, '1/1/1', 'get domain name', null, 15, 'superuser'),
       ('got to catch them all', '9/9/2023', true, '1/1/1', 'fetch pokemon', null, 16, 'user'),
       ('buy pokeballs from store', '9/9/2023', true, '1/1/1', 'get pokeballs', null, 17, 'superuser'),
       ('practice my pokeball throw with pikachu', '9/9/2023', true, '1/1/1', 'practice trowing', null, 18, 'user'),
       ('check if pokemon still exist', '9/9/2023', true, '1/1/1', 'do research', null, 19, 'admin'),
       ('is pokemon.com stil open?', '9/9/2023', true, '1/1/1', 'get domain name', null, 20, 'superuser'),
       ('got to catch them all', '9/9/2023', true, '1/1/1', 'fetch pokemon', null, 1, 'user'),
       ('buy pokeballs from store', '9/9/2023', true, '1/1/1', 'get pokeballs', null, 2, 'superuser'),
       ('practice my pokeball throw with pikachu', '9/9/2023', true, '1/1/1', 'practice trowing', null, 3, 'user'),
       ('check if pokemon still exist', '9/9/2023', true, '1/1/1', 'do research', null, 4, 'admin'),
       ('is pokemon.com stil open?', '9/9/2023', true, '1/1/1', 'get domain name', null, 5, 'superuser'),
       ('got to catch them all', '9/9/2023', true, '1/1/1', 'fetch pokemon', null, 1, 'user'),
       ('buy pokeballs from store', '9/9/2023', true, '1/1/1', 'get pokeballs', null, 2, 'superuser'),
       ('practice my pokeball throw with pikachu', '9/9/2023', true, '1/1/1', 'practice trowing', null, 3, 'user'),
       ('check if pokemon still exist', '9/9/2023', true, '1/1/1', 'do research', null, 4, 'admin'),
       ('is pokemon.com stil open?', '9/9/2023', true, '1/1/1', 'get domain name', null, 5, 'superuser'),
       ('got to catch them all', '9/9/2023', true, '1/1/1', 'fetch pokemon', null, 11, 'user'),
       ('buy pokeballs from store', '9/9/2023', true, '1/1/1', 'get pokeballs', null, 12, 'superuser'),
       ('practice my pokeball throw with pikachu', '9/9/2023', true, '1/1/1', 'practice trowing', null, 13, 'user'),
       ('check if pokemon still exist', '9/9/2023', true, '1/1/1', 'do research', null, 14, 'admin'),
       ('is pokemon.com stil open?', '9/9/2023', true, '1/1/1', 'get domain name', null, 15, 'superuser'),
       ('got to catch them all', '9/9/2023', true, '1/1/1', 'fetch pokemon', null, 11, 'user'),
       ('buy pokeballs from store', '9/9/2023', true, '1/1/1', 'get pokeballs', null, 12, 'superuser'),
       ('practice my pokeball throw with pikachu', '9/9/2023', true, '1/1/1', 'practice trowing', null, 13, 'user'),
       ('check if pokemon still exist', '9/9/2023', true, '1/1/1', 'do research', null, 14, 'admin'),
       ('is pokemon.com stil open?', '9/9/2023', true, '1/1/1', 'get domain name', null, 15, 'superuser'),
       ('got to catch them all', '9/9/2023', true, '1/1/1', 'fetch pokemon', null, 11, 'user'),
       ('buy pokeballs from store', '9/9/2023', true, '1/1/1', 'get pokeballs', null, 12, 'superuser'),
       ('practice my pokeball throw with pikachu', '9/9/2023', true, '1/1/1', 'practice trowing', null, 13, 'user'),
       ('check if pokemon still exist', '9/9/2023', true, '1/1/1', 'do research', null, 14, 'admin'),
       ('is pokemon.com stil open?', '9/9/2023', true, '1/1/1', 'get domain name', null, 15, 'superuser');
INSERT INTO comments (start_time, blog_id, parent_comment_id, project_id, text, comment_owner)
values ('2021-09-12T08:00:1', null , null, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-12T08:00:2', null , null, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-12T08:00:3', null , null, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-12T08:00:4', null , null, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-12T08:00:5', null , null, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-12T08:00:6', null , null, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-12T08:00:7', null , null, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-12T08:00:8', null , null, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-12T08:00:9', null , null, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-12T08:00:10', null , null, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-12T08:00:11', null , null, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-12T08:00:12', null , null, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-12T08:00:13', null , null, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-12T08:00:14', null , null, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-12T08:00:15', null , null, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-1T08:1:1', null , 1, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-2T08:2:2', null , 2, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-3T08:3:3', null , 3, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-4T08:4:4', null , 4, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-5T08:5:5', null , 5, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-6T08:6:6', null , 6, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-7T08:7:7', null , 7, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-8T08:8:8', null , 8, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-9T08:9:9', null , 9, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-10T08:10:10', null , 10, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-11T08:11:11', null , 11, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-12T08:12:12', null , 12, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-13T08:13:13', null , 13, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-14T08:14:14', null , 14, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-15T08:15:15', null , 15, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-1T08:1:1', null , 1, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-2T08:2:2', null , 2, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-3T08:3:3', null , 3, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-4T08:4:4', null , 4, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-5T08:5:5', null , 5, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-6T08:6:6', null , 6, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-7T08:7:7', null , 7, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-8T08:8:8', null , 8, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-9T08:9:9', null , 9, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-10T08:10:10', null , 10, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-11T08:11:11', null , 11, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-12T08:12:12', null , 12, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-13T08:13:13', null , 13, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-14T08:14:14', null , 14, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-15T08:15:15', null , 15, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-1T08:1:1', null , 1, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-2T08:2:2', null , 2, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-3T08:3:3', null , 3, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-4T08:4:4', null , 4, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-5T08:5:5', null , 5, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-6T08:6:6', null , 6, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-7T08:7:7', null , 7, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-8T08:8:8', null , 8, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-9T08:9:9', null , 9, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-10T08:10:10', null , 10, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-11T08:11:11', null , 11, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-12T08:12:12', null , 12, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-13T08:13:13', null , 13, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-14T08:14:14', null , 14, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-15T08:15:15', null , 15, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-1T08:1:1', null , null, 2, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-2T08:2:2', null , null, 2, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-3T08:3:3', null , null, 2, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-4T08:4:4', null , null, 2, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-5T08:5:5', null , null, 2, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-6T08:6:6', null , null, 2, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-7T08:7:7', null , null, 2, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-8T08:8:8', null , null, 2, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-9T08:9:9', null , null, 2, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-10T08:10:10', null , null, 2, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-11T08:11:11', null , null, 2, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-12T08:12:12', null , null, 2, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-13T08:13:13', null , null, 2, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-14T08:14:14', null , null, 2, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-15T08:15:15', null , null, 2, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-1T08:1:1', null , null, 3, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-2T08:2:2', null , null, 3, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-3T08:3:3', null , null, 3, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-4T08:4:4', null , null, 3, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-5T08:5:5', null , null, 3, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-6T08:6:6', null , null, 3, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-7T08:7:7', null , null, 3, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-8T08:8:8', null , null, 3, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-9T08:9:9', null , null, 3, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-10T08:10:10', null , null, 3, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-11T08:11:11', null , null, 3, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-12T08:12:12', null , null, 3, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-13T08:13:13', null , null, 3, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-14T08:14:14', null , null, 3, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-15T08:15:15', null , null, 3, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-1T08:1:1', null , null, 4, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-2T08:2:2', null , null, 4, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-3T08:3:3', null , null, 4, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-4T08:4:4', null , null, 4, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-5T08:5:5', null , null, 4, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-6T08:6:6', null , null, 4, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-7T08:7:7', null , null, 4, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-8T08:8:8', null , null, 4, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-9T08:9:9', null , null, 4, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-10T08:10:10', null , null, 4, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-11T08:11:11', null , null, 4, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-12T08:12:12', null , null, 4, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-13T08:13:13', null , null, 4, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-14T08:14:14', null , null, 4, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-15T08:15:15', null , null, 4, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-07-1T08:1:1', null , null, 5, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-07-2T08:2:2', null , null, 5, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-07-3T08:3:3', null , null, 5, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-07-4T08:4:4', null , null, 5, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-07-5T08:5:5', null , null, 5, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-07-6T08:6:6', null , null, 5, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-07-7T08:7:7', null , null, 5, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-07-8T08:8:8', null , null, 5, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-07-9T08:9:9', null , null, 5, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-07-10T08:10:10', null , null, 5, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-07-11T08:11:11', null , null, 5, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-07-12T08:12:12', null , null, 5, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-07-13T08:13:13', null , null, 5, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-07-14T08:14:14', null , null, 5, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-07-15T08:15:15', null , null, 5, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-1T08:1:1', null , null, 6, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-2T08:2:1', null , null, 6, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-3T08:3:1', null , null, 6, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-4T08:4:1', null , null, 6, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-5T08:5:1', null , null, 6, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-6T08:6:1', null , null, 6, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-7T08:7:1', null , null, 6, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-8T08:8:1', null , null, 6, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-9T08:9:1', null , null, 6, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-10T08:10:1', null , null, 6, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-11T08:11:1', null , null, 6, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-12T08:12:1', null , null, 6, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-13T08:13:1', null , null, 6, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-14T08:14:1', null , null, 6, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-15T08:15:1', null , null, 6, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-07-11T08:11:11', 1 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-07-12T08:12:12', 2 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-07-13T08:13:13', 3 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-07-14T08:14:14', 4 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-07-15T08:15:15', 5 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-1T08:1:1', 6 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-2T08:2:1', 7 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-3T08:3:1', 8 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-4T08:4:1', 9 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-5T08:5:1', 10 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'user'),
       ('2021-09-6T08:6:1', 11 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-7T08:7:1', 12 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-8T08:8:1', 13 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-9T08:9:1', 14 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-10T08:10:1', 15 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'admin'),
       ('2021-09-11T08:11:1', 16 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-12T08:12:1', 17 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-13T08:13:1', 18 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-14T08:14:1', 19 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser'),
       ('2021-09-15T08:15:1', 20 , null, null, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus, officia.', 'superuser');
INSERT INTO comment_comments (parent_comment_id, comment_id)
values (1, 16),
       (1, 17),
       (1, 18),
       (1, 19),
       (1, 20),
       (1, 21),
       (1, 22),
       (1, 23),
       (1, 24),
       (1, 25),
       (1, 26),
       (1, 27),
       (1, 28),
       (1, 29),
       (1, 30),
       (1, 31),
       (1, 32),
       (1, 33),
       (1, 34),
       (1, 35),
       (1, 36),
       (1, 37),
       (1, 38),
       (1, 39),
       (1, 40),
       (1, 41),
       (1, 42),
       (1, 43),
       (1, 44),
       (1, 45),
       (1, 46),
       (1, 47),
       (1, 48),
       (1, 49),
       (1, 50),
       (1, 51),
       (1, 52),
       (1, 53),
       (1, 54),
       (1, 55),
       (1, 56),
       (1, 57),
       (1, 58),
       (1, 59),
       (1, 60),
       (2, 61),
       (2, 62),
       (2, 63),
       (2, 64),
       (2, 65),
       (2, 66),
       (2, 67),
       (2, 68),
       (2, 69),
       (2, 70),
       (2, 71),
       (2, 72),
       (2, 73),
       (2, 74),
       (2, 75),
       (3, 76),
       (3, 77),
       (3, 78),
       (3, 79),
       (3, 80),
       (3, 81),
       (3, 82),
       (3, 83),
       (3, 84),
       (3, 85),
       (3, 86),
       (3, 87),
       (3, 88),
       (3, 89),
       (3, 90),
       (4, 91),
       (4, 92),
       (4, 93),
       (4, 94),
       (4, 95),
       (4, 96),
       (4, 97),
       (4, 98),
       (4, 99),
       (4, 100),
       (4, 101),
       (4, 102),
       (4, 103),
       (4, 104),
       (4, 105),
       (5, 106),
       (5, 107),
       (5, 108),
       (5, 109),
       (5, 110),
       (5, 111),
       (5, 112),
       (5, 113),
       (5, 114),
       (5, 115),
       (5, 116),
       (5, 117),
       (5, 118),
       (5, 119),
       (5, 120),
       (6, 121),
       (6, 122),
       (6, 123),
       (6, 124),
       (6, 125),
       (6, 126),
       (6, 127),
       (6, 128),
       (6, 129),
       (6, 130),
       (6, 131),
       (6, 132),
       (6, 133),
       (6, 134),
       (6, 135);
INSERT INTO project_tasks (parent_project_id, task_id)
values (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (9, 9),
       (10, 10),
       (11, 11),
       (12, 12),
       (13, 13),
       (14, 14),
       (15, 15),
       (16, 16),
       (17, 17),
       (18, 18),
       (19, 19),
       (20, 20);
INSERT INTO task_tasks (parent_task_id, task_id)
values
       (1, 21),
       (2, 22),
       (3, 23),
       (4, 24),
       (5, 25),
       (6, 26),
       (7, 27),
       (8, 28),
       (9, 29),
       (10, 30),
       (11, 31),
       (12, 32),
       (13, 33),
       (14, 34),
       (15, 35),
       (16, 36),
       (17, 37),
       (18, 38),
       (19, 39),
       (20, 40),
       (1, 41),
       (2, 42),
       (3, 43),
       (4, 44),
       (5, 45),
       (1, 46),
       (2, 47),
       (4, 48),
       (5, 49),
       (6, 50),
       (11, 51),
       (12, 52),
       (13, 53),
       (14, 54),
       (15, 55),
       (11, 56),
       (12, 57),
       (13, 58),
       (14, 59),
       (15, 60),
       (11, 61),
       (12, 62),
       (13, 63),
       (14, 64),
       (15, 65);
INSERT INTO project_votes (vote_type, user_name, project_id)
VALUES (1, 'user', 1),
       (1, 'superuser', 1),
       (1, 'admin', 1),
       (1, 'user', 2),
       (1, 'superuser', 2),
       (1, 'admin', 2),
       (1, 'user', 2),
       (1, 'superuser', 2),
       (1, 'admin', 2),
       (1, 'user', 3),
       (1, 'superuser', 3),
       (1, 'admin', 3),
       (1, 'user', 4),
       (1, 'superuser', 4),
       (1, 'admin', 4),
       (1, 'user', 5),
       (1, 'superuser', 5),
       (1, 'admin', 5),
       (1, 'user', 6),
       (1, 'superuser', 6),
       (1, 'admin', 6),
       (1, 'user', 7),
       (1, 'superuser', 7),
       (1, 'admin', 7),
       (1, 'user', 8),
       (1, 'superuser', 8),
       (1, 'admin', 8),
       (1, 'user', 9),
       (1, 'superuser', 9),
       (1, 'admin', 9),
       (1, 'user', 10),
       (1, 'superuser', 10),
       (1, 'admin', 10),
       (1, 'user', 11),
       (1, 'superuser', 11),
       (1, 'admin', 11),
       (1, 'user', 12),
       (1, 'superuser', 12),
       (1, 'admin', 12),
       (1, 'user', 13),
       (1, 'superuser', 13),
       (1, 'admin', 13),
       (1, 'user', 14),
       (1, 'superuser', 14),
       (1, 'admin', 14),
       (1, 'user', 15),
       (1, 'superuser', 15),
       (1, 'admin', 15),
       (1, 'user', 16),
       (1, 'superuser', 16),
       (1, 'admin', 16),
       (1, 'user', 17),
       (1, 'superuser', 17),
       (1, 'admin', 17),
       (1, 'user', 18),
       (1, 'superuser', 18),
       (1, 'admin', 18),
       (1, 'user', 19),
       (1, 'superuser', 19),
       (1, 'admin', 19),
       (1, 'user', 20),
       (1, 'superuser', 20),
       (1, 'admin', 20);
INSERT INTO setting_schemas (id,comment_alert, task_alert)
VALUES
    (1,false, true),
    (2,false, false),
    (3,true, false),
    (4,true, false);
INSERT INTO portals ( user_username)
VALUES
    ('user'),
    ('superuser'),
    ('admin');
INSERT INTO alerts ("text", title, portal_id)
VALUES
       ('something happend', 'you have got mail', 1),
       ('something happend', 'you have got mail', 2),
       ('something happend', 'you have got mail', 3),
       ('something happend', 'you have got mail', 1),
       ('something happend', 'you have got mail', 2),
       ('something happend', 'you have got mail', 3);

INSERT INTO portal_alerts (portal_id, alert_id)
VALUES
       (1, 1),
       (2, 2),
       (3, 3),
       (1, 4),
       (2, 5),
       (3, 6);
INSERT INTO project_collaborators (project_id, username)
VALUES
       (1, 'user'),
       (1, 'superuser'),
       (1, 'admin'),
       (2, 'user'),
       (2, 'superuser'),
       (2, 'admin'),
       (3, 'user'),
       (3, 'superuser'),
       (3, 'admin'),
       (4, 'user'),
       (4, 'superuser'),
       (4, 'admin'),
       (5, 'user'),
       (5, 'superuser'),
       (5, 'admin'),
       (6, 'user'),
       (6, 'superuser'),
       (6, 'admin'),
       (7, 'user'),
       (7, 'superuser'),
       (7, 'admin'),
       (8, 'user'),
       (8, 'superuser'),
       (8, 'admin'),
       (9, 'user'),
       (9, 'superuser'),
       (9, 'admin'),
       (10, 'user'),
       (10, 'superuser'),
       (10, 'admin'),
       (11, 'user'),
       (11, 'superuser'),
       (11, 'admin'),
       (12, 'user'),
       (12, 'superuser'),
       (12, 'admin'),
       (13, 'user'),
       (13, 'superuser'),
       (13, 'admin'),
       (14, 'user'),
       (14, 'superuser'),
       (14, 'admin'),(1, 'user'),
       (15, 'superuser'),
       (15, 'admin'),
       (15, 'user'),
       (16, 'superuser'),
       (16, 'admin'),
       (16, 'user'),
       (17, 'superuser'),
       (17, 'admin'),
       (17, 'user'),
       (18, 'superuser'),
       (18, 'admin'),
       (18, 'user'),
       (19, 'superuser'),
       (19, 'admin'),
       (19, 'user'),
       (20, 'superuser'),
       (20, 'admin'),
       (20, 'user');
INSERT INTO category_projects (category_id, project_id)
VALUES
    (5,1),
    (4,2),
    (3,3),
    (2,4),
    (1,5),
    (1,6),
    (5,7),
    (4,8),
    (3,9),
    (2,10),
    (1,11),
    (1,12),
    (5,13),
    (4,14),
    (3,15),
    (2,16),
    (1,17),
    (1,18),
    (5,19),
    (4,20),
    (3,21),
    (2,22),
    (1,23),
    (1,24);