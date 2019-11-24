
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
	$initialized = "block";
	$currentFolder = "";
	$messageClass = "alert-info";
	$generalMessage = "Welcome to our amazing file manager !!!";
	if(isset($_POST["firstLoad"])) 
	{
		$currentFolder = exec("pwd");
		$initialized = "none";
	}

	if(isset($_POST["changePath"])) 
	{
		if( isset($_POST['currenPath']) && $_POST['currenPath'] != "")
		{
			echo "Actualizando ruta actual... <br/>";
			//$result = exec("{$CHANGE_PATH_COMMAND} {$_POST['currenPath']}");
			echo exec("dir {$_POST['currenPath']}");
			$currentFolder = $_POST['currenPath'];
			echo $currentFolder;
		}
		else {
			echo "<div class='alert alert-danger'> <br/></div>";
		}
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


	exec("dir -l " . $currentFolder,$result);
	$tableBody = "";

	for ($i=0; $i < count($result); $i++) { 

		$fields = explode(' ', $result[$i]);
		if(count($fields) > 4)
		{
			$fileName = end($fields);
			$permissions = $fields[0];

			$tableBody .= "<tr>";
			$tableBody .= "<td>{$fileName}</td>";
			$tableBody .= "<td>{$permissions}</td>";

			$tableBody .= "</tr>";



		}
		
		
	}
	
	//$tableBody .= "</tBody>";

	
	 //echo $currentFolder;
	// echo $FILE_LIST_COMMAND . "<br/>";
	// echo $CURRENT_PATH_COMMAND."<br/>";
	
	
	include("index.php");
?>