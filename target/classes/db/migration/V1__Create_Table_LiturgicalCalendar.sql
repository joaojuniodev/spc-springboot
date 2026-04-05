CREATE TABLE liturgical_calendar (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    PRIMARY KEY (id)
);