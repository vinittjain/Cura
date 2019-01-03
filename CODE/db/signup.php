<?php

include "db.php";

if($_POST){

     $name = mysqli_real_escape_string($link,$_POST['name']);
      $email = mysqli_real_escape_string($link,$_POST['email']);
    $age = mysqli_real_escape_string($link,$_POST['age']);
     $problem = mysqli_real_escape_string($link,$_POST['problem']);
    $password = mysqli_real_escape_string($link,$_POST['password']);


if(empty($name) || empty($email) || empty($age) || empty($problem) || empty($password)){
 echo "Please fill in all the details";
}else{
   
$query = "SELECT * FROM `users` WHERE email = '".mysqli_real_escape_string($link,$_POST['email'])."' LIMIT 1 ";
        $result = mysqli_query($link,$query);
        
        if(mysqli_num_rows($result) > 0)
            $error = "That email address is already taken";
        else{
            
            $query = "INSERT INTO `users`(`name`,`email`,`password`,`age`,`problem`) VALUES('".mysqli_real_escape_string($link,$_POST['name'])."','".mysqli_real_escape_string($link,$_POST['email'])."','".mysqli_real_escape_string($link,$_POST['password'])."','".mysqli_real_escape_string($link,$_POST['age'])."','".mysqli_real_escape_string($link,$_POST['problem'])."')";
            
            if(mysqli_query($link,$query)){
                
                $_SESSION['id'] = mysqli_insert_id($link);
                
        $query = "UPDATE users SET password = '" .md5(md5($_SESSION['id']).$_POST['password'])."' WHERE id= ".$_SESSION['id']." LIMIT 1 ";
                mysqli_query($link,$query);
                
                echo 1;
                
                
                
            }else{
                echo "Couldn't create user. Please try again";
            }


}

}
}

?>



<!-- Login and Signup forms -->
<div id="tabs">    

<p>
  <?php
  
  if($error){
  echo $error;
  }
  
  ?>
  
  </p>
    <form  method="POST">
    <p><input id="name" name="name" type="text" placeholder="Name"></p>
    <p><input id="email" name="email" type="text" placeholder="Email"></p>
    <p><input name="age" type="number" min="0" max="100" placeholder="Age"></p>
    <p><input name="problem" type="text" placeholder="Please enter your problem">
    <p><input id="password" name="password" type="password" placeholder="Password">
    <p><input type="submit" value="Signup"></p>
  </form>
</div>