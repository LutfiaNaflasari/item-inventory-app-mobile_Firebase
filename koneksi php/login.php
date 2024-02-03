<?php
require("koneksi.php");

$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $username = $_POST['username'];
    $password = $_POST['password'];

    $perintah = "SELECT * FROM user WHERE username = '$username' AND password = '$password'";
    $eksekusi = mysqli_query($konek, $perintah);
    $cek = mysqli_affected_rows($konek);
    if($cek>0){
        $response["kode"] = 1;
        $response["pesan"] = "Login Berhasil";
    }else{
        $response["kode"] = 0;
        $response["pesan"] = "Gagal Login";
    }
}else{
    $response["kode"] = 0;
    $response["pesan"] = "Tidak User";
}

echo json_encode($response);
mysqli_close($konek);
?>