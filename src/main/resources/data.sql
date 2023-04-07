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
