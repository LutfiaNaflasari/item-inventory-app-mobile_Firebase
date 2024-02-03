<?php
require("koneksi.php");

$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $id = $_POST['$id'];
    $kode_barang = $_POST['kode_barang'];
    $nama_pelanggan = $_POST['nama_pelanggan'];
    $alamat_pelanggan = $_POST['alamat_pelanggan'];
    $nama_barang = $_POST['nama_barang'];
    $total_beli = $_POST['total_beli'];
    $harga_awal = $_POST['harga_awal'];
    $total_bayar = $_POST['total_bayar'];

    $perintah = "INSERT INTO penjualan SET id = '$id', kode_barang = '$kode_barang', nama_pelanggan = '$nama_pelanggan', 
    alamat_pelanggan = '$alamat_pelanggan', nama_barang = '$nama_barang', total_beli = '$total_beli', harga_awal = '$harga_awal',
    total_bayar = '$total_bayar'";
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