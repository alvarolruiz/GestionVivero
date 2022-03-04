IF NOT EXISTS (SELECT * FROM sys.databases WHERE NAME = 'LaurinoRuiz')
CREATE DATABASE LaurinoRuiz;
GO
USE LaurinoRuiz;
GO
DROP TABLE IF EXISTS [dbo].[Productos]
CREATE TABLE Productos(
id INT IDENTITY(1,1) PRIMARY KEY,
descripcion VARCHAR(50) NOT NULL,
precioUnitario SMALLMONEY NOT NULL,
unidadesDisponibles INT
);

DROP TABLE IF EXISTS [dbo].[ProductosJardineria]
CREATE TABLE ProductosJardineria(
id INT NOT NULL PRIMARY KEY,
CONSTRAINT FK_idProductosJardineria_Cascade  FOREIGN KEY (id)  REFERENCES Productos (id) 
ON DELETE CASCADE 
ON UPDATE CASCADE
)

DROP TABLE IF EXISTS [dbo].[TiposPlanta]
CREATE TABLE TiposPlanta(
id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
tipo VARCHAR (20) NOT NULL,
);

DROP TABLE IF EXISTS [dbo].[ProductosPlanta]
CREATE TABLE ProductosPlanta(
id INT NOT NULL PRIMARY KEY,
idTipoPlanta INT NOT NULL  
CONSTRAINT FK_idProductosPlanta_Cascade FOREIGN KEY (id) REFERENCES Productos (id)
ON DELETE CASCADE 
ON UPDATE CASCADE,
CONSTRAINT FK_idTipoPlanta_Cascade FOREIGN KEY (idTipoPlanta) REFERENCES TiposPlanta(id)
ON DELETE CASCADE 
ON UPDATE CASCADE
);



DROP TABLE IF EXISTS [dbo].[Facturas]
CREATE TABLE Facturas (
idFactura INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
idCliente INT NOT NULL,
idVendedor INT NOT NULL,
fecha DATE NOT NULL,
importeTotal Money NOT NULL
);

DROP TABLE IF EXISTS [dbo].[FilasFactura]
CREATE TABLE FilasFactura (
idFactura INT NOT NULL,
idProducto INT NOT NULL,
cantidad INT NOT NULL,
precioUnitario smallMoney NOT NULL,
total smallMoney NOT NULL,
CONSTRAINT FK_idLineasFactura FOREIGN KEY (idFactura) REFERENCES Facturas(idFactura)
ON DELETE CASCADE 
ON UPDATE CASCADE,
);

DROP TABLE IF EXISTS [dbo].[Vendedores]
CREATE TABLE Vendedores(
idVendedor INT IDENTITY(1,1) NOT NULL,
nombre VARCHAR(50) NOT NULL,
dni VARCHAR (9) NOT NULL,
direccion VARCHAR (60) NOT NULL,
codigoPostal INT,
ciudad VARCHAR (30) NOT NULL,
telefonoContacto INT NOT NULL,
correoElectronico VARCHAR (50) NOT NULL,
usuario VARCHAR(25) NOT NULL,
contraseña VARCHAR(25) NOT NULL
);

DROP TABLE IF EXISTS [dbo].[Gestores]
CREATE TABLE Gestores(
idGestor INT IDENTITY(1,1) NOT NULL,
nombre VARCHAR(50) NOT NULL,
dni VARCHAR (9) NOT NULL,
direccion VARCHAR (60) NOT NULL,
codigoPostal INT,
ciudad VARCHAR (30) NOT NULL,
telefonoContacto INT NOT NULL,
correoElectronico VARCHAR (50) NOT NULL,
usuario VARCHAR(25) NOT NULL,
contraseña VARCHAR(25) NOT NULL
);

DROP TABLE IF EXISTS [dbo].[Clientes]
CREATE TABLE Clientes(
idCliente INT IDENTITY(1,1) NOT NULL,
nombre VARCHAR(30) NOT NULL,
dni VARCHAR (9) NOT NULL,
direccion VARCHAR (60) NOT NULL,
codigoPostal INT,
ciudad VARCHAR (30) NOT NULL,
telefonoContacto INT NOT NULL,
correoElectronico VARCHAR (50) NOT NULL
);
--Procedimientos

-- Procedimiento para añadir una planta
GO
CREATE PROC sp_InsertProductoPlanta
@descripcion VARCHAR(50), 
@precioUnitario SMALLMONEY, 
@unidadesDisponibles INT, 
@idTipoPlanta INT
AS
BEGIN
	INSERT INTO Productos VALUES (@descripcion, @precioUnitario,@unidadesDisponibles);
	INSERT INTO ProductosPlanta  VALUES ((SELECT MAX(id) FROM Productos), @idTipoPlanta);
