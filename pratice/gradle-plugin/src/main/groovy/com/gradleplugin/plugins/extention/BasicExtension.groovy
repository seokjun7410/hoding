package com.gradleplugin.plugins.extention

import org.gradle.api.file.RegularFileProperty

abstract class BasicExtension{
    abstract RegularFileProperty getFile1()
    abstract RegularFileProperty getFile2()
}

