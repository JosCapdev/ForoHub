-- Agregar columna activo a Curso
ALTER TABLE cursos
ADD COLUMN activo BOOLEAN NOT NULL DEFAULT TRUE;
