-PRUEBAS UNITARIAS PARA TRIGGERS--

--1. Trigger de validaci�n de correo �nico en Usuarios
--Prueba: Intentar insertar dos usuarios con el mismo correo.

-- Limpieza previa
DELETE FROM Usuarios WHERE Correo = 'prueba@correo.com';

-- Inserci�n v�lida
INSERT INTO Usuarios (NombreCompleto, Correo, Contrasena, Rol)
VALUES ('Usuario Prueba', 'prueba@correo.com', '1234', 'Talento');

-- Inserci�n duplicada (debe fallar por trigger o restricci�n �nica)
BEGIN TRY
    INSERT INTO Usuarios (NombreCompleto, Correo, Contrasena, Rol)
    VALUES ('Usuario Prueba 2', 'prueba@correo.com', '5678', 'Talento');
    PRINT 'ERROR: El trigger no funcion� correctamente';
END TRY
BEGIN CATCH
    PRINT 'OK: Trigger o restricci�n �nica evit� duplicados';
END CATCH;

-- Limpieza
DELETE FROM Usuarios WHERE Correo = 'prueba@correo.com';

--RESULTADO--
--La segunda inserci�n debe fallar y el trigger (o la restricci�n �nica) debe impedir el duplicado.



--2. Trigger de auditor�a en Postulaciones--
--Prueba: Insertar, actualizar y eliminar una postulaci�n, luego verificar la tabla de auditor�a.

-- Inserta datos de prueba
DECLARE @IdUsuario INT, @IdVacante INT;
INSERT INTO Usuarios (NombreCompleto, Correo, Contrasena, Rol) VALUES ('Talento Test', 'talento@correo.com', '1234', 'Talento');
SET @IdUsuario = SCOPE_IDENTITY();
INSERT INTO Vacantes (IdEmpresa, Titulo) VALUES (@IdUsuario, 'Vacante Test');
SET @IdVacante = SCOPE_IDENTITY();

-- Inserta postulaci�n
INSERT INTO Postulaciones (IdUsuario, IdVacante) VALUES (@IdUsuario, @IdVacante);

-- Actualiza postulaci�n
UPDATE Postulaciones SET Estado = 'Aceptado' WHERE IdUsuario = @IdUsuario AND IdVacante = @IdVacante;

-- Elimina postulaci�n
DELETE FROM Postulaciones WHERE IdUsuario = @IdUsuario AND IdVacante = @IdVacante;

-- Verifica auditor�a
SELECT * FROM AuditoriaCambios WHERE Tabla = 'Postulaciones' AND Datos IS NOT NULL;

-- Limpieza
DELETE FROM Vacantes WHERE IdVacante = @IdVacante;
DELETE FROM Usuarios WHERE IdUsuario = @IdUsuario;

--Resultado esperado:--
--Deben existir registros en AuditoriaCambios para las operaciones INSERT, UPDATE y DELETE sobre la tabla Postulaciones.


--3. Trigger que evita inscripciones duplicadas en Cursos_Usuarios--
--Prueba: Intentar inscribir dos veces al mismo usuario en el mismo curso.

-- Prepara datos
DECLARE @IdCurso INT, @IdUsuario INT;
INSERT INTO Usuarios (NombreCompleto, Correo, Contrasena, Rol) VALUES ('Alumno Test', 'alumno@correo.com', '1234', 'Talento');
SET @IdUsuario = SCOPE_IDENTITY();
INSERT INTO Cursos (Titulo) VALUES ('Curso Test');
SET @IdCurso = SCOPE_IDENTITY();

-- Primera inscripci�n (v�lida)
INSERT INTO Cursos_Usuarios (IdCurso, IdUsuario) VALUES (@IdCurso, @IdUsuario);

-- Segunda inscripci�n (debe fallar)
BEGIN TRY
    INSERT INTO Cursos_Usuarios (IdCurso, IdUsuario) VALUES (@IdCurso, @IdUsuario);
    PRINT 'ERROR: El trigger no funcion� correctamente';
END TRY
BEGIN CATCH
    PRINT 'OK: Trigger evit� inscripci�n duplicada';
END CATCH;

-- Limpieza
DELETE FROM Cursos_Usuarios WHERE IdCurso = @IdCurso AND IdUsuario = @IdUsuario;
DELETE FROM Cursos WHERE IdCurso = @IdCurso;
DELETE FROM Usuarios WHERE IdUsuario = @IdUsuario;

--Resultado esperado:--
--La segunda inscripci�n debe ser rechazada por el trigger.

