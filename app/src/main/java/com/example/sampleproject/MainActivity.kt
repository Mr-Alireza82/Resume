package com.example.sampleproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.sampleproject.core.iso.iso7816.part4.aid.KnownAids
import com.example.sampleproject.core.iso.iso7816.part4.reader.IccReaderProvider
import com.example.sampleproject.core.iso.iso7816.part4.session.CardSession
import com.example.sampleproject.core.iso.iso8583.protocol.sipa.builders.SipaProtocolBuilder
import com.example.sampleproject.core.utils.getLayoutDirection
import com.example.sampleproject.features.language.presentation.LanguageViewModel
import com.example.sampleproject.features.language.presentation.contract.LanguageEffect
import com.example.sampleproject.features.navigation.AppNavGraph
import com.example.sampleproject.ui.theme.SampleProjectTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val TAG = "TEST Select AID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        // launch a coroutine to call suspend function
        lifecycleScope.launch {
            val builder = SipaProtocolBuilder.from("0800", "920000").build()
        }

        setContent {
            val languageViewModel: LanguageViewModel = hiltViewModel()
            var layoutDirection by rememberSaveable { mutableStateOf(LayoutDirection.Ltr) }

            LaunchedEffect(Unit) {
                languageViewModel.effect.collectLatest {
                    if (it is LanguageEffect.LanguageChanged) {
                        layoutDirection = getLayoutDirection(it.language.code)
                    }
                }
            }

            SampleProjectTheme {
                CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                    val navController = rememberNavController()
                    AppNavGraph(navController = navController)
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            val reader = IccReaderProvider.getReader(this@MainActivity)
            val session = CardSession(reader)

            if (session.start()) {
                val aid = KnownAids.TK
                val select = session.selectAid(aid)
                Log.i("APDU_TEST_SELECT", "Select result: $select")

                if (select.isSuccess()) {
                    val write = session.verifyPin("2345")
                    Log.i("APDU_TEST_PIN", "Write result: $write")

                    if (write.isSuccess()) {

                        val read = session.readKeyApdu()
                        Log.i("APDU_TEST_READ", "Read result: $read")
                        if (read.isSuccess()) {
                            val dataHex = read.data?.joinToString("") { "%02X".format(it) }
                            Log.i("APDU_TEST_READ", "Read data: $dataHex")
                        }
                    }
                }
            }
        }

/*        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val iccReader = POICardManager.getDefault(this@MainActivity).getIccCardReader()

                if (iccReader.open() == 0 && waitForCard(iccReader)) {
                    iccReader.reset()

                    val atr = iccReader.getCardReaderInfo()?.mAttribute
                    val atrHex = atr?.joinToString("") { "%02X".format(it) }
                    Log.d(TAG, "ATR: $atrHex")

                    val aid = deriveAidFromAtr(atrHex)
                    Log.d(TAG, "Derived AID: $aid")

                    if (aid != null) {
                        selectAid(iccReader, aid)
                    } else {
                        Log.w(TAG, "Unknown ATR. AID not derived.")
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Card processing error", e)
            }
        }*/
    }

    // 2.1.8
/*    fun btnStartTransactionClick() {
        val pm = packageManager

        if (isPackageInstalled("com.tech.pay", pm)) {
            // Hardcoded inputs
            val MID = "211261684"
            val TID = "22271742"
            val OrderId = "ORD133456"
            val Amount = "2001" // Must be between 2000 - 1,000,000,000 Rials
            val MerchantName = "MyStore2"

            if (!isValidAmount(Amount)) {
                showToast("Invalid amount: must be between 2,000 and 1,000,000,000 Rials")
                return
            }

            val payId = "$MID-$TID-$MerchantName"
            val billId = OrderId

            openOtherApp(Amount, payId, billId)
        } else {
            showToast("TechPay app not installed.")
        }
    }

    fun openOtherApp(amount: String, payId: String, billId: String) {
        val intent = packageManager.getLaunchIntentForPackage("com.tech.pay") ?: return

        intent.putExtra("amount", amount)
        intent.putExtra("packageName", packageName)

        if (payId.isNotEmpty())
            intent.putExtra("payId", payId)
        // if (billId.isNotEmpty())
        //     intent.putExtra("billId", billId)

        val transactionType = "purchase"
        intent.putExtra("transactionType", transactionType)

        startActivity(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.i(TAG, "onNewIntent Received: $intent")
        val result = intent.getStringExtra("transaction") // lowercase, per doc 2.2.2
        Log.i(TAG, "xxxTranasdsadsadsadx: $result")
        if (result != null) {
            showToast("xxxTransaction resultxxxxx: $result")
            Log.i(TAG, "Transaction result: $result")
        } else {
            showToast("xxxNo transaction result receivedxxx.")
            Log.i(TAG, "No transaction result received")
        }
    }

    private fun isPackageInstalled(packageName: String, pm: PackageManager): Boolean {
        return try {
            pm.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    private fun isValidAmount(amountStr: String): Boolean {
        return try {
            val amount = amountStr.toLong()
            amount in 2000..1_000_000_000
        } catch (e: NumberFormatException) {
            false
        }
    }

    fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }*/

    // 2.1.19
