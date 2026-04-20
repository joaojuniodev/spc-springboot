ALTER TABLE etapa RENAME TO steps;
ALTER TABLE steps RENAME COLUMN etapa TO step_name;