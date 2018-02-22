DROP TABLE car_type IF EXISTS;
CREATE TABLE IF NOT EXISTS car_type (
    id INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(64),
    UNIQUE (name)
 );
CREATE INDEX idx_car_types_name ON car_type (name);

DROP TABLE item_type IF EXISTS;
CREATE TABLE IF NOT EXISTS item_type (
    id INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(64),
    UNIQUE (name)
 );
CREATE INDEX idx_item_type_name ON item_type (name);

DROP TABLE vehicle_type IF EXISTS;
CREATE TABLE IF NOT EXISTS vehicle_type (
    id INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(64),
    UNIQUE (name)
 );
CREATE INDEX idx_vehicle_type_name ON vehicle_type (name);

DROP TABLE measurement IF EXISTS;
CREATE TABLE IF NOT EXISTS measurement (
    id INTEGER IDENTITY PRIMARY KEY,
    entrance_date DATETIME NOT NULL,
    driver VARCHAR(255),
    item_type_id INTEGER,
    vehicle_type_id INTEGER,
    car_type_id INTEGER
);
ALTER TABLE measurement ADD FOREIGN KEY (item_type_id) REFERENCES item_type(id);
ALTER TABLE measurement ADD FOREIGN KEY (vehicle_type_id) REFERENCES vehicle_type(id);
ALTER TABLE measurement ADD FOREIGN KEY (car_type_id) REFERENCES car_type(id);
