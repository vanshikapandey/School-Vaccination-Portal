CREATE TABLE students (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    student_id VARCHAR(255) NOT NULL,
    student_class VARCHAR(255) NOT NULL
);

CREATE TABLE vaccination_drives (
    id BIGSERIAL PRIMARY KEY,
    vaccine_name VARCHAR(255),
    date DATE,
    available_doses INT
);

CREATE TABLE vaccination_records (
    id BIGSERIAL PRIMARY KEY,
    student_id BIGINT,
    drive_id BIGINT,
    date_given DATE,
    vaccinated BOOLEAN,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (drive_id) REFERENCES vaccination_drives(id) ON DELETE CASCADE
);