-- 1. renomeia tabela
ALTER TABLE missa RENAME TO masses;

-- 2. remove fk
ALTER TABLE masses
ADD INDEX idx_masses_liturgical_calendar_id (mass_of_liturgical_calendar_id);