package com.voidlauncher.data

object SearchLogic {
    private val contacts = listOf("Alex Kim", "Jordan Lee", "Mina Park", "Sam Rivera")
    private val settings = listOf("Wi-Fi", "Bluetooth", "Display", "Battery", "Sound", "Notifications")

    fun resultsFor(query: String): List<SearchResult> {
        val q = query.trim()
        if (q.isEmpty()) return emptyList()
        val out = mutableListOf<SearchResult>()

        // timer command
        val timerMatch = Regex("^timer\\s+(\\d+)$", RegexOption.IGNORE_CASE).find(q)
        if (timerMatch != null) {
            out.add(SearchResult.Timer(timerMatch.groupValues[1].toInt()))
        }

        // calculator: try eval simple expression
        val calc = tryCalc(q)
        if (calc != null) out.add(SearchResult.Calc(q, calc))

        // apps
        SampleApps.all.filter { it.name.contains(q, ignoreCase = true) }
            .forEach { out.add(SearchResult.App(it)) }

        // contacts
        contacts.filter { it.contains(q, ignoreCase = true) }
            .forEach { out.add(SearchResult.Contact(it)) }

        // settings
        settings.filter { it.contains(q, ignoreCase = true) }
            .forEach { out.add(SearchResult.Setting(it)) }

        // web fallback
        out.add(SearchResult.Web(q))

        return out
    }

    private fun tryCalc(expr: String): String? {
        // very simple: support + - * / with two operands
        val m = Regex("^\\s*(\\d+(?:\\.\\d+)?)\\s*([+\\-*/])\\s*(\\d+(?:\\.\\d+)?)\\s*$").find(expr) ?: return null
        val a = m.groupValues[1].toDouble()
        val op = m.groupValues[2]
        val b = m.groupValues[3].toDouble()
        val r = when (op) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> if (b == 0.0) return null else a / b
            else -> return null
        }
        return if (r == r.toLong().toDouble()) r.toLong().toString() else r.toString()
    }
}
