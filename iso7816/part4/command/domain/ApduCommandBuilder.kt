package com.example.sampleproject.core.iso.iso7816.part4.command.domain

object ApduCommandBuilder {
    private var cla: Byte? = null
    private var ins: Byte? = null
    private var p1: Int? = null
    private var p2: Int? = null
    private var lc: Int? = null
    private var data: ByteArray? = null
    private var le: Int? = null

    fun setCla(cla: Byte) = apply { this.cla = cla }
    fun setIns(ins: Byte) = apply { this.ins = ins }
    fun setP1(p1: Int) = apply { this.p1 = p1 }
    fun setP2(p2: Int) = apply { this.p2 = p2 }
    fun setLc(lc: Int?) = apply { this.lc = lc }
    fun setData(data: ByteArray?) = apply { this.data = data }
    fun setLe(le: Int?) = apply { this.le = le }

    fun build(): ByteArray {
        requireNotNull(cla) { "CLA is required" }
        requireNotNull(ins) { "INS is required" }
        requireNotNull(p1) { "P1 is required" }
        requireNotNull(p2) { "P2 is required" }

        return ApduCommand(cla!!, ins!!, p1!!, p2!!, lc, data, le).toByteArray()
    }
}
