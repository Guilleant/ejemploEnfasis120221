package com.ingenesys.kotlin202201

class ClsNinos {

    var id: Int? = 0
    var tipoId: String? = null
    var nroId: Int? = null
    var apellidos: String? = null
    var nombres: String? = null
    var direccion: String? = null
    var telefono: String? = null

    constructor(ti: String, id: Int, ape: String, nom: String, dir: String?, tel: String?) {
        this.tipoId = ti
        this.nroId = id
        this.apellidos = ape
        this.nombres = nom
        this.direccion = dir
        this.telefono = tel
    }

    constructor(){

    }
}