ALTER TABLE catequista RENAME TO catechists;

ALTER TABLE catechists DROP FOREIGN KEY fk_etapa_id;

ALTER TABLE catechists RENAME COLUMN etapa_id TO step_id;

ALTER TABLE catechists
ADD CONSTRAINT fk_catechists_steps
FOREIGN KEY (step_id) REFERENCES steps(id);