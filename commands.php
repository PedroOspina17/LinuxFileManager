
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
	$currentFolder = "";
	$messageClass = "";
	$generalMessage = "";
	$initialized = "";
	if($_POST['currenPath'] != "")
	{
		$initialized = "none";
	}else
	{
		$initialized = "block";	
	}
	
	if(isset($_POST["firstLoad"])) 
	{
		$currentFolder = exec("pwd");
		$initialized = "none";
		$generalMessage = "Welcome to our amazing file manager !!!";
		$messageClass = "alert-info";
	}

	if(isset($_POST["changePath"])) 
	{
		if( isset($_POST['currenPath']) && $_POST['currenPath'] != "")
		{
			$generalMessage="The current path was changed !";
			$messageClass = "alert-info";
			$currentFolder = $_POST['currenPath'];
		}
		else {
			$generalMessage="The current path cannot be null !";
			$messageClass = "alert-danger";

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