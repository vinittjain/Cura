<?php

include "db.php";

?>

<script type="application/javascript">
    var motorcycle_chart = new AwesomeChart('motorcycle_graph');
    motorcycle_chart.data = [
    <?php
    $query = mysql_query("select * from realtime") or die(mysql_error());
    while ($row = mysql_fetch_array($query)) {
        ?>
        <?php echo $row['angle'] . ','; ?>	
    <?php }; ?>
    ];
 
    motorcycle_chart.labels = [
    <?php
    $query = mysql_query("select * from users") or die(mysql_error());
    while ($row = mysql_fetch_array($query)) {
        ?>
        <?php echo "'" . $row['name'] . "'" . ','; ?>	
    <?php }; ?>
    ];
    motorcycle_chart.colors = ['gold', 'skyblue', '#FF6600', 'pink', 'darkblue', 'lightpink', 'green'];
    motorcycle_chart.randomColors = true;
    motorcycle_chart.animate = true;
    motorcycle_chart.animationFrames = 20;
    motorcycle_chart.draw();
</script>
