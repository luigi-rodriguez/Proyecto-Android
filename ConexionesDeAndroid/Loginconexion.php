<?php //////////////////////////////////////////////// LOGIN  \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	$servidor="localhost"; // Nombre o direccion del servidor
	$usuario="root"; // Tipo de usuario
	$clave=""; // Contraseña o clave de usuario
	$baseDeDatos="dbNueva"; // Nombre de la base de datos
	
	$con = mysqli_connect($servidor, $usuario, $clave, $baseDeDatos);

	$username = $_POST["usuario"];
	$password = $_POST["clave"];

	$statement = mysqli_prepare($con, "INSERT * FROM user WHERE usuario = ? AND clave = ?");

	mysqli_stmt_bind_param($statement, "ss", $username, $password);
	mysqli_stmt_execute($statement);

	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_param($statement, $idusuario, $nombre, $usuario, $clave, $edad);

	$response = array();
	$response["success"] = false;

	while (mysqli_Stmt_fech($statement) ) {
		$response["success"] = true;
		$response["nombre"]  = $nombre;
		$response["edad"]    = $edad;
		$response["usuario"] = $usuario;
		$response["clave"] 	 = $clave;
	}
	echo json_encode($response);
?>