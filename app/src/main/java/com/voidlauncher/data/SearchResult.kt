package com.voidlauncher.data

sealed class SearchResult {
    data class App(val app: AppInfo) : SearchResult()
    data class Contact(val name: String) : SearchResult()
    data class Setting(val label: String) : SearchResult()
    data class Calc(val expression: String, val result: String) : SearchResult()
    data class Timer(val seconds: Int) : SearchResult()
    data class Web(val query: String) : SearchResult()
}
