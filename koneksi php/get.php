<?php
require("koneksi.php");

$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $kode = $_POST['kode'];

    $perintah = "SELECT * FROM barang WHERE kode = '$kode'";
    $eksekusi = mysqli_query($konek, $perintah);
    $cek = mysqli_affected_rows($konek);
    if($cek>0){
        $response["kode"] = 1;
        $response["pesan"] = "Data Berhasil Dihapus";
        $response["data"] = array();

        while($ambil=mysqli_fetch_object($eksekusi)){
            $F["kode"] = $ambil->kode;
            $F["nama"] = $ambil->nama;
            $F["satuan"] = $ambil->satuan;
            $F["harga"] = $ambil->harga;
            $F["stok"] = $ambil->stok;
            $F["terjual"] = $ambil->terjual;

            array_push($response["data"],$F);
        }
    }else{
        $response["kode"] = 0;
        $response["pesan"] = "Data Tidak Tersedia";
    }
}else{
    $response["kode"] = 0;
    $response["pesan"] = "Tidak Ada Post Data";
}

echo json_encode($response);
mysqli_close($konek);