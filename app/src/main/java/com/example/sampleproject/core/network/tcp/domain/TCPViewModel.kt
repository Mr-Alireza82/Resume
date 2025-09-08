package com.example.sampleproject.core.network.tcp.domain

import com.example.sampleproject.core.network.tcp.data.Client

interface TCPViewModel{
    fun startTcpSession()
    fun writeToTcp(client: Client)
    fun readFromTcp(client: Client)
}
