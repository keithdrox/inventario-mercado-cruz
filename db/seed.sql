-- passwords are 'password' (BCrypt hash)
INSERT INTO usuarios (username, password, rol) VALUES 
('usuario', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HCGKK25F7fRzZ.Dcew9CW', 'ROLE_USER'),
('admin', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HCGKK25F7fRzZ.Dcew9CW', 'ROLE_ADMIN');

INSERT INTO productos (nombre, categoria, stock, precio) VALUES 
('Manzana', 'Frutas', 100, 0.50),
('Pan', 'Panadería', 50, 1.00),
('Leche', 'Lácteos', 200, 1.50);
