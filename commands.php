
<?php


error_reporting(E_ALL);
ini_set('display_errors', TRUE);
ini_set('display_startup_errors', TRUE);
ini_set('error_reporting', E_ALL);

	$CURRENT_PATH_COMMAND = "pwd";
	$CHANGE_PATH_COMMAND = "cd ";
	$CREATE_DIR_COMMAND = "mkdir ";
	$CREATE_FILE_COMMAND = "touch ";
	$REMOVE_FILE_COMMAND = "rm ";
	$COPY_FILE_COMMAND =  "cp ";
	$MOVE_FILE_COMMAND = "cv ";
	$FILE_LIST_COMMAND = "dir -l";
	
	var_dump($_POST);

	$currentCommand = "";
	$currentFolder = "";
	$messageClass = "";
	$generalMessage = "";
	$initialized = "";
	$selectedFileName = "";
	if(isset($_POST['currenPath']) && $_POST['currenPath'] != "")
	{
		$currentFolder = $_POST['currenPath'];
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


	if(isset($_POST["btnDelete"])) 
	{
		if( isset($_POST['selectedFileName']) && $_POST['selectedFileName'] != "")
		{
			echo "'rm " . $currentFolder . "/" . $_POST['selectedFileName']."'";
			echo  exec("sudo rm " . $currentFolder . "/" . $_POST['selectedFileName'],$test);
			var_dump($test);
			$selectedFileName = "";
			$generalMessage="The file selected was deleted !";
			$messageClass = "alert-info";
			
			
		}else
		{
			$generalMessage="One file needs to be selected!";
			$messageClass = "alert-danger";
		}
	}

	if(isset($_POST["btnCopy"])) 
	{
		if( isset($_POST['selectedFileName']) && $_POST['selectedFileName'] != "")
		{
			if(isset($_POST['fileNewName']) && $_POST['fileNewName'] != "")
			{
				exec("cp {$currentFolder}/{$_POST['selectedFileName']} {$_POST['fileNewName']}");
				$selectedFileName = "";
				$generalMessage="The file selected was copied !";
				$messageClass = "alert-info";
			}
			else {
				$generalMessage="The new path file cannot be null or empty !";
				$messageClass = "alert-danger";

			}
		}else
		{
			$generalMessage="One file needs to be selected!";
			$messageClass = "alert-danger";
		}
	}

	if(isset($_POST["btnMove"])) 
	{
		if( isset($_POST['selectedFileName']) && $_POST['selectedFileName'] != "")
		{
			if(isset($_POST['fileNewName']) && $_POST['fileNewName'] != "")
			{
				exec("mv {$currentFolder}/{$_POST['selectedFileName']} {$_POST['fileNewName']}");
				$selectedFileName = "";
				$generalMessage="The file selected was moved !";
				$messageClass = "alert-info";
			}
			else {
				$generalMessage="The current path cannot be null !";
				$messageClass = "alert-danger";

			}
		}else
		{
			$generalMessage="One file needs to be selected!";
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

			$tableBody .= "<tr onClick='rowSelected(this)'>";
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