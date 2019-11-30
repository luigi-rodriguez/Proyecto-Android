<?php //////////////////////////////////////////////// REGISTRO \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	$servidor="localhost"; // Nombre o direccion del servidor
	$usuario="root"; // Tipo de usuario
	$clave=""; // Contraseña o clave de usuario
	$baseDeDatos="dbNueva"; // Nombre de la base de datos
	$con = mysqli_connect($servidor, $usuario, $clave, $baseDeDatos)

	$nombre  = $_POST["nombre"]; 
	$usuario = $_POST["usuario"]; 
	$edad	 = $_POST["edad"]; 
	$clave	 = $_POST["clave"];


	$statement = mysqli_prepare($con, "INSERT INTO user (nombre, usuario, edad, clave) VALUES (?, ?, ?, ?)");
	mysqli_Stmt_bind_param($statement, "ssis", $Nombre, $usuario, $edad, $clave);
	mysqli_Stmt_execute($statement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);

	// echo"conexión establecida con el servidor";
?>