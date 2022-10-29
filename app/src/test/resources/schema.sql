create table tb_user
(
    user_id bigint auto_increment primary key,
    nickname varchar(30) not null
);

create table tb_app_account
(
    app_account_id bigint auto_increment primary key,
    login_id varchar(50) not null unique,
    password varchar(100) not null,
    user_id bigint not null unique,
    created_date datetime not null default now()
);

create table tb_social_account
(
    app_account_id bigint auto_increment primary key,
    login_id varchar(50) not null unique,
    user_id bigint not null unique,
    created_date datetime not null default now()
);

create table tb_store_owner
(
    store_owner_id bigint auto_increment primary key,
    user_id bigint not null unique,
    created_date datetime not null default now()
);

create table tb_address
(
    address_id bigint auto_increment primary key,
    post_code varchar(30) not null,
    sido varchar(30) not null,
    sigungu varchar(30) not null,
    type varchar(10) not null,
    type_address varchar(100) not null,
    main_no smallint not null,
    sub_no smallint not null,
    reference_address varchar(30)
);

create table tb_store
(
    store_id bigint auto_increment primary key,
    name varchar(50) not null,
    store_owner_id bigint not null,
    min_order_amount mediumint not null,
    open_status enum('OPEN', 'CLOSED') not null
);

create table tb_store_operating_time
(
    store_operating_time_id bigint auto_increment primary key,
    store_id bigint not null,
    week enum('MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN') not null,
    opening_time time not null,
    closing_time time not null comment '영업 종료일시'
);

create table tb_store_address
(
    store_address_id bigint auto_increment primary key,
    store_id         bigint not null,
    address_id       bigint not null,
    address_detail   varchar(150)
);

create table tb_menu
(
    menu_id bigint auto_increment primary key,
    store_id bigint not null,
    name varchar(50) not null,
    amount mediumint not null default 0,
    cooking_time mediumint not null
);

create table tb_menu_option
(
    menu_option_id bigint auto_increment primary key,
    menu_id bigint not null,
    name varchar(50) not null,
    min_size tinyint not null,
    max_size tinyint not null
);

create table tb_menu_selection
(
    menu_selection_id bigint auto_increment primary key,
    menu_option_id bigint not null,
    selection varchar(50) not null,
    amount tinyint not null
);

create table tb_food_category
(
    food_category_id bigint auto_increment primary key,
    name varchar(15) not null unique
);

create table tb_store_food_category
(
    store_id bigint not null,
    food_category_id bigint not null
);

ALTER TABLE tb_store_food_category ADD PRIMARY KEY (store_id, food_category_id);

create table tb_image
(
    image_id bigint auto_increment primary key,
    path varchar(200) not null,
    name varchar(50) not null,
    created_date datetime not null default now()
);

create table tb_store_image
(
    store_id bigint not null,
    image_id bigint not null
);

ALTER TABLE tb_store_image ADD PRIMARY KEY (store_id, image_id);

create table tb_order
(
    order_id bigint auto_increment primary key,
    customer_id bigint not null,
    store_id bigint not null,
    created_date datetime not null default now(),
    amount int not null,
    status enum('REQUEST', 'COOKING', 'COMPLETED', 'CANCELED') not null,
    comment varchar(150)
);

create table tb_order_menu
(
    order_menu_id bigint auto_increment primary key,
    order_id bigint not null,
    menu_id bigint not null,
    amount int not null default 0,
    count tinyint not null default 0
);

create table tb_order_menu_selection
(
    order_menu_selection_id bigint auto_increment primary key,
    order_menu_id int not null,
    menu_selection_id bigint not null
);

create table tb_payment
(
    payment_id bigint auto_increment primary key,
    order_id bigint not null,
    action_type enum('PAYMENT', 'CANCELLATION' ) not null,
    created_date datetime not null default now()
);

create table tb_payment_log
(
    payment_log_id bigint auto_increment primary key,
    payment_id bigint not null,
    method enum('CARD', 'ACCOUNT_TRANSFER', 'POINT' ) not null,
    amount mediumint not null default 0,
    point_id mediumint null default 0,
    created_date datetime not null default now()
);

create table tb_point
(
    point_id bigint auto_increment primary key,
    user_id bigint not null,
    type enum('USE', 'COLLECT', 'RETRIEVE', 'RECOLLECT') not null,
    changed_amount mediumint not null default 0,
    current_amount mediumint not null default 0,
    payment_id bigint,
    created_date datetime not null default now()
);

create table tb_review
(
    review_id bigint auto_increment primary key,
    writer_id bigint not null,
    store_id bigint not null,
    order_id bigint not null,
    score tinyint not null,
    content varchar(1000) not null,
    created_date datetime not null default now()
);

create table tb_review_image
(
    review_id bigint not null,
    image_id bigint not null
);

ALTER TABLE tb_review_image ADD PRIMARY KEY (review_id, image_id);

create table tb_refresh_token
(
    token_value char(60) primary key,
    owner bigint not null,
    issued_date datetime not null,
    expired_date datetime not null
);

create table tb_log_in_out_log
(
    account_id bigint auto_increment not null,
    account_type varchar(30) not null,
    log_type varchar(30) not null,
    created_date datetime not null default now()
);