package com.gradleplugin.plugins.task
import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

abstract class BasicTask extends DefaultTask {
    @InputFile //Gradle이 매핑된 파일의 변경 사항을 감시하고 변경된 경우에만 작업을 다시 실행하도록 설정합니다.
    abstract RegularFileProperty getFile1()
    @InputFile
    abstract RegularFileProperty getFile2()
    @OutputFile //결과 파일이 변경된 경우에만 작업을 다시 실행합니다.
    abstract RegularFileProperty getResultFile()

    BasicTask() {
        resultFile.convention(project.layout.buildDirectory.file('diff-result.txt'))
    }

    @TaskAction //task가 실행될 때 실행되는 메서드임을 나타냅니다.
    def diff() {
        String diffResult
        if (size(file1) == size(file2)) {
            diffResult = "Files have the same size at ${file1.get().asFile.size()} bytes."
        } else {
            File largestFile = size(file1) > size(file2) ? file1.get().asFile : file2.get().asFile
            diffResult = "${largestFile.toString()} was the largest file at ${largestFile.size()} bytes."
        }

        resultFile.get().asFile.write diffResult

        println "File written to $resultFile"
        println diffResult
    }

    private static long size(RegularFileProperty regularFileProperty) {
        return regularFileProperty.get().asFile.size()
    }
}