create table tb_user (
    user_id bigint auto_increment primary key,
    nickname varchar(30) not null
);

create table tb_app_account (
    app_account_id bigint auto_increment primary key,
    login_id varchar(50) not null unique,
    password varchar(100) not null,
    user_id bigint not null unique,
    created_date datetime not null default now()
);