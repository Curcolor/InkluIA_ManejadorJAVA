--Crear tabla de auditoria
CREATE TABLE dbo.AuditoriaCambios (
    IdAuditoria INT IDENTITY(1,1) PRIMARY KEY,
    Tabla NVARCHAR(100),
    Operacion NVARCHAR(20),
    Usuario NVARCHAR(100),
    Fecha DATETIME DEFAULT GETDATE(),
    Datos XML
);
GO

--Triggers para cada tabla

--Usuarios
CREATE TRIGGER trg_Usuarios_Audit
ON dbo.Usuarios
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    -- Insert
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Usuarios', 'INSERT', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted;
    -- Update
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Usuarios', 'UPDATE', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted
    INNER JOIN deleted ON inserted.IdUsuario = deleted.IdUsuario;
    -- Delete
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Usuarios', 'DELETE', SYSTEM_USER, (SELECT * FROM deleted FOR XML AUTO)
    FROM deleted;
END;
GO

--Vacantes
CREATE TRIGGER trg_Vacantes_Audit
ON dbo.Vacantes
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Vacantes', 'INSERT', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Vacantes', 'UPDATE', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted
    INNER JOIN deleted ON inserted.IdVacante = deleted.IdVacante;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Vacantes', 'DELETE', SYSTEM_USER, (SELECT * FROM deleted FOR XML AUTO)
    FROM deleted;
END;
GO

--Postulaciones
CREATE TRIGGER trg_Postulaciones_Audit
ON dbo.Postulaciones
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Postulaciones', 'INSERT', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Postulaciones', 'UPDATE', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted
    INNER JOIN deleted ON inserted.IdPostulacion = deleted.IdPostulacion;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Postulaciones', 'DELETE', SYSTEM_USER, (SELECT * FROM deleted FOR XML AUTO)
    FROM deleted;
END;
GO

--Cursos
CREATE TRIGGER trg_Cursos_Audit
ON dbo.Cursos
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Cursos', 'INSERT', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Cursos', 'UPDATE', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted
    INNER JOIN deleted ON inserted.IdCurso = deleted.IdCurso;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Cursos', 'DELETE', SYSTEM_USER, (SELECT * FROM deleted FOR XML AUTO)
    FROM deleted;
END;
GO

--Cursos_Usuarios
CREATE TRIGGER trg_CursosUsuarios_Audit
ON dbo.Cursos_Usuarios
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Cursos_Usuarios', 'INSERT', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Cursos_Usuarios', 'UPDATE', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted
    INNER JOIN deleted ON inserted.IdCurso = deleted.IdCurso AND inserted.IdUsuario = deleted.IdUsuario;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Cursos_Usuarios', 'DELETE', SYSTEM_USER, (SELECT * FROM deleted FOR XML AUTO)
    FROM deleted;
END;
GO

--Indicadores
CREATE TRIGGER trg_Indicadores_Audit
ON dbo.Indicadores
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Indicadores', 'INSERT', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Indicadores', 'UPDATE', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted
    INNER JOIN deleted ON inserted.IdIndicador = deleted.IdIndicador;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Indicadores', 'DELETE', SYSTEM_USER, (SELECT * FROM deleted FOR XML AUTO)
    FROM deleted;
END;
GO

--Tipos_Discapacidad
CREATE TRIGGER trg_TiposDiscapacidad_Audit
ON dbo.Tipos_Discapacidad
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Tipos_Discapacidad', 'INSERT', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Tipos_Discapacidad', 'UPDATE', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted
    INNER JOIN deleted ON inserted.IdDiscapacidad = deleted.IdDiscapacidad;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Tipos_Discapacidad', 'DELETE', SYSTEM_USER, (SELECT * FROM deleted FOR XML AUTO)
    FROM deleted;
END;
GO

-----------------------------------------------
---------------OTROS TRIGGERS------------------

