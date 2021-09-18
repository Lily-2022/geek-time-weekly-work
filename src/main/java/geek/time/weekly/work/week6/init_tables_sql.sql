create table user_login (
u_user_id bigint(20) auto_increment not null,
u_login_name varchar(20) not null,
u_password varchar(64) not null,
u_status tinyint(11) not null default 1,
u_last_update_time bigint(30) not null,
primary key pk_user_id(u_user_id)
) ENGINE = innodb default CHARSET=utf8mb4 comment='user login';

create table user_info (
ui_user_info_id bigint(20) auto_increment not null,
ui_user_id bigint(20) not null,
ui_username varchar(64) not null,
ui_phone_number varchar(11),
ui_email_addr varchar(50),
ui_gender tinyint(11),
ui_address varchar(100),
ui_register_time bigint(30) not null,
ui_birthday varchar(20),
ui_last_update_time bigint(30) not null,
primary key pk_user_info(ui_user_info_id)
) ENGINE = innodb default CHARSET=utf8mb4 comment='user info';

create table brand_info(
b_brand_id bigint(20) auto_increment not null,
b_brand_name varchar(50) not null,
b_phone_number varchar(20) not null,
b_brand_web varchar(255),
b_brand_logo_url varchar(255),
b_brand_desc varchar(255),
b_brand_status tinyint(11),
b_last_update_time bigint(30) not null,
primary key pk_brand_info(b_brand_id)
) ENGINE = innodb default CHARSET=utf8mb4 comment='brand info';

create table goods_category(
g_category_id bigint(20) auto_increment not null,
g_category_name varchar(255),
g_category_code varchar(100),
g_parent_id bigint(20) not null,
g_category_level tinyint(10),
g_category_status tinyint(10) not null,
g_last_update_time bigint(30) not null,
primary key pk_goods_category(g_category_id)

) ENGINE = innodb default CHARSET=utf8mb4 comment='goods category';


create table product_image_info(
p_image_id bigint(20) auto_increment not null,
p_product_id bigint(30) not null,
p_image_url varchar(255),
p_img_order tinyint(10) not null,
p_descriptions varchar(255),
p_status tinyint(2) not null,
p_is_master tinyint(2) not null,
p_last_update_time bigint(30) not null,
primary key pk_product_image_info(p_image_id)
) ENGINE = innodb default CHARSET=utf8mb4 comment='product image info';

create table products_info(
pi_product_id bigint(30) auto_increment not null,
pi_product_code varchar(50),
pi_product_name varchar(50),
pi_brand_id int not null,
pi_price DECIMAL(8, 2) not null,
pi_publish_status char(1) not null,
pi_audit_status char(1) not null,
pi_weight FLOAT,
pi_lengths FLOAT,
pi_height FLOAT,
pi_width FLOAT,
pi_color varchar(50),
pi_descriptions varchar(255),
pi_last_update_time bigint(30) not null,
pi_insert_time bigint(30) not null,
pi_one_category_id int ,
pi_two_category_id int ,
pi_three_category_id int,
pi_production_date DATETIME not null,
pi_expire_time bigint(30) ,
pi_supplier_id int,
pi_qr_code varchar(255),
primary key pk_products_info(pi_product_id)

)ENGINE  = innodb default CHARSET=utf8mb4 comment='products info';


create table order_table (
o_order_id bigint(30) auto_increment not null,
o_order_sn bigint(10) not null,
o_user_id bigint(20) not null,
o_receiver_name varchar(50),
o_address varchar(255),
o_payment tinyint(10) not null,
o_order_money DECIMAL(8, 2) not null,
o_delivery_fee DECIMAL(8, 2) not null,
o_delivery_sn varchar(255),
o_create_time bigint(30) not null,
o_pay_time bigint(30),
o_shipping_time bigint(30),
o_receive_name varchar(255),
o_order_status tinyint not null,
o_order_point int not null,
o_delivery_time bigint(30),
o_last_update_time bigint(30) not null,
o_delivery_company varchar(255),
primary key pk_order_table(o_order_id)
) engine = innodb default CHARSET=utf8mb4 comment='order table';

create table order_details(
od_detail_id bigint(30) auto_increment not null,
od_order_id bigint(30) not null,
od_product_id bigint(30) not null,
od_product_name varchar(255),
od_product_nums int,
od_product_price varchar(20),
od_average_cost varchar(20),
od_weight FLOAT,
od_discount_money varchar(10),
od_last_update_time bigint(30) not null,
primary key pk_order_details(od_detail_id)
) engine = innodb default CHARSET=utf8mb4 comment='order details';

CREATE TABLE order_car(
  c_cart_id int  AUTO_INCREMENT not null,
  c_user_id bigint(30) not null ,
  c_product_id bigint(30) not null ,
  c_product_amount int not null ,
  c_price DECIMAL(8,2) not null ,
  c_insert_time bigint(30) not null,
  c_last_update_time bigint(30) not null,
  primary key pk_order_car(c_cart_id)
) ENGINE = innodb default CHARSET=utf8mb4 comment='order car';