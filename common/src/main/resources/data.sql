insert into tb_user (id, password, nickname, point, created_date)
            values ('user1', '1234', 'user1', 0, now()),
                   ('user2', '1234', 'user2', 0, now()),
                   ('user3', '1234', 'user3', 0, now());

insert into tb_food_category (name) values ('분식'),
                                    ('한식'),
                                    ('중식'),
                                    ('양식'),
                                    ('치킨'),
                                    ('피자'),
                                    ('야식');

insert into tb_store
        (name, address, min_order_amount, opening_time, closing_time, cooking_time, open_status, food_category_id)
values ('store_name1', 'address1', 1000, '07:00:00', '17:00:00', '00:30:00', 'OPEN', 1),
        ('store_name2', 'address2', 1000, '07:00:00', '17:00:00', '00:30:00', 'OPEN', 1),
        ('store_name3', 'address3', 1000, '07:00:00', '17:00:00', '00:30:00', 'OPEN', 1);

insert into tb_menu (store_id, name, amount, image_urls)
values (1, 'menu_name1', 12000, '[https://imageUrlA.jpg, https://imageUrlB.jpg]'),
       (1, 'menu_name2', 13000, '[https://imageUrlC.jpg, https://imageUrlD.jpg]'),
       (1, 'menu_name3', 14000, '[https://imageUrlE.jpg, https://imageUrlF.jpg]');

insert into tb_payment (amount, method, status, canceled_date, before_point, after_point)
values (25000, 'CARD', 'PAYMENT_COMPLETED', null, 0, 0),
       (30000, 'ACCOUNT_TRANSFER', 'CANCELLATION_REQUESTED', null, 0, 0),
       (35000, 'CARD', 'PAYMENT_COMPLETED', null, 0, 0);

insert into tb_order (user_id, store_id, amount, status, additional_comment, payment_id)
values (1, 1, 24000, 'COOKING', null, 1),
       (1, 1, 24000, 'CANCELED', null, 2),
       (1, 1, 20000, 'REQUESTED', null, 3);

