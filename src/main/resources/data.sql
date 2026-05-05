-- Sample data for Rapidito application

-- Insert sample clients
INSERT INTO cliente (nombre, apellido, direccion, telefono, email) VALUES
                                                                       ('Juan', 'Pérez', 'Calle Falsa 123', '555-1234', 'juan.perez@email.com'),
                                                                       ('María', 'García', 'Avenida Siempre Viva 742', '555-5678', 'maria.garcia@email.com'),
                                                                       ('Carlos', 'López', 'Boulevard del Sol 456', '555-9012', 'carlos.lopez@email.com');

-- Insert sample vehicles (Corregido: numero_bastidor, tipo_combustible, precio_dia)
INSERT INTO vehiculo (numero_bastidor, marca, modelo, matricula, tipo_combustible, precio_dia) VALUES
                                                                                                   ('1HGCM82633A004352', 'Toyota', 'Corolla', 'ABC-123', 'Gasolina', 35.50),
                                                                                                   ('JTDKN3DU0A5000001', 'Honda', 'Civic', 'XYZ-789', 'Híbrido', 40.00),
                                                                                                   ('5YJ3E1EA0KF000001', 'Ford', 'Focus', 'DEF-456', 'Diésel', 38.75),
                                                                                                   ('WF0DXXGACDKP12784', 'Chevrolet', 'Malibu', 'GHI-012', 'Eléctrico', 45.00);

-- Insert sample reservations (Corregido: fecha_inicio, fecha_fin)
INSERT INTO reserva (fecha_inicio, fecha_fin, id_vehiculo, id_cliente) VALUES
                                                                           ('2026-05-10', '2026-05-15', 1, 1),
                                                                           ('2026-05-20', '2026-05-25', 2, 2),
                                                                           ('2026-06-01', '2026-06-10', 3, 3);

-- Insert sample contracts (Corregido: fecha_inicio, fecha_fin, numero_dias, precio_vehiculo, total_contrato)
INSERT INTO contrato (fecha_inicio, fecha_fin, numero_dias, precio_vehiculo, total_contrato, id_vehiculo, id_cliente) VALUES
                                                                                                                          ('2026-05-10', '2026-05-20', 10, 35.50, 355.00, 1, 1),
                                                                                                                          ('2026-05-20', '2026-06-01', 12, 40.00, 480.00, 2, 2);