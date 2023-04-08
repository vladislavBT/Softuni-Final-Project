INSERT INTO roles (id, name)
values
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO users (id, age, description, full_name, gender, password, username, money, email)
VALUES
    (1, 44, 'testtesttesttesttesttest','Test Testov', 'MALE','$2a$10$E3qEr34BqcB4BVJ4wk1HX.kFgkr52xzj6x4tuzdeBOOD67ym.iAnu','testtest',550.50, 'testtest@gmail.com');


INSERT INTO users_roles (user_entity_id, roles_id)
VALUES
    (1, 1),
    (1, 2);

INSERT INTO users (id, age, description, full_name, gender, password, username, money, email)
VALUES
    (2, 30, 'testtesttesttesttesttesttesttesttesttesttesttest','Test Testova', 'FEMALE','$2a$10$O5cGQTYn68rgYLMGh95Z2uLW2R6xb29ghl36p4h7NE/gi7VOe8W4K', 'test', 450.50, 'test@gmail.com');


INSERT INTO users_roles (user_entity_id, roles_id)
VALUES
    (2, 2);


INSERT INTO brands (id, description, image_url, name)
VALUES
    (1, 'Nike is a;lknsfjsnklfdmz', 'https://res.cloudinary.com/diind8g6e/image/upload/v1678739665/c3d6ab74-d07b-415b-a4c9-7e3034b1c70f.png', 'Nike');

INSERT INTO brands (id, description, image_url, name)
VALUES
    (2, 'Adidas is aosdbjakl,ndskaj,bs dlfkashnfl;akmflijksnfaijkaw.', 'https://res.cloudinary.com/diind8g6e/image/upload/v1678739665/711eb97e-6f98-4614-aa25-b9a2a8121f24.jpg', 'Adidas');

INSERT INTO brands (id, description, image_url, name)
VALUES
    (3, 'Reebok is oaisbfanfoasbfkLIHBOWNHFLKAHFO;BHkajsnfk;azsn.', 'https://res.cloudinary.com/diind8g6e/image/upload/v1678739665/11a59176-34a4-4c83-9d55-9dc2277bd0d0.jpg', 'Reebok');

INSERT INTO brands (id, description, image_url, name)
VALUES
    (4, 'Puma is ukajsbfhkljabnorlfjbaiklkfjalkhfgfos;ilkghauokjbjsgfiukjadhgnolfkzxbldzvhfuzhkjhkbu\ysjgfabwuyjfvakszfnajkdn.', 'https://res.cloudinary.com/diind8g6e/image/upload/v1678739665/61408f71-b1c0-4329-83d8-6068a47a4078.jpg', 'Puma');



INSERT INTO shoes (id, category, color, fabric, image_url, name, brand_id)
VALUES
    (1, 'SPORT', 'WHITE', 'LEATHER', 'https://res.cloudinary.com/diind8g6e/image/upload/v1678739665/40b13fb3-f27f-40c3-a195-17e28ab6a423.jpg','AirForce',1);

INSERT INTO shoes (id, category, color, fabric, image_url, name, brand_id)
VALUES
    (2, 'SPORT', 'WHITE', 'LEATHER', 'https://res.cloudinary.com/diind8g6e/image/upload/v1678739665/d23d46c8-1263-4427-9c73-dfac228ff690.jpg','Stan Smith',2);


INSERT INTO shoes (id, category, color, fabric, image_url, name, brand_id)
VALUES
    (3, 'SPORT', 'WHITE', 'ECO', 'https://res.cloudinary.com/diind8g6e/image/upload/v1678739665/3369834b-64e3-4320-8850-bd1e4f301608.jpg','Yeezy',2);

INSERT INTO shoes (id, category, color, fabric, image_url, name, brand_id)
VALUES
    (4, 'SPORT', 'WHITE', 'TEXTILES', 'https://res.cloudinary.com/diind8g6e/image/upload/v1678739665/e4129873-0eb5-4b9d-9545-52cd3905f423.jpg','Ultraboost',2);



INSERT INTO offers (id, description, gender, image_url, price, size, title, seller_id, shoe_id)
VALUES
    (1, 'l[pkljnsjiejffjjdmkvfdlkng;lskdjgml;ak.ng;lkadnfgl;k.akng;oaihrhgnaer;g', 'MALE', 'https://res.cloudinary.com/diind8g6e/image/upload/v1678739665/4aed0bba-ece5-4980-aa39-1b09c05e16a1.jpg',200.50,44, 'Nike air Force 1',1,1);

INSERT INTO offers (id, description, gender, image_url, price, size, title, seller_id, shoe_id)
VALUES
    (2, 'l0jiuhrijkewfjkvjaoifaklsndfmpahoriaipukjhnfvoialdfhnfslkhgnaoilhksngoialkdhnfgoa;kfgolhkjaolkghasjrn\dukgjhna\sgfnbsds', 'MALE', 'https://res.cloudinary.com/diind8g6e/image/upload/v1678739665/162ca7cf-dacb-495c-97ff-18ad288b0ea1.jpg',500.50,44, 'Adidas Yeezy',1,3);

INSERT INTO offers (id, description, gender, image_url, price, size, title, seller_id, shoe_id)
VALUES
    (3, 'l[pkljnsjiejffjjdmkvfdlkng;lskdjgml;ak.ng;lkadnfgl;k.akng;oaihrhgnaer;g',  'MALE', 'https://res.cloudinary.com/diind8g6e/image/upload/v1678739665/926f36a5-c17a-4ace-875e-ba9bd8c1805f.jpg',150.50,44, 'Adidas Ultraboost',1,4);

INSERT INTO offers (id, description, gender, image_url, price, size, title, seller_id, shoe_id)
VALUES
    (4, 'l[pkljnsjiejffjjdmkvfdlkng;lskdjgml;ak.ng;lkadnfgl;k.akng;oaihrhgnaer;g',  'MALE', 'https://res.cloudinary.com/diind8g6e/image/upload/v1678739665/39225b28-c9d4-4915-91dc-6ba79f129a74.jpg',120.40,41, 'Adidas Stan Smith',1,2);









