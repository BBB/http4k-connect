package org.http4k.connect.google.authentication

import java.io.File

interface FileSystem {
    fun readFile(path: String): String
    fun exists(path: String): Boolean

}

object RealFileSystem : FileSystem {
    override fun readFile(path: String) = File(path).readText()
    override fun exists(path: String) = File(path).exists()
}
