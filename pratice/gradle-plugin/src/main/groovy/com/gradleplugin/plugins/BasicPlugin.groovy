package com.gradleplugin.plugins

import com.gradleplugin.plugins.extention.BasicExtension
import com.gradleplugin.plugins.task.BasicTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class BasicPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) { // 플러그인이 build.gradle 파일 에 적용될 때 호출됩니다 .

        // 플러그인이 적용되는 build.gradle 에서 구성 블록 에서 구성을 정의할 수 있음을 의미합니다
        project.extensions.create('fileDiff', BasicExtension)

        //구성된 fileDiff 속성을 통해 file을 설정하고 task로 등록합니다.
        project.tasks.register('fileDiff', BasicTask) {
            file1 = project.fileDiff.file1
            file2 = project.fileDiff.file2
        }
    }
}

