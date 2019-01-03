<?php

include "db.php";

if($_GET){

  $userId = mysqli_real_escape_string($link,$_GET['userId']);
  $angle = mysqli_real_escape_string($link,$_GET['Angle']);
  date_default_timezone_set('Asia/Kolkata');     
  $date= date("Y-m-d");
  $time=date("H:m");
  $datetime=$date."T".$time;
  
  
  
$query = "INSERT INTO `realtime`(`user_id`,`angle`,`datetime`) VALUES('".mysqli_real_escape_string($link,$_GET['userId'])."','".mysqli_real_escape_string($link,$_GET['angle'])."', '$datetime')";

if(mysqli_query($link,$query)){
echo 1;

}
}

?>

<div>    

<p>
  
  </p>
    <form  method="GET">
    <p><input id="UserId" name="userId" type="text" placeholder="UserId"></p>
    <p><input id="angle" name="angle" type="text" placeholder="Angle"></p>
      <p><input type="submit" value="Submit"></p>
  </form>
</div>