END
GO

--Procedimiento para añadir un producto jardineria
GO
CREATE PROC sp_InsertProductoJardineria
@descripcion VARCHAR(50), 
@precioUnitario SMALLMONEY, 
@unidadesDisponibles INT
AS
BEGIN
	INSERT INTO Productos VALUES (@descripcion, @precioUnitario,@unidadesDisponibles);
	INSERT INTO ProductosJardineria VALUES ((SELECT MAX(id) FROM Productos));
END
GO

-- Procedimiento para actualizar una planta
GO
CREATE PROC sp_actualizarProductoPlanta
@id INT,
@descripcion VARCHAR(50), 
@precioUnitario SMALLMONEY, 
@unidadesDisponibles INT, 
@idTipoPlanta INT
AS
BEGIN
	UPDATE Productos SET descripcion = @descripcion, precioUnitario = @precioUnitario, unidadesDisponibles = @unidadesDisponibles WHERE id = @id;
	UPDATE ProductosPlanta  SET idTipoPlanta = @idTipoPlanta WHERE id = @id;
END
GO

--Procedimiento para generar un informe de las ventas totales de cada producto en un rango temporal determinado
GO
 CREATE PROCEDURE sp_GenerarInforme
 @fechaInicio Date,
 @fechaFinal Date
 AS
BEGIN
	 SELECT p. id, sum(ff.total) as totalVendido From productos as p
	 left join FilasFactura as ff on p.id = ff.idProducto
	 join Facturas f on ff.idFactura = f.idFactura
	 where f.fecha >= @fechaInicio AND f.fecha<=@fechaFinal
	 group by p.id
END
GO
--Funciones

-- Table-Valued-Function para seleccionar un producto en concreto. Cuando se trate de un producto jardineria el idTipoPlanta será 0
GO
CREATE FUNCTION GetProducto (@id INT)
	RETURNS @Producto TABLE(
	id INT,
	descripcion VARCHAR(50),
	precioUnitario SMALLMONEY,
	unidadesDisponibles INT,
	idTipoPlanta INT)
AS
	BEGIN
	DECLARE @noPlanta INT = 0;
		IF (Select Count(*) From ProductosJardineria WHERE id = @id) = 0
			BEGIN
				INSERT INTO @Producto
				SELECT p.id, descripcion, precioUnitario, unidadesDisponibles, pp.idTipoPlanta FROM Productos  AS p JOIN ProductosPlanta AS pp 
				ON p.id = pp.id 
				WHERE p.id = @id
			END -- IF
		ELSE
			BEGIN
				INSERT INTO @Producto
				SELECT id, descripcion, precioUnitario, unidadesDisponibles, @noPlanta FROM Productos  
				WHERE id = @id
			END -- ELSE
		RETURN 
	END
GO

-- Table-Valued-Function para seleccionar un producto en concreto. Esta funcion se utiliza en el from. Cuando se trate de un producto jardineria el idTipoPlanta será 0
GO
CREATE FUNCTION GetProductosPlanta ()
	RETURNS TABLE
AS
RETURN 
SELECT p.id, p.descripcion, p.precioUnitario, p.unidadesDisponibles, pp.idTipoPlanta FROM Productos AS p JOIN ProductosPlanta pp ON p.id = pp.id;
GO

-- View para obtener todos los productos planta

GO
CREATE VIEW view_ProductosTipo
AS SELECT p.id, p.descripcion, p.precioUnitario, p.unidadesDisponibles, pp.idTipoPlanta
FROM Productos AS p 
LEFT JOIN ProductosPlanta AS pp
ON p.id = pp.id
GO


GO
CREATE VIEW view_ProductosPlanta
AS
SELECT p.id, p.descripcion, p.precioUnitario, p.unidadesDisponibles, tp.tipo
FROM Productos AS p 
JOIN ProductosPlanta AS pp
ON p.id = pp.id
JOIN TiposPlanta AS tp
ON pp.idTipoPlanta = tp.id;
GO

GO
CREATE VIEW view_ProductosJardineria
AS
SELECT p.id, p.descripcion, p.precioUnitario, p.unidadesDisponibles 
FROM Productos AS p
JOIN ProductosJardineria AS pj
ON p.id = pj.id
GO

 --Tipos Planta
