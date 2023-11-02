package com.example.mekato

import android.annotation.SuppressLint
import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.util.Log
import java.io.*
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MekatoProvider : ContentProvider() {

    companion object {
        private const val AUTHORITY = "com.example.mekato"
        private const val FILE_PATH = "exploit.txt"
        private const val URI_CODE = 1
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, FILE_PATH , URI_CODE)
        }
    }


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Implement this to handle requests to insert a new row.")
    }

    override fun onCreate(): Boolean {
        return false
    }

    @SuppressLint("LongLogTag")
    override fun openFile(uri: Uri, str: String): ParcelFileDescriptor? {
        Log.d("MekatoProvider openFile", "esta es la URI $uri")
        val file = File(uri.getQueryParameter("path"))
        Log.d("MekatoProvider openFile", "esta es el file $file")
        return ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val context = context ?: return null
        if (uriMatcher.match(uri) != URI_CODE) {
            return null
        }
        Log.i("pailaProvider", "path $FILE_PATH")
        val filePath = context.filesDir.absolutePath + "/" + FILE_PATH
        val file = FileInputStream(filePath)
        val reader = BufferedReader(InputStreamReader(file))
        Log.i("pailaProvider", "path $file")
        Log.i("archivo a leer", "path $reader")
        var line: String?

        val cursor = MatrixCursor(arrayOf("_id", "file_name", "file_content"))

        cursor.newRow().apply {
            add("_id", 1)
            add("file_name", FILE_PATH)
            add("file_content", readTextFile(reader))
        }

        Log.i("pailaprovider", "filepath $filePath")

        return cursor
    }

    private fun readTextFile(reader: BufferedReader): String {
        val stringBuilder = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        reader.close()
        return stringBuilder.toString()
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}