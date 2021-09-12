create table user_login (
user_id int auto_increment not null,
login_name varchar(255) not null,
password char(32) not null,
status char(1) not null default 1,
last_update_time timestamp not null,
primary key pk_user_id(user_id)
) ENGINE = innodb;

create table user_info (
user_info_id int auto_increment not null,
user_id not null,
username varchar(255) not null,
phone_number varchar(11),
email_addr varchar(50),
gender char(1),
address varchar(255),
register_time timestamp not null,
birthday DATETIME,
last_update_time timestamp not null,
primary key pk_user_info(user_info_id)
) ENGINE = innodb;

create table brand_info(
brand_id int auto_increment not null,
brand_name varchar(50) not null,
phone_number varchar(20) not null,
brand_web varchar(255),
brand_logo_url varchar(255),
brand_desc varchar(255),
brand_status char(1),
last_update_time timestamp not null
primary key pk_brand_info(brand_id)
) ENGINE = innodb;

create table goods_category(
category_id int auto_increment not null,
category_name varchar(255),
category_code varchar(100),
parent_id int not null,
category_level int,
category_status char(1) not null,
last_update_time timestamp not null,
primary key pk_goods_category(category_id)

) ENGINE = innodb;


create table product_image_info(
image_id int auto_increment not null,
product_id int not null,
image_url varchar(255),
img_order tinyint not null,
descriptions varchar(255),
status char(1) not null,
is_master char(1) not null,
last_update_time timestamp not null
primary key pk_product_image_info(image_id)
) ENGINE = innodb;

create table products_info(
product_id int auto_increment not null,
product_code varchar(50),
product_name varchar(50),
brand_id int not null,
price DECIMAL(8, 2) not null,
publish_status char(1) not null,
audit_status char(1) not null,
weight FLOAT,
lengths FLOAT,
height FLOAT,
width FLOAT,
color varchar(50),
descriptions varchar(255),
last_update_time timestamp not null,
insert_time timestamp not null,
one_category_id int not null,
two_category_id int not null,
three_category_id int not null,
production_date DATETIME not null,
expire_time int ,
supplier_id int,
qr_code varchar(255),
primary key pk_products_info(product_id)

)ENGINE  = innodb;


create table order_table (
order_id int auto_increment not null,
order_sn bigint not null,
user_id int not null,
receiver_name varchar(50),
address varchar(255),
payment tinyint not null,
order_money DECIMAL(8, 2) not null,
delivery_fee DECIMAL(8, 2) not null,
delivery_sn varchar(255),
create_time timestamp not null,
pay_time DATETIME,
shipping_time DATETIME,
receive_name varchar(255),
order_status tinyint not null,
order_point int not null,
delivery_time DATETIME,
last_update_time timestamp not null,
delivery-company varchar(255)
primary key pk_order_table(order_id)
) engine = innodb;

create table order_details(
detail_id primary key, auto_increment not null,
order_id int not null,
product_id int nut null,
product_name varchar(255),
product_nums int,
product_price ,
average_cost,
weight FLOAT,
discount_money,
last_update_time timestamp not null
primary key pk_order_details(detail_id)
) engine=innodb;

CREATE TABLE order_car(
  cart_id int not null AUTO_INCREMENT,
  user_id int not null ,
  product_id int not null ,
  product_amount int not null ,
  price DECIMAL(8,2) not null ,
  insert_time timestamp not null default current_timestamp,
  last_update_time timestamp not null default current_timestamp on update current_timestamp ,
  primary key pk_order_car(cart_id)
) ENGINE = innodb