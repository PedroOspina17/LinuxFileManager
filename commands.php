
<?php
	

	$CURRENT_PATH_COMMAND = "pwd";
	$CHANGE_PATH_COMMAND = "cd ";
	$CREATE_DIR_COMMAND = "mkdir ";
	$CREATE_FILE_COMMAND = "touch ";
	$REMOVE_FILE_COMMAND = "rm ";
	$COPY_FILE_COMMAND =  "cp ";
	$MOVE_FILE_COMMAND = "cv ";
	$FILE_LIST_COMMAND = "dir -l";
	
	$currentCommand = "";

	if(isset($_POST["changePath"])) 
	{
		echo "Actualizando ruta actual... <br/>";
		echo  "'{$CHANGE_PATH_COMMAND} {$_POST['currenPath']} '<br/>";
	}

	if(isset($_POST["createDir"])) {
		if(isset($_POST["dirName"]) && $_POST["dirName"] != '')
		{
			$currentCommand = "{$CREATE_DIR_COMMAND} {$_POST['currenPath']}/{$_POST['dirName']}";
			
			echo "{$currentCommand} <br/>";
			$salida = exec($currentCommand);
			echo "<pre>$salida</pre>";

			echo "<div class='alert alert-success'>directory created. <br/></div>";
			// $message = "directory created.. ";
			// $messageClass="alert-success";
		}
		else
		{
			// $message = "The directory name is required. ";
			// $messageClass="alert-danger";
			echo "<div class='alert alert-danger'> <br/></div>";
		}
	}

	

	$message = "Page loaded. ";
	$messageClass="alert-info";
	// echo "Page loaded !! <br/>";
	// $result = exec("");
	// echo $result;
	// echo $FILE_LIST_COMMAND . "<br/>";
	// echo $CURRENT_PATH_COMMAND."<br/>";

	

	include("index.php");
?>