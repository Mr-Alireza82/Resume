package com.example.sampleproject.core.network.tcp.data

import androidx.lifecycle.ViewModel
import com.example.sampleproject.core.network.tcp.domain.TCPViewModel

open class TCPViewModelImpl : ViewModel(), TCPViewModel  {
    override fun startTcpSession() {}

    override fun writeToTcp(client: Client) {}

    override fun readFromTcp(client: Client) {}
}
