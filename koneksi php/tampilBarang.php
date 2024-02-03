<?php
require("koneksi.php");
$perintah = "SELECT * FROM barang";
$eksekusi = mysqli_query($konek, $perintah);
$cek = mysqli_affected_rows($konek);

if($cek>0){
    $response["kode"] = 1;
    $response["pesan"] = "Data Tersedia";
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

echo json_encode($response);
mysqli_close($konek);
?>