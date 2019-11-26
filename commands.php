
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
	
	//var_dump($_POST);

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
		$currentFolder = exec("pwd")."/";
		$initialized = "none";
		$generalMessage = "Welcome to our amazing file manager !!!";
		$messageClass = "alert-info";
	}

	if(isset($_POST["changePath"])) 
	{

		if( isset($_POST['currenPath']) && $_POST['currenPath'] != "")
		{
			if(file_exists($_POST['currenPath']))
			{
				$generalMessage="The current path was changed !";
				$messageClass = "alert-info";
				$currentFolder = $_POST['currenPath']."/";
			}
			else{
				$generalMessage="Please choose a correct directory. Init the app again.";
				$messageClass = "alert-danger";
				$currentFolder = "";
				$initialized = "block";	
			}
		}
		else {
			$generalMessage="The current path cannot be null !";
			$messageClass = "alert-danger";
		}
	}


	if(isset($_POST["btnDelete"])) 
	{
		if(!(isset($_POST["selectedFileName"]) && trim($_POST["selectedFileName"]) != ''))
		{
			$generalMessage="The file should be selected !";
			$messageClass = "alert-danger";
		}
		else
		{
			$path = $currentFolder . $_POST['selectedFileName'];
				error_reporting(E_ALL);
				set_error_handler(function()
					{
						throw new Exception("Error");
					});
				$isDir = is_dir($path);

			if(delete_files($path))
			{
				if($isDir)
				{
					$generalMessage="The directory selected was deleted !";
				}else{
					$generalMessage="The file selected was deleted !";
				}
				$selectedFileName = "";
				
				$messageClass = "alert-info";	
			}
			else{
				$selectedFileName = "";
				$generalMessage="The file couldn't be deleted !";
				$messageClass = "alert-danger";	
			}			
		}
	}

	if(isset($_POST["btnCopy"])) 
	{
		if(!(isset($_POST["selectedFileName"]) && trim($_POST["selectedFileName"]) != ''))
		{
			$generalMessage="The file should be selected !";
			$messageClass = "alert-danger";
		}
		else
		{
			if(isset($_POST['fileNewName']) && $_POST['fileNewName'] != "")
			{

				$newPath = $_POST['fileNewName'] ."/". $_POST['newFileName'];
				exec("cp {$currentFolder}/{$_POST['selectedFileName']} {$newPath}");
				$selectedFileName = "";
				$generalMessage="The file selected was copied !";
				$messageClass = "alert-info";
			}
			else {
				$generalMessage="The new path file cannot be null or empty !";
				$messageClass = "alert-danger";

			}
		}
	}
	
	if(isset($_POST["btnMove"])) 
	{
		if(!(isset($_POST["selectedFileName"]) && trim($_POST["selectedFileName"]) != ''))
		{
			$generalMessage="The file should be selected !";
			$messageClass = "alert-danger";
		}
		else
		{
			
			
			if( isset($_POST['selectedFileName']) && $_POST['selectedFileName'] != "")
			{
				if(isset($_POST['fileNewName']) && $_POST['fileNewName'] != "")
				{
					$newPath = $_POST['fileNewName'] ."/". $_POST['newFileName'];
					exec("mv {$currentFolder}/{$_POST['selectedFileName']} {$newPath}");
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
				$generalMessage="The file should be selected !";
				$messageClass = "alert-danger";
			}
		}
	}

	// When we want to change user permissions
	if(isset($_POST["btnPerm"])) 
	{
		if(isset($_POST["selectedFileName"]) && trim($_POST["selectedFileName"]) != '')
		{
			if(isset($_POST["userPerm"]) && $_POST["userPerm"] != '')
			{	
				error_reporting(E_ALL);
				set_error_handler(function()
					{
						throw new Exception("Error");
					});
				try
				{	
					$folderPerm =trim($currentFolder).'/'.trim($_POST['selectedFileName']);
					$perm = (int) $_POST['userPerm'];
					if(chmod($folderPerm,$perm))
					{
						$generalMessage="Permissions were changed.";
						$messageClass = "alert-success";
					}
					else 
					{
						$generalMessage="Permissions weren't changed. Try it later.";
						$messageClass = "alert-danger";
					}
				}
				catch (Exception $e)
				{
						$generalMessage="You aren't the owner.";
						$messageClass = "alert-danger";
				}
				restore_error_handler();
			}
			else
			{
				$generalMessage="You have to write the permissions.";
				$messageClass = "alert-danger";
			}
		}
		else
		{
			$generalMessage="The file should be selected !";
			$messageClass = "alert-danger";
		}
	}

	//When we want to change the owner 
	if(isset($_POST["btnNewOw"])) 
	{
		if(isset($_POST["selectedFileName"]) && trim($_POST["selectedFileName"]) != '')
		{
			if(isset($_POST["newOw"]) && $_POST["newOw"] != '')
			{
				$folderN =trim($currentFolder).'/'.trim($_POST['selectedFileName']);
				$userN = $_POST['newOw'];
				/*if(chown($folderN,$userN))
				{
					$generalMessage="Owner was changed.";
					$messageClass = "alert-success";
				}
				else 
				{
					$generalMessage="Owner wasn't changed.";
					$messageClass = "alert-danger";
				}*/
			}
			else
			{
				$generalMessage="You have to write who is the new owner.";
				$messageClass = "alert-danger";
			}
		}
		else
		{
			$generalMessage="The file should be selected !";
			$messageClass = "alert-danger";
		}
	}

	if(isset($_POST["createDir"])) {
		if(isset($_POST["dirName"]) && $_POST["dirName"] != '')
		{
			$directoryName=trim($currentFolder).'/'.trim($_POST['dirName']);
			if (!file_exists($directoryName))
			{
				if(mkdir($directoryName,0777))
				{
					$generalMessage="The directory was created.";
					$messageClass = "alert-success";
				}
				else 
				{
					$generalMessage="The directory wasn't created";
					$messageClass = "alert-danger";
				}
			}
			else
			{
				$generalMessage="The directory already exists.";
				$messageClass = "alert-danger";
			}
		}
		else
		{
			$generalMessage="Directory name is required.";
			$messageClass = "alert-danger";
		}
	}

	//When we want to create a new file 
	if(isset($_POST["createFil"])) {
		if(isset($_POST["filName"]) && $_POST["filName"] != '')
		{
			$fileName=trim($currentFolder).'/'.trim($_POST['filName']);
			if (!file_exists($fileName))
			{
				if(touch($fileName))
				{
					$generalMessage="The file was created.";
					$messageClass = "alert-success";
				}
				else 
				{
					$generalMessage="The file wasn't created";
					$messageClass = "alert-danger";
				}
			}
			else
			{
				$generalMessage="The file already exists.";
				$messageClass = "alert-danger";
			}
		}
		else
		{
			$generalMessage="File name is required.";
			$messageClass = "alert-danger";
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


	function delete_files($target) {
		$result = false;
	    if(is_dir($target)){
	        $files = glob( $target . '*', GLOB_MARK ); //GLOB_MARK adds a slash to directories returned

	        foreach( $files as $file ){
	            $result = delete_files( $file );      
	        }

			try {
			    $result =rmdir( $target );
			} catch (Exception $e) {
			}
	        
	        	
	    } elseif(is_file($target)) {
	        $result = unlink( $target );  
	    }
	    return $result;
	}
	
	//$tableBody .= "</tBody>";

	
	 //echo $currentFolder;
	// echo $FILE_LIST_COMMAND . "<br/>";
	// echo $CURRENT_PATH_COMMAND."<br/>";
	
	
	include("index.php");
?>