/*    data class IbanShare(val iban: String, val amount: Int, val priority: Int)

    fun btnStartTransactionClick() {
        val pm = packageManager

        if (!isPackageInstalled("com.tech.pay", pm)) {
            showToast("TechPay app not installed.")
            return
        }

        // IBAN Shares
        val ibanShares = listOf(
            IbanShare("IR490160000000000539453006", 20000, 0),  // Main
            IbanShare("IR730580103801101859851001", 60000, 1)   // Secondary
        )

        val totalAmount = ibanShares.sumOf { it.amount }

        if (!isValidAmount(totalAmount.toString())) {
            showToast("Invalid amount: must be between 2,000 and 1,000,000,000 Rials")
            return
        }

        val formattedString = ibanShares.joinToString(";") {
            "${it.iban},${it.amount},${it.priority}"
        }

        Log.i("TASHIMMMM", totalAmount.toString())
        Log.i("TASHIMMMM", formattedString)

        val intent = Intent().apply {
            action = "com.tosan.app_techpayment_catalog.TASHIM"
            setPackage("com.tech.pay")
            putExtra("amount", totalAmount.toString())
            putExtra("ibanShareList", formattedString)
            putExtra("transactionType", "tashim")
            putExtra("packageName", packageName)
        }

        startActivity(intent)
    }

    private fun isPackageInstalled(packageName: String, pm: PackageManager): Boolean {
        return try {
            pm.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    private fun isValidAmount(amountStr: String): Boolean {
        return try {
            val amount = amountStr.toLong()
            amount in 2000..1_000_000_000
        } catch (e: NumberFormatException) {
            false
        }
    }

    fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntentIfExists(intent)
    }

    private fun handleIntentIfExists(intent: Intent) {
        val data = intent.getStringExtra("data")
        val errorMessage = intent.getStringExtra("errorMessage")
        val transactionMessage = intent.getStringExtra("transaction")

        val result = buildString {
            if (!data.isNullOrEmpty()) append("✅ Data: $data\n")
            if (!transactionMessage.isNullOrEmpty()) append("ℹ️ Transaction: $transactionMessage\n")
            if (!errorMessage.isNullOrEmpty()) append("❌ Error: $errorMessage\n")
        }

        Log.i("XXXRESULTXXX", "XXXRESULTXXX: $result")
        showToast(if (result.isNotBlank()) result else "No transaction result.")
    }*/

/*    suspend fun waitForCard(iccReader: PosIccCardReader): Boolean {
        repeat(30) {
            val result = iccReader.detect()
            Log.d(TAG, "Polling detect() → $result")
            if (result == 0) return true
            delay(1000)
        }
        return false
    }

    fun deriveAidFromAtr(atrHex: String?): String? {
        return when (atrHex?.uppercase()) {
            "3B6800000073C84011009000" -> "A0000000030000"
            "3B8F8001804F0CA000000306030001000000006A" -> "A0000000031010"
            else -> null
        }
    }

    private fun selectAid(reader: PosIccCardReader, aidHex: String) {
        val aidBytes = aidHex.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
        val apdu = byteArrayOf(0x00, 0xA4.toByte(), 0x04, 0x00, aidBytes.size.toByte()) + aidBytes

        val rsp = PosByteArray()
        val sw = PosByteArray()

        val result = reader.transmitApdu(apdu, rsp, sw)
        val swHex = sw.buffer?.joinToString(" ") { "%02X".format(it) }
        val rspHex = rsp.buffer?.byteArrayToHex() ?: "null"

        Log.d(TAG, "SELECT AID result=$result, SW=$swHex, RSP=$rspHex")

        val parsedRsp = parseSelectResponse(rsp.buffer)
        parsedRsp.forEach { (tagWithLen, value) ->
            val (tag, lenStr) = tagWithLen.split("|")
            Log.d(TAG, "TLV → Tag=$tag, Length=$lenStr, Value=${value.byteArrayToHex()}")
        }
    }

    fun parseSelectResponse(response: ByteArray?): Map<String, ByteArray> {
        val result = mutableMapOf<String, ByteArray>()
        if (response == null) return result

        var i = 0
        while (i < response.size - 1) {
            val tag = buildString {
                append("%02X".format(response[i]))
                if ((response[i].toInt() and 0x1F) == 0x1F) {
                    // Multi-byte tag
                    i++
                    append("%02X".format(response.getOrNull(i) ?: break))
                }
            }

            val lengthByte = response.getOrNull(++i) ?: break
            val len = lengthByte.toInt() and 0xFF

            val value = response.copyOfRange(i + 1, i + 1 + len)
            result["$tag|$len"] = value

            i += len
        }
        return result
    }*/
}
