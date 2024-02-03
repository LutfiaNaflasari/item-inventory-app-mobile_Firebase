<?php
require("koneksi.php");

$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $kode = $_POST['kode'];

    $perintah = "DELETE FROM barang WHERE kode = '$kode'";
    $eksekusi = mysqli_query($konek, $perintah);
    $cek = mysqli_affected_rows($konek);
    if($cek>0){
        $response["kode"] = 1;
        $response["pesan"] = "Data Berhasil Dihapus";
    }else{
        $response["kode"] = 0;
        $response["pesan"] = "Gagal Menghapus Data";
    }
}else{
    $response["kode"] = 0;
    $response["pesan"] = "Tidak Ada Post Data";
}

echo json_encode($response);
mysqli_close($konek);