Insert Into TiposPlanta VALUES ('Planta Interior');
Insert Into TiposPlanta VALUES ('Planta Exterior');
Insert Into TiposPlanta VALUES ('Árbol Ornamental');
Insert Into TiposPlanta VALUES ('Árbol Frutal');
--Plantas
--Planta interior
EXEC sp_InsertProductoPlanta @descripcion = 'Cactus', @precioUnitario =  7.5, @unidadesDisponibles =  50, @idTipoPlanta = 1 ;
EXEC sp_InsertProductoPlanta @descripcion = 'Orquídea', @precioUnitario =  8, @unidadesDisponibles =  50, @idTipoPlanta = 1 ;
EXEC sp_InsertProductoPlanta @descripcion = 'Bromelia', @precioUnitario =  7.5, @unidadesDisponibles =  50, @idTipoPlanta = 1 ;
EXEC sp_InsertProductoPlanta @descripcion = 'Echeveria', @precioUnitario =  7.5, @unidadesDisponibles =  50, @idTipoPlanta = 1 ;
EXEC sp_InsertProductoPlanta @descripcion = 'Arbol de jade', @precioUnitario =  7.5, @unidadesDisponibles =  50, @idTipoPlanta = 1 ;
EXEC sp_InsertProductoPlanta @descripcion = 'Senecio', @precioUnitario =  7.5, @unidadesDisponibles =  50, @idTipoPlanta = 1 ;
--

--Planta Exterior
EXEC sp_InsertProductoPlanta @descripcion = 'Rosal', @precioUnitario =  6, @unidadesDisponibles =  50, @idTipoPlanta = 2 ;
EXEC sp_InsertProductoPlanta @descripcion = 'Jazmin', @precioUnitario =  8, @unidadesDisponibles =  50, @idTipoPlanta = 2 ;
EXEC sp_InsertProductoPlanta @descripcion = 'Buganvilla', @precioUnitario =  15.75, @unidadesDisponibles =  50, @idTipoPlanta = 2 ;
--
--Arbol Ornamental
EXEC sp_InsertProductoPlanta @descripcion = 'Mimosa', @precioUnitario =  39.99, @unidadesDisponibles =  50, @idTipoPlanta = 3 ;
EXEC sp_InsertProductoPlanta @descripcion = 'Arce', @precioUnitario =  25.99, @unidadesDisponibles =  50, @idTipoPlanta = 3 ;
--
--Arbol Futal
EXEC sp_InsertProductoPlanta @descripcion = 'Limonero', @precioUnitario =  10, @unidadesDisponibles =  50, @idTipoPlanta = 4 ;
EXEC sp_InsertProductoPlanta @descripcion = 'Manzano', @precioUnitario =  10, @unidadesDisponibles =  50, @idTipoPlanta = 4 ;


--Productos Jardineria
 EXEC sp_InsertProductoJardineria @descripcion = 'Tijeras de poda', @precioUnitario =  5.99, @unidadesDisponibles =  35;
 EXEC sp_InsertProductoJardineria @descripcion = 'Pala', @precioUnitario =  12.99, @unidadesDisponibles =  35;
 EXEC sp_InsertProductoJardineria @descripcion = 'Regadera', @precioUnitario =  5, @unidadesDisponibles =  35;
 EXEC sp_InsertProductoJardineria @descripcion = 'Pulverizador', @precioUnitario =  2.99, @unidadesDisponibles =  35;
 EXEC sp_InsertProductoJardineria @descripcion = 'Rastrillo', @precioUnitario =  9, @unidadesDisponibles =  35;
 EXEC sp_InsertProductoJardineria @descripcion = 'Semillero', @precioUnitario =  4, @unidadesDisponibles =  35;

 --Usuarios

 --Gestores
 Insert Into Gestores  Values('root', '77866502W', 'La casa del jefe', '41010', 'Sevilla', 666334455,'root@gmail.com', 'root', 'toor');
 Insert Into Gestores  Values('root2', '77848439V', 'La casa del subjefe', '41010', 'Sevilla', 646777837,'root2@gmail.com', 'root2', '2toor');

 --Vendedores
 Insert Into Vendedores  Values('vendedor', '59546563T', 'La casa del empleado 1', '41010', 'Sevilla', 666228895,'vendedor@gmail.com', 'vendedor', 'abc');
 Insert Into Vendedores  Values('vendedor2', '23689812L', 'La casa del empleado 2', '41010', 'Sevilla', 666228265,'vendedor2@gmail.com', 'vendedor2', 'cba');

 --Clientes
 Insert Into Clientes Values ('clienteNoRegistrado', 'NUUUUUULL', 'La casa de nadie', '00000', 'NoWhere', 000000000,'noRegistrado@gmail.com');
 Insert Into Clientes Values ('cliente', '68895536W', 'La casa del cliente 1', '41010', 'Sevilla', 666229913,'cliente1@gmail.com');
 Insert Into Clientes Values ('cliente2', '99108945J', 'La casa del cliente 2', '41010', 'Sevilla', 666778866,'cliente2@gmail.com');


 EXEC sp_GenerarInforme @fechaInicio = '2/2/21', @fechaFinal = '2/3/23';