----[dbo].[Usuarios]
--Evita insertar usuarios con correo duplicado (adem�s del indice �nico).
--     
CREATE TRIGGER trg_Usuarios_Validaciones
ON dbo.Usuarios
INSTEAD OF INSERT
AS
BEGIN
    IF EXISTS (
        SELECT 1 FROM inserted i
        JOIN dbo.Usuarios u ON i.Correo = u.Correo
    )
    BEGIN
        RAISERROR('Ya existe un usuario con ese correo.', 16, 1);
        RETURN;
    END
    INSERT INTO dbo.Usuarios (NombreCompleto, Correo, Contrasena, Rol, IdDiscapacidad, FechaRegistro)
    SELECT NombreCompleto, Correo, Contrasena, Rol, IdDiscapacidad, FechaRegistro
    FROM inserted;
END;
GO

CREATE TRIGGER trg_Usuarios_Auditoria
ON dbo.Usuarios
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    -- INSERT
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Usuarios', 'INSERT', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted;
    -- UPDATE
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Usuarios', 'UPDATE', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted
    INNER JOIN deleted ON inserted.IdUsuario = deleted.IdUsuario;
    -- DELETE
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Usuarios', 'DELETE', SYSTEM_USER, (SELECT * FROM deleted FOR XML AUTO)
    FROM deleted;
END;
GO

--[dbo].[Vacantes]
--Evita eliminar vacantes si existen postulaciones asociadas.
--Auditor�a de cambios.

CREATE TRIGGER trg_Vacantes_PreventDelete
ON dbo.Vacantes
INSTEAD OF DELETE
AS
BEGIN
    IF EXISTS (
        SELECT 1 FROM deleted d
        JOIN dbo.Postulaciones p ON d.IdVacante = p.IdVacante
    )
    BEGIN
        RAISERROR('No se puede eliminar la vacante porque tiene postulaciones asociadas.', 16, 1);
        RETURN;
    END
    DELETE FROM dbo.Vacantes WHERE IdVacante IN (SELECT IdVacante FROM deleted);
END;
GO

CREATE TRIGGER trg_Vacantes_Auditoria
ON dbo.Vacantes
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Vacantes', 'INSERT', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Vacantes', 'UPDATE', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted
    INNER JOIN deleted ON inserted.IdVacante = deleted.IdVacante;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Vacantes', 'DELETE', SYSTEM_USER, (SELECT * FROM deleted FOR XML AUTO)
    FROM deleted;
END;
GO


--[dbo].[Postulaciones]
--Evita duplicidad de postulaciones (un usuario no puede postularse dos veces a la misma vacante).
--Auditor�a de cambios.
CREATE TRIGGER trg_Postulaciones_Validaciones
ON dbo.Postulaciones
INSTEAD OF INSERT
AS
BEGIN
    IF EXISTS (
        SELECT 1 FROM inserted i
        JOIN dbo.Postulaciones p
        ON i.IdUsuario = p.IdUsuario AND i.IdVacante = p.IdVacante
    )
    BEGIN
        RAISERROR('El usuario ya est� postulado a esta vacante.', 16, 1);
        RETURN;
    END
    INSERT INTO dbo.Postulaciones (IdUsuario, IdVacante, FechaPostulacion, Estado)
    SELECT IdUsuario, IdVacante, FechaPostulacion, Estado
    FROM inserted;
END;
GO

CREATE TRIGGER trg_Postulaciones_Auditoria
ON dbo.Postulaciones
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Postulaciones', 'INSERT', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Postulaciones', 'UPDATE', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted
    INNER JOIN deleted ON inserted.IdPostulacion = deleted.IdPostulacion;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Postulaciones', 'DELETE', SYSTEM_USER, (SELECT * FROM deleted FOR XML AUTO)
    FROM deleted;
END;
GO

--[dbo].[Cursos]
--Evita eliminar cursos si hay usuarios inscritos.
--Auditor�a de cambios.
CREATE TRIGGER trg_Cursos_PreventDelete
ON dbo.Cursos
INSTEAD OF DELETE
AS
BEGIN
    IF EXISTS (
        SELECT 1 FROM deleted d
        JOIN dbo.Cursos_Usuarios cu ON d.IdCurso = cu.IdCurso
    )
    BEGIN
        RAISERROR('No se puede eliminar el curso porque existen inscripciones asociadas.', 16, 1);
        RETURN;
    END
    DELETE FROM dbo.Cursos WHERE IdCurso IN (SELECT IdCurso FROM deleted);
