INSERT INTO roles (name) VALUES
('ROLE_ADMIN'),
('ROLE_COORDINATOR'),
('ROLE_CATECHIST');

INSERT INTO permissions (name, description) VALUES
('USER_READ', 'Pode visualizar usuarios'),
('USER_CREATE', 'Pode cadastrar usuarios'),
('USER_UPDATE', 'Pode editar usuarios'),
('USER_DELETE', 'Pode excluir usuarios'),
('ROLE_READ', 'Pode visualizar roles'),
('ROLE_CREATE', 'Pode cadastrar roles'),
('ROLE_UPDATE', 'Pode editar roles'),
('ROLE_DELETE', 'Pode excluir roles'),
('PERMISSION_READ', 'Pode visualizar permissoes'),
('PERMISSION_CREATE', 'Pode cadastrar permissoes'),
('PERMISSION_UPDATE', 'Pode editar permissoes'),
('PERMISSION_DELETE', 'Pode excluir permissoes'),
('CATECHIST_READ', 'Pode visualizar catequistas'),
('CATECHIST_CREATE', 'Pode cadastrar catequistas'),
('CATECHIST_UPDATE', 'Pode editar catequistas'),
('CATECHIST_DELETE', 'Pode excluir catequistas'),
('CATECHUMEN_READ', 'Pode visualizar catequizandos'),
('CATECHUMEN_CREATE', 'Pode cadastrar catequizandos'),
('CATECHUMEN_UPDATE', 'Pode editar catequizandos'),
('CATECHUMEN_DELETE', 'Pode excluir catequizandos'),
('STEP_READ', 'Pode visualizar etapas'),
('STEP_CREATE', 'Pode cadastrar etapas'),
('STEP_UPDATE', 'Pode editar etapas'),
('STEP_DELETE', 'Pode excluir etapas'),
('MASS_READ', 'Pode visualizar missas'),
('MASS_CREATE', 'Pode cadastrar missas'),
('MASS_UPDATE', 'Pode editar missas'),
('MASS_DELETE', 'Pode excluir missas'),
('PRESENCE_READ', 'Pode visualizar presencas'),
('PRESENCE_REGISTER', 'Pode registrar presenca'),
('PRESENCE_REGISTER_RETROACTIVE', 'Pode registrar presenca retroativa'),
('PRESENCE_HISTORY_READ', 'Pode visualizar historico de presencas'),
('PRESENCE_UPDATE', 'Pode editar presencas'),
('PRESENCE_DELETE', 'Pode excluir presencas'),
('CATECHUMEN_READ_OWN_STEP', 'Pode visualizar catequizandos da propria etapa'),
('PRESENCE_READ_OWN_CATECHUMENS', 'Pode visualizar presencas dos proprios catequizandos');

INSERT INTO role_permission (role_id, permission_id)
SELECT r.id, p.id
FROM roles r
CROSS JOIN permissions p
WHERE r.name IN ('ROLE_ADMIN', 'ROLE_COORDINATOR');

INSERT INTO role_permission (role_id, permission_id)
SELECT r.id, p.id
FROM roles r
JOIN permissions p ON p.name IN (
    'MASS_READ',
    'PRESENCE_REGISTER',
    'PRESENCE_REGISTER_RETROACTIVE',
    'PRESENCE_HISTORY_READ',
    'CATECHUMEN_READ_OWN_STEP',
    'PRESENCE_READ_OWN_CATECHUMENS'
)
WHERE r.name = 'ROLE_CATECHIST';