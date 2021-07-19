<?php
if(isset($_REQUEST['submit'])){
    echo $_REQUEST['xsscode'];
    echo "<a href="./XSS.php">返回</a>"
}else{
    echo "<a href="./XSS.php">返回</a>"
}
?>