END;
GO

CREATE TRIGGER trg_Cursos_Auditoria
ON dbo.Cursos
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Cursos', 'INSERT', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Cursos', 'UPDATE', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted
    INNER JOIN deleted ON inserted.IdCurso = deleted.IdCurso;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Cursos', 'DELETE', SYSTEM_USER, (SELECT * FROM deleted FOR XML AUTO)
    FROM deleted;
END;
GO

--[dbo].[Cursos_Usuarios]
--Evita inscribir dos veces al mismo usuario en el mismo curso.
--Auditor�a de cambios.
CREATE TRIGGER trg_CursosUsuarios_Validaciones
ON dbo.Cursos_Usuarios
INSTEAD OF INSERT
AS
BEGIN
    IF EXISTS (
        SELECT 1 FROM inserted i
        JOIN dbo.Cursos_Usuarios cu
        ON i.IdCurso = cu.IdCurso AND i.IdUsuario = cu.IdUsuario
    )
    BEGIN
        RAISERROR('El usuario ya est� inscrito en este curso.', 16, 1);
        RETURN;
    END
    INSERT INTO dbo.Cursos_Usuarios (IdCurso, IdUsuario, FechaInscripcion)
    SELECT IdCurso, IdUsuario, FechaInscripcion
    FROM inserted;
END;
GO

CREATE TRIGGER trg_CursosUsuarios_Auditoria
ON dbo.Cursos_Usuarios
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Cursos_Usuarios', 'INSERT', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Cursos_Usuarios', 'UPDATE', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted
    INNER JOIN deleted ON inserted.IdCurso = deleted.IdCurso AND inserted.IdUsuario = deleted.IdUsuario;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Cursos_Usuarios', 'DELETE', SYSTEM_USER, (SELECT * FROM deleted FOR XML AUTO)
    FROM deleted;
END;
GO

--[dbo].[Indicadores]
--Auditor�a de cambios.

CREATE TRIGGER trg_Indicadores_Auditoria
ON dbo.Indicadores
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Indicadores', 'INSERT', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Indicadores', 'UPDATE', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted
    INNER JOIN deleted ON inserted.IdIndicador = deleted.IdIndicador;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Indicadores', 'DELETE', SYSTEM_USER, (SELECT * FROM deleted FOR XML AUTO)
    FROM deleted;
END;
GO

--[dbo].[Tipos_Discapacidad]
--Evita eliminar tipos de discapacidad si hay usuarios asociados.
--Auditor�a de cambios.

CREATE TRIGGER trg_TiposDiscapacidad_PreventDelete
ON dbo.Tipos_Discapacidad
INSTEAD OF DELETE
AS
BEGIN
    IF EXISTS (
        SELECT 1 FROM deleted d
        JOIN dbo.Usuarios u ON d.IdDiscapacidad = u.IdDiscapacidad
    )
    BEGIN
        RAISERROR('No se puede eliminar el tipo de discapacidad porque hay usuarios asociados.', 16, 1);
        RETURN;
    END
    DELETE FROM dbo.Tipos_Discapacidad WHERE IdDiscapacidad IN (SELECT IdDiscapacidad FROM deleted);
END;
GO

CREATE TRIGGER trg_TiposDiscapacidad_Auditoria
ON dbo.Tipos_Discapacidad
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Tipos_Discapacidad', 'INSERT', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Tipos_Discapacidad', 'UPDATE', SYSTEM_USER, (SELECT * FROM inserted FOR XML AUTO)
    FROM inserted
    INNER JOIN deleted ON inserted.IdDiscapacidad = deleted.IdDiscapacidad;
    INSERT INTO dbo.AuditoriaCambios (Tabla, Operacion, Usuario, Datos)
    SELECT 'Tipos_Discapacidad', 'DELETE', SYSTEM_USER, (SELECT * FROM deleted FOR XML AUTO)
    FROM deleted;
END;
GO