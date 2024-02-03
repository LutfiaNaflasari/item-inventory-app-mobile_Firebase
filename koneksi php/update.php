<?php
require("koneksi.php");

$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $kode = $_POST['kode'];
    $nama = $_POST['nama'];
    $satuan = $_POST['satuan'];
    $harga = $_POST['harga'];
    $stok = $_POST['stok'];
    $terjual = $_POST['terjual'];

    $perintah = "UPDATE barang SET kode = '$kode', nama = '$nama', satuan = '$satuan', harga = '$harga', stok = '$stok', terjual = '$terjual'  WHERE kode = '$kode'";
    $eksekusi = mysqli_query($konek, $perintah);
    $cek = mysqli_affected_rows($konek);
    if($cek>0){
        $response["kode"] = 1;
        $response["pesan"] = "Data Berhasil Diubah";
    }else{
        $response["kode"] = 0;
        $response["pesan"] = "Data Gagal Diubah";
    }
}else{
    $response["kode"] = 0;
    $response["pesan"] = "Tidak Ada Post Data";
}

echo json_encode($response);
mysqli_close($konek);