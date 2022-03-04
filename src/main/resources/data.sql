
INSERT INTO role (name)
SELECT * FROM (SELECT 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM role WHERE name = 'ROLE_ADMIN'
    ) LIMIT 1;
INSERT INTO role (name)
SELECT * FROM (SELECT 'ROLE_SUPERADMIN') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM role WHERE name = 'ROLE_SUPERADMIN'
    ) LIMIT 1;

INSERT INTO role (name)
SELECT * FROM (SELECT 'ROLE_USER') AS tmp
WHERE NOT EXISTS (
        SELECT name FROM role WHERE name = 'ROLE_USER'
) LIMIT 1;
INSERT INTO user (email,password,is_none_locked)
SELECT * FROM (SELECT 'admin','$2a$10$26xBiNrZY9Kbt9wCimSHt...qqFNAMcJv2PjBiq41Y5Tt6k7jzCt.',true) AS tmp
WHERE NOT EXISTS (
        SELECT name FROM user WHERE email = 'admin'
    ) LIMIT 1;

INSERT INTO user_roles (user_id, role_id)
SELECT * FROM (SELECT 1,2) AS tmp
WHERE NOT EXISTS (
        SELECT user_id FROM user_roles WHERE user_id=1 and role_id=2
    );