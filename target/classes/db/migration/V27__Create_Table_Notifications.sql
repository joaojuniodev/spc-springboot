CREATE TABLE notifications (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    username       VARCHAR(100) NOT NULL,
    type           VARCHAR(20)  NOT NULL,
    title          VARCHAR(150) NOT NULL,
    body           TEXT         NOT NULL,
    reference_id   BIGINT       NULL,
    reference_type VARCHAR(30)  NULL,
    is_read        BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_notifications_username (username),
    INDEX idx_notifications_username_read (username, is_read)
);