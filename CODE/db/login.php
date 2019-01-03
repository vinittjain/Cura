<?php

include "db.php";

if($_POST){



$query = "SELECT * FROM `users` WHERE email = '".mysqli_real_escape_string($link,$_POST['email'])."' LIMIT 1 ";
		$result = mysqli_query($link,$query);
		
		
		$row = mysqli_fetch_assoc($result);
			if($row['password'] == md5(md5($row['id']).$_POST['password'])){
				
				echo 1;	
				
			$_SESSION['id'] = $row['id'];
			}else{
				$error = "Couldn't find that username/password combination. Please try again";
			}	
	}
	
	if($error != ""){
		echo $error;
		exit();
	}




?>






<div id="tabs">    


    <form  method="POST">
    <p><input id="name" name="email" type="text" placeholder="Email ID"></p>
     <p><input id="password" name="password" type="password" placeholder="Password">
     </p>
     <input type="submit" value="Login">
     </form>