CREATE VIEW vw_VacantesConEmpresas AS
SELECT 
    V.IdVacante,
    V.Titulo,
    V.Descripcion,
    V.FechaPublicacion,
    E.NombreEmpresa AS Empresa
FROM Vacantes V
JOIN Empresas E ON V.IdEmpresa = E.IdEmpresa;

CREATE VIEW vw_PostulacionesDetalle AS
SELECT 
    P.IdPostulacion,
    U.NombreCompleto AS Talento,
    V.Titulo AS Vacante,
    P.Estado,
    P.FechaPostulacion
FROM Postulaciones P
JOIN Usuarios U ON P.IdUsuario = U.IdUsuario
JOIN Vacantes V ON P.IdVacante = V.IdVacante;

CREATE VIEW vw_CursosInscritos AS
SELECT 
    CU.IdCurso,
    C.Titulo,
    CU.IdUsuario,
    U.NombreCompleto,
    CU.FechaInscripcion
FROM Cursos_Usuarios CU
JOIN Cursos C ON CU.IdCurso = C.IdCurso
JOIN Usuarios U ON CU.IdUsuario = U.IdUsuario;

CREATE VIEW vw_IndicadoresUsuarios AS
SELECT 
    I.IdIndicador,
    U.NombreCompleto,
    I.Tipo,
    I.Valor,
    I.FechaRegistro
FROM Indicadores I
JOIN Usuarios U ON I.IdUsuario = U.IdUsuario;

-- Vista para empresas con sus vacantes
CREATE VIEW vw_EmpresasConVacantes AS
SELECT 
    E.IdEmpresa,
    E.NombreEmpresa,
    E.NIT,
    E.Sector,
    COUNT(V.IdVacante) AS TotalVacantes,
    E.FechaRegistro
FROM Empresas E
LEFT JOIN Vacantes V ON E.IdEmpresa = V.IdEmpresa
GROUP BY E.IdEmpresa, E.NombreEmpresa, E.NIT, E.Sector, E.FechaRegistro;

-- ============================================
-- VISTAS DE ABSTRACCIÓN PARA TABLAS PRINCIPALES
-- ============================================

-- Vista abstracta para Usuarios
CREATE VIEW vw_Usuarios AS
SELECT 
    IdUsuario,
    NombreCompleto,
    Correo,
    Contrasena,
    Rol,
    IdDiscapacidad,
    FechaRegistro
FROM Usuarios;

-- Vista abstracta para Empresas
CREATE VIEW vw_Empresas AS
SELECT 
    IdEmpresa,
    NombreEmpresa,
    NIT,
    Direccion,
    Telefono,
    CorreoContacto,
    Sector,
    Descripcion,
    FechaRegistro
FROM Empresas;

-- Vista abstracta para Vacantes
CREATE VIEW vw_Vacantes AS
SELECT 
    IdVacante,
    IdEmpresa,
    Titulo,
    Descripcion,
    Requisitos,
    FechaPublicacion
FROM Vacantes;

-- Vista abstracta para Postulaciones
CREATE VIEW vw_Postulaciones AS
SELECT 
    IdPostulacion,
    IdUsuario,
    IdVacante,
    FechaPostulacion,
    Estado
FROM Postulaciones;

-- Vista abstracta para Cursos
CREATE VIEW vw_Cursos AS
SELECT 
    IdCurso,
    Titulo,
    Descripcion,
    Accesibilidad,
    URLContenido
FROM Cursos;

-- Vista abstracta para Cursos_Usuarios
CREATE VIEW vw_CursosUsuarios AS
SELECT 
    IdCurso,
    IdUsuario,
    FechaInscripcion
FROM Cursos_Usuarios;

-- Vista abstracta para Indicadores
CREATE VIEW vw_Indicadores AS
SELECT 
    IdIndicador,
    IdUsuario,
    Tipo,
    Valor,
    FechaRegistro
FROM Indicadores;

-- Vista abstracta para Tipos_Discapacidad
CREATE VIEW vw_TiposDiscapacidad AS
SELECT 
    IdDiscapacidad,
    Nombre,
    Descripcion
FROM Tipos_Discapacidad;