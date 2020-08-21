package com.example.manageme.model

class Transaksi {
     var id : Int= 0
      var harga : Int= 0
     var jenis :String? = null
     var tipe :String? = null
     var tanggal :String? = null


    constructor(harga: Int, jenis: String?, tipe: String?) {
        this.harga = harga
        this.jenis = jenis
        this.tipe = tipe
        //this.tanggal = tanggal
    }

    constructor(id: Int, harga: Int, jenis: String?, tipe: String?) {
        this.id = id
        this.harga = harga
        this.jenis = jenis
        this.tipe = tipe
        //this.tanggal = tanggal
    }


}