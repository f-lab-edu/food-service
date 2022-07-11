create table tb_user (
    user_id bigint primary key,
    id varchar(16) not null unique,
    password varchar(50) not null,
    nickname varchar(30) not null,
    point mediumint not null default 0,
    created_date datetime not null default now()
);

create table tb_store (
    store_id bigint primary key,
    name varchar(50) not null,
    address varchar(120) not null,
    min_order_amount mediumint not null default 0,
    opening_time time not null,
    closing_time time not null,
    cooking_time time not null,
    open_status enum('open', 'closed') not null,
    food_category_id bigint not null
);

create table tb_food_category (
    food_category_id bigint primary key,
    name varchar(15) not null
);

create table tb_menu (
    menu_id bigint primary key,
    store_id bigint not null,
    name varchar(50) not null,
    amount mediumint not null default 0,
    image_urls varchar(255)
);

create table tb_menu_option (
    menu_option_id bigint primary key,
    menu_id bigint not null,
    title varchar(30) not null,
    selection varchar(50) not null,
    additional_amount mediumint not null default 0,
    is_required tinyint not null default 0
);

create table tb_order (
    order_id bigint primary key,
    user_id bigint not null,
    store_id bigint not null,
    amount int not null,
    status enum('requested', 'cooking', 'completed', 'canceled') not null,
    additional_comment varchar(150),
    payment_id bigint not null,
    created_date datetime not null default now()
);

create table tb_order_menu (
    order_menu_id bigint primary key,
    order_id int not null,
    menu_id int not null,
    menu_options varchar(150),
    amount int not null default 0,
    count tinyint not null default 0
);

create table tb_payment (
    payment_id bigint primary key,
    amount mediumint not null default 0,
    method enum('card', 'account_transfer') not null,
    created_date datetime not null default now(),
    status enum('payment_requested', 'payment_completed', 'cancellation_requested', 'cancellation_completed') not null,
    canceled_date datetime null,
    before_point mediumint not null,
    after_point mediumint not null
);

create table tb_review (
    review_id bigint primary key,
    user_id bigint not null,
    store_id bigint not null,
    order_id bigint not null,
    score tinyint not null,
    content varchar(1000) not null,
    created_date datetime not null,
    image_urls varchar(255) not null
);