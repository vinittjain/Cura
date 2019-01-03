<?php

include "db.php";

$id = 1;

$query = "SELECT * from `realtime`";

$result = mysqli_query($link,$query);

while($row = mysqli_fetch_array($result)){

 echo $row['angle'] ."=" .$row['datetime'] ."|";

}

?>