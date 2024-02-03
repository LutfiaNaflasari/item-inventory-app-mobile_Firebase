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

    $perintah = "INSERT INTO barang SET kode = '$kode', nama = '$nama', satuan = '$satuan', harga = '$harga', stok = '$stok', terjual = '$terjual'";
    $eksekusi = mysqli_query($konek, $perintah);
    $cek = mysqli_affected_rows($konek);
    if($cek>0){
        $response["kode"] = 1;
        $response["pesan"] = "Simpan Data Berhasil";
    }else{
        $response["kode"] = 0;
        $response["pesan"] = "Gagal Menyimpan Data";
    }
}else{
    $response["kode"] = 0;
    $response["pesan"] = "Tidak Ada Post Data";
}

echo json_encode($response);
mysqli_close($konek);
?>