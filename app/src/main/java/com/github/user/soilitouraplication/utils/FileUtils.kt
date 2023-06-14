package com.github.user.soilitouraplication.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

object FileUtils {
    fun uriToFile(context: Context, uri: Uri): File? {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val file = createTemporalFile(context)
        if (inputStream != null) {
            writeInputStreamToFile(inputStream, file)
            return file
        }
        return null
    }

    @Throws(IOException::class)
    private fun writeInputStreamToFile(inputStream: InputStream, file: File) {
        val outputStream = FileOutputStream(file)
        val buffer = ByteArray(4 * 1024)
        var read: Int
        while (inputStream.read(buffer).also { read = it } != -1) {
            outputStream.write(buffer, 0, read)
        }
        outputStream.flush()
        outputStream.close()
        inputStream.close()
    }

    private fun createTemporalFile(context: Context): File {
        val tempFileName = "temp_image"
        val tempDir = context.cacheDir
        return File.createTempFile(tempFileName, null, tempDir)
    }
}
