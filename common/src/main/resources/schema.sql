create table tb_user (
    user_id bigint auto_increment primary key,
    login_id varchar(16) not null unique,
    password varchar(50) not null,
    nickname varchar(30) not null,
    point mediumint not null default 0,
    created_date datetime not null default now()
);

create table tb_store (
    store_id bigint auto_increment primary key,
    name varchar(50) not null,
    address varchar(120) not null,
    min_order_amount mediumint not null default 0,
    opening_time time not null,
    closing_time time not null,
    cooking_time time not null,
    open_status enum('OPEN', 'CLOSED') not null,
    food_category_id bigint not null
);

create table tb_food_category (
    food_category_id bigint auto_increment primary key,
    name varchar(15) not null
);

create table tb_menu (
    menu_id bigint auto_increment primary key,
    store_id bigint not null,
    name varchar(50) not null,
    amount mediumint not null default 0,
    image_urls varchar(255)
);

create table tb_menu_option (
    menu_option_id bigint auto_increment primary key,
    menu_id bigint not null,
    title varchar(30) not null,
    selection varchar(50) not null,
    additional_amount mediumint not null default 0,
    is_required tinyint not null default 0
);

create table tb_order (
    order_id bigint auto_increment primary key,
    user_id bigint not null,
    store_id bigint not null,
    amount int not null,
    status enum('REQUESTED', 'COOKING', 'COMPLETED', 'CANCELED') not null,
    additional_comment varchar(150),
    payment_id bigint not null,
    created_date datetime not null default now()
);

create table tb_order_menu (
    order_menu_id bigint auto_increment primary key,
    order_id int not null,
    menu_id int not null,
    menu_options varchar(150),
    amount int not null default 0,
    count tinyint not null default 0
);

create table tb_payment (
    payment_id bigint auto_increment primary key,
    amount mediumint not null default 0,
    method enum('CARD', 'ACCOUNT_TRANSFER') not null,
    created_date datetime not null default now(),
    status enum('PAYMENT_REQUESTED', 'PAYMENT_COMPLETED', 'CANCELLATION_REQUESTED', 'CANCELLATION_COMPLETED') not null,
    canceled_date datetime null,
    before_point mediumint not null,
    after_point mediumint not null
);

create table tb_review (
    review_id bigint auto_increment primary key,
    user_id bigint not null,
    store_id bigint not null,
    order_id bigint not null,
    score tinyint not null,
    content varchar(1000) not null,
    created_date datetime not null default now(),
    image_urls varchar(255)
);