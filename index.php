<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Page Title</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" media="screen" href="main.css">
	<script>

		function rowSelected(row)
		{
			document.getElementById("fileName").value = row.firstElementChild.innerHTML
			
		}
		$(document).ready(function () {
			var activeSystemClass = $('.list-group-item.active');

			//something is entered in search form
			$('#system-search').keyup(function () {
				var that = this;
				// affect all table rows on in systems table
				var tableBody = $('.table-list-search tbody');
				var tableRowsClass = $('.table-list-search tbody tr');
				$('.search-sf').remove();
				tableRowsClass.each(function (i, val) {

					//Lower text for case insensitive
					var rowText = $(val).text().toLowerCase();
					var inputText = $(that).val().toLowerCase();
					if (inputText != '') {
						$('.search-query-sf').remove();
						tableBody.prepend('<tr class="search-query-sf"><td colspan="6"><strong>Searching for: "'
							+ $(that).val()
							+ '"</strong></td></tr>');
					}
					else {
						$('.search-query-sf').remove();
					}

					if (rowText.indexOf(inputText) == -1) {
						//hide rows
						tableRowsClass.eq(i).hide();

					}
					else {
						$('.search-sf').remove();
						tableRowsClass.eq(i).show();
					}
				});
				//all tr elements are hidden
				if (tableRowsClass.children(':visible').length == 0) {
					tableBody.append('<tr class="search-sf"><td class="text-muted" colspan="6">No entries found.</td></tr>');
				}
			});
		});
	</script>


	<link href="http://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet"
		id="bootstrap-css">
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<body>
	<div style="margin: 15px;" class='alert <?php echo $messageClass?>'>
		<?php
		
		echo $generalMessage;

		?>
	</div>
	<div class="container" style="margin-top: 1em;">
		<!-- Sign up form -->
		<form action="commands.php" method="POST">
			<button type="submit" name="firstLoad" id="firstLoad" class="btn btn-outline-info btn-block" style="display: <?php echo $initialized; ?>"> Init Application</button>
			<!-- Sign up card -->
			<div class="card person-card" style="display: <?php if($initialized == "block") {echo 'none'; } else{ echo 'block'; } ?>">
				<div class="card-body">
					<!-- Sex image -->
					<div class="row  pb-5">
						<div class="col-7">
							<h2 id="who_message" class="card-title ">Linux file manager</h2>
						</div>
						<div class="col-5">

							<h6 class="text-right">
								Current path:
								<input id="currenPath" name="currenPath" type="text"
									class="form-check-inline form-control form-control-plaintext text-muted w-50 font-italic border-info"
									placeholder="Current path" value='<?php echo $currentFolder?> '>
								<button type="submit" id="changePath" name="changePath" class="btn btn-outline-info">Change</button>
							</h6>

						</div>
					</div>
					<div class="row">


						<div class="col-md-6 p-5">
							<div class="row my-4">
								<div class="col-md-12">
									<div class="card">
										<div class="card-body">
											<h2 class="card-title">Create a directory</h2>
											<div class="form-group">
												<label for="email" class="col-form-label">Set the name</label>
												<input type="text" class="form-control" name="dirName" id="dirName" placeholder="Directory name">
												<button type="submit" name="createDir" id="createDir" class="btn btn-outline-info" >Create</button>
											</div>

										</div>
									</div>
								</div>
							</div>

							<div class="row ">
								<div class="col-md-12">
									<div class="card">
										<div class="card-body">
											<h2 class="card-title">Create a file</h2>
											<div class="form-group">
												<label for="email" class="col-form-label">Select the file</label>
												<input type="file" class="form-control" id="email">
												<button type="button" class="btn btn-outline-info">Upload</button>
											</div>

										</div>
									</div>
								</div>
							</div>

						</div>

						<div class="col-6 ">
							<div class="row pr-4">

								<h4 id="who_message" class="card-title mx-auto">Current path file list</h4>
								<table class="table table-list-search">
									<thead>
										<tr>
											<th class="w-50">Name</th>
											<th>Access</th>
										</tr>
									</thead>
									<tbody>
										<?php echo $tableBody; ?>
									</tbody>
								</table>
							</div>
							<div class="row my-4">
								<div class="col-md-12">
									<div class="card">
										<div class="card-body">
											<h2 class="card-title">Manage files</h2>
											<div class="form-group">
												
												<input type="text" class="form-control" name="fileName" id="fileName" placeholder="File name">
												<div class="btn-group" role="group" aria-label="Basic example">
													<button type="button" class="btn btn-outline-info">Delete</button>
													<button type="button" class="btn btn-outline-info">Copy</button>
													<button type="button" class="btn btn-outline-info ">Move</button>
													<div class="dropdown">
													<button class="btn btn-outline-info dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
														Access rights
													</button>
													<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
														<a class="dropdown-item" href="#">Action</a>
														<a class="dropdown-item" href="#">Another action</a>
														<a class="dropdown-item" href="#">Something else here</a>
													</div>
													</div>

												</div>
											</div>

										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>


		</form>

	</div>
</body>